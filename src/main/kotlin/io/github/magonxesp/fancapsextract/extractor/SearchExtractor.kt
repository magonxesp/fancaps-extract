package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.Context
import io.github.magonxesp.fancapsextract.Media
import io.github.magonxesp.fancapsextract.MediaType
import io.github.magonxesp.fancapsextract.exception.InvalidMediaUrlException
import io.github.magonxesp.fancapsextract.findSecondOrNull
import it.skrape.selects.Doc
import it.skrape.selects.DocElement
import it.skrape.selects.ElementNotFoundException

class SearchExtractor(private val context: Context) : Extractor<List<Media>> {
	private fun resolveResultType(text: String): MediaType? {
		if (text.lowercase().contains("movie")) {
			return MediaType.MOVIES
		}

		if (text.lowercase().contains("tv")) {
			return MediaType.TV
		}

		if (text.lowercase().contains("anime")) {
			return MediaType.ANIME
		}

		return null
	}

	private fun resultSections(content: DocElement): Map<MediaType, DocElement> {
		var lastMediaType: MediaType? = null
		val searchSections: MutableMap<MediaType, DocElement> = mutableMapOf()

		for (child in content.children) {
			if (child.tagName == "h2") {
				lastMediaType = resolveResultType(child.text)
			} else if (lastMediaType != null && child.tagName == "table") {
				searchSections[lastMediaType] = child
			}
		}

		return searchSections
	}

	private fun DocElement.buildSearchResult(type: MediaType): Media? =
		try {
			val link = findFirst("h4 a")
			val href = context.getFullUrl(link.attribute("href"))

			Media(
				title = link.text,
				type = type,
				url = href
			)
		} catch (exception: ElementNotFoundException) {
			null
		}

	private fun itemsFromSection(section: DocElement, type: MediaType): List<Media> =
		try {
			section.findAll(".row > div:first-child").mapNotNull { row -> row.buildSearchResult(type) }
		} catch (exception: ElementNotFoundException) {
			listOf()
		}

	override fun extract(document: Doc): List<Media> {
		val results: MutableList<Media> = mutableListOf()
		val content = document.findSecondOrNull(".single_post_content")

		if (content != null) {
			val sections = resultSections(content)
			sections.forEach { results.addAll(itemsFromSection(it.value, it.key)) }
		}

		return results
	}
}
