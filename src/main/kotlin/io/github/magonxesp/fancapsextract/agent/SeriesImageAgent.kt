package io.github.magonxesp.fancapsextract.agent

import io.github.magonxesp.fancapsextract.*
import io.github.magonxesp.fancapsextract.exception.MediaNotSupportedException
import io.github.magonxesp.fancapsextract.extractor.SeriesEpisodeExtractor
import io.github.magonxesp.fancapsextract.extractor.SeriesEpisodeImagesExtractor

class SeriesImageAgent(context: Context) : Agent(context) {

	fun getEpisodesImages(media: Media, page: Page = Page(1)): List<SeriesEpisode> {
		if (!media.isSeries()) {
			throw MediaNotSupportedException("The media should be series")
		}

		val query = media.url.parseQueryString()
		query["page"] = page.value.toString()

		return extractWithGetRequest(SeriesEpisodeExtractor(), media.url.setQueryStringToUrl(query))
	}

	fun getEpisodeImages(episode: SeriesEpisode, page: Page = Page(1)): SeriesEpisodeImages {
		val query = episode.url.parseQueryString()
		query["page"] = page.value.toString()

		return extractWithGetRequest(
			extractor = SeriesEpisodeImagesExtractor(),
			endpoint = episode.url.setQueryStringToUrl(query)
		)
	}

}
