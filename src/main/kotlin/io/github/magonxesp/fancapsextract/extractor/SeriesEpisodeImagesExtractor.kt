package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.Picture
import io.github.magonxesp.fancapsextract.SeriesEpisode
import io.github.magonxesp.fancapsextract.SeriesEpisodeImages
import io.github.magonxesp.fancapsextract.findAllOrEmpty
import it.skrape.selects.Doc
import it.skrape.selects.ElementNotFoundException

class SeriesEpisodeImagesExtractor : Extractor<SeriesEpisodeImages?> {
	private fun extractTopImages(document: Doc): List<Picture> =
		document.findAllOrEmpty(".row.topImages a").map {
			Picture(url = it.attribute("href"))
		}

	private fun extractImages(document: Doc): List<Picture> =
		document.findAllOrEmpty(".row .col-lg-3.col-md-4.col-sm-6.col-xs-12 a").map {
			Picture(url = it.attribute("href"))
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
