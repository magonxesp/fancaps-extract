package io.github.magonxesp.fancapsextract.agent

import io.github.magonxesp.fancapsextract.*
import io.github.magonxesp.fancapsextract.exception.MediaNotSupportedException
import io.github.magonxesp.fancapsextract.extractor.SeriesEpisodeExtractor
import io.github.magonxesp.fancapsextract.extractor.SeriesEpisodeImagesExtractor
import kotlinx.coroutines.flow.*

class SeriesImageAgent(context: Context) : Agent(context) {

	fun getEpisodes(media: Media, page: Page = Page(1)): List<SeriesEpisode> {
		if (!media.isSeries()) {
			throw MediaNotSupportedException("The media should be series")
		}

		val query = media.url.parseQueryString()
		query["page"] = page.value.toString()

		return extractWithGetRequest(SeriesEpisodeExtractor(context), media.url.setQueryStringToUrl(query))
	}

	fun getAllEpisodes(media: Media): Flow<SeriesEpisode> = flow {
		val page = Page(1)

		while (true) {
			val episodes = getEpisodes(media, page)

			if (episodes.isEmpty()) {
				break
			}

			for (episode in episodes) {
				emit(episode)
			}

			page.next()
		}
	}

	fun getEpisodeImages(episode: SeriesEpisode, page: Page = Page(1)): SeriesEpisodeImages? {
		val query = episode.url.parseQueryString()
		query["page"] = page.value.toString()

		return extractWithGetRequest(
			extractor = SeriesEpisodeImagesExtractor(context),
			endpoint = episode.url.setQueryStringToUrl(query)
		)
	}

	fun getEpisodeTopImages(episode: SeriesEpisode): List<Picture> {
		val episodeImages = getEpisodeImages(episode)
		return episodeImages?.topImages ?: listOf()
	}

	fun getEpisodeAllImages(episode: SeriesEpisode): Flow<Picture> = flow {
		val page = Page(1)

		while (true) {
			val episodeImages = getEpisodeImages(episode, page)

			if (episodeImages == null || episodeImages.images.isEmpty()) {
				break
			}

			episodeImages.images.forEach { emit(it) }
			page.next()
		}
	}

	fun getAllEpisodesAllImages(media: Media): Flow<Picture> = flow {
		getAllEpisodes(media).map { it.images }.collect { pictures ->
			for (picture in pictures) {
				emit(picture)
			}
		}
	}

}
