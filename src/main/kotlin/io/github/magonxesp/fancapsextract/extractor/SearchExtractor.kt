package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.*
import it.skrape.selects.Doc
import it.skrape.selects.DocElement
import it.skrape.selects.ElementNotFoundException

class SearchExtractor(private val context: Context) : Extractor<List<Media>> {
	private fun String.resolveMediaTypeByUrl(): MediaType? {
		if (contains("/movies")) {
			return MediaType.MOVIES
		}

		if (contains("/tv")) {
			return MediaType.TV
		}

		if (contains("/anime")) {
			return MediaType.ANIME
		}

		return null
	}

	private fun String.resolveRawImageUrl(): String? {
		if (contains("moviethumbs")) {
			return "/file/fancaps-movieimages/${getImageIdFromUrl()}.jpg"
		}

		if (contains("tvthumbs")) {
			return "/file/fancaps-tvimages/${getImageIdFromUrl()}.jpg"
		}

		if (contains("/animethumbs")) {
			return "/file/fancaps-animeimages/${getImageIdFromUrl()}.jpg"
		}

		return null
	}

	private fun String.resolvePictureUrl(): String? {
		if (contains("moviethumbs")) {
			return "/movies/Image.php?imageid=${getImageIdFromUrl()}"
		}

		if (contains("tvthumbs")) {
			return "/tv/picture.php?/${getImageIdFromUrl()}"
		}

		if (contains("/animethumbs")) {
			return "/anime/picture.php?/${getImageIdFromUrl()}"
		}

		return null
	}

	private fun DocElement.extractPreview(): List<Picture> {
		try {
			return findAll("a img").mapNotNull {
				val src = it.attribute("src")

				Picture(
					url = context.getFullUrl(src.resolvePictureUrl() ?: return@mapNotNull null),
					rawUrl = src.resolveRawImageUrl()?.let { context.getCdnFullUrl(it) },
				)
			}
		} catch (exception: ElementNotFoundException) {
			return listOf()
		}
	}

	private fun DocElement.buildSearchResult(): Media? {
		try {
			val link = findFirst("h4 a")
			val href = context.getFullUrl(link.attribute("href"))
			val type = href.resolveMediaTypeByUrl() ?: return null

			return Media(
				title = link.text,
				type = type,
				url = href,
				preview = extractPreview()
			)
		} catch (exception: ElementNotFoundException) {
			return null
		}
	}

	override fun extract(document: Doc): List<Media> {
		val content = document.findSecondOrNull(".single_post_content")

		if (content != null) {
			val searchResults = content.findAll(".row")
			return searchResults.mapNotNull { it.buildSearchResult() }
		}

		return listOf()
	}
}
