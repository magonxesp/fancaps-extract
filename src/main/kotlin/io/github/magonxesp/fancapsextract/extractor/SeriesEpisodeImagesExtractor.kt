package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.*
import it.skrape.selects.Doc
import it.skrape.selects.ElementNotFoundException

class SeriesEpisodeImagesExtractor(private val context: Context) : Extractor<SeriesEpisodeImages?> {
	private fun extractTopImages(document: Doc): List<Picture> =
		document.findAllOrEmpty(".row.topImages a").map {
			Picture(
				url = it.attribute("href"),
				rawUrl = it.attribute("href").resolveRawImageUrlFromUrl(context)
			)
		}

	private fun extractImages(document: Doc): List<Picture> =
		document.findAllOrEmpty(".row .col-lg-3.col-md-4.col-sm-6.col-xs-12 a").map {
			Picture(
				url = it.attribute("href"),
				rawUrl = it.attribute("href").resolveRawImageUrlFromUrl(context)
			)
		}

	override fun extract(document: Doc): SeriesEpisodeImages? {
		val topImages = extractTopImages(document)
		val images = extractImages(document)

		if (topImages.isEmpty() && images.isEmpty()) {
			return null
		}

		return SeriesEpisodeImages(
			topImages = topImages,
			images = images
		)
	}
}
