package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.*
import it.skrape.selects.Doc
import it.skrape.selects.ElementNotFoundException

class SeriesEpisodeExtractor(private val context: Context) : Extractor<List<SeriesEpisode>> {

	override fun extract(document: Doc): List<SeriesEpisode> {
		val allImages = document.findAllOrEmpty("#contentbody .col-lg-9.col-md-8.col-sm-8.col-xs-12 .col-lg-3.col-md-4.col-sm-6.col-xs-6")
		val allEpisodesLinks = document.findAllOrEmpty("#contentbody .col-lg-9.col-md-8.col-sm-8.col-xs-12 .row a.btn.btn-block")
		val imagesPerEpisode = allImages.chunked(4)
		val episodes = mutableListOf<SeriesEpisode>()

		for ((index, episode) in imagesPerEpisode.withIndex()) {
			try {
				val images = episode.map {
					val href = it.findFirst("a").attribute("href")

					Picture(
						url = href,
						rawUrl = href.resolveRawImageUrlFromUrl(context)
					)
				}

				val link = allEpisodesLinks[index]

				episodes.add(SeriesEpisode(
					episodeNumber = index + 1,
					images = images,
					url = link.attribute("href")
				))
			} catch (_: ElementNotFoundException) {

			}
		}

		return episodes
	}
}
