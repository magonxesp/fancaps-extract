package io.github.magonxesp.fancapsextract

import it.skrape.selects.Doc
import it.skrape.selects.DocElement
import it.skrape.selects.ElementNotFoundException

object SearchExtractor {
	private fun resolveResultType(text: String): SearchType? {
		if (text.lowercase().contains("movie")) {
			return SearchType.MOVIES
		}

		if (text.lowercase().contains("tv")) {
			return SearchType.TV
		}

		if (text.lowercase().contains("anime")) {
			return SearchType.ANIME
		}

		return null
	}

	private fun resultSections(content: DocElement): Map<SearchType, DocElement> {
		var lastSearchType: SearchType? = null
		val searchSections: MutableMap<SearchType, DocElement> = mutableMapOf()

		for (child in content.children) {
			if (child.tagName == "h2") {
				lastSearchType = resolveResultType(child.text)
			} else if (lastSearchType != null && child.tagName == "table") {
				searchSections[lastSearchType] = child
			}
		}

		return searchSections
	}

	private fun DocElement.buildSearchResult(type: SearchType): SearchResult? =
		try {
			val link = findFirst("h4 a")

			SearchResult(
				title = link.text,
				url = Context.getFullUrl(link.attribute("href")),
				type = type
			)
		} catch (exception: ElementNotFoundException) {
			null
		}

	private fun itemsFromSection(section: DocElement, type: SearchType): List<SearchResult> =
		try {
			section.findAll(".row > div:first-child").mapNotNull { row -> row.buildSearchResult(type) }
		} catch (exception: ElementNotFoundException) {
			listOf()
		}

	fun extract(document: Doc): List<SearchResult> {
		val results: MutableList<SearchResult> = mutableListOf()
		val content = document.findSecondOrNull(".single_post_content")

		if (content != null) {
			val sections = resultSections(content)
			sections.forEach { results.addAll(itemsFromSection(it.value, it.key)) }
		}

		return results
	}
}
