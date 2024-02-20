package io.github.magonxesp.fancapsextract

import io.github.magonxesp.fancapsextract.agent.ImageAgent
import io.github.magonxesp.fancapsextract.agent.SearchAgent
import io.github.magonxesp.fancapsextract.agent.SeriesImageAgent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class RawPictureFinder(context: Context) {
	private val searchAgent = SearchAgent(context)
	private val seriesImagesAgent = SeriesImageAgent(context)
	private val imageAgent = ImageAgent(context)

	private suspend fun findAllSeriesImages(media: Media): Flow<RawPicture> = flow {
		seriesImagesAgent.getAllEpisodes(media).collect { episode ->
			seriesImagesAgent.getEpisodeAllImages(episode).collect { picture ->
				imageAgent.getRawPicture(picture)?.also { rawPicture -> emit(rawPicture) }
			}
		}
	}

	private suspend fun findSeriesImages(media: Media, page: Page = Page(1)): Flow<RawPicture> = flow {
		seriesImagesAgent.getAllEpisodes(media).collect { episode ->
			val episodeImages = seriesImagesAgent.getEpisodeImages(episode, page)

			(episodeImages?.images ?: listOf()).forEach { picture ->
				imageAgent.getRawPicture(picture)?.also { rawPicture -> emit(rawPicture) }
			}
		}
	}

	private suspend fun findSeriesTopImages(media: Media): Flow<RawPicture> = flow {
		seriesImagesAgent.getAllEpisodes(media).collect { episode ->
			seriesImagesAgent.getEpisodeTopImages(episode).forEach { picture ->
				imageAgent.getRawPicture(picture)?.also { rawPicture -> emit(rawPicture) }
			}
		}
	}

	suspend fun findSeriesTopImagesByName(name: String): Flow<RawPicture> = flow {
		searchAgent.search(name, listOf(MediaType.ANIME, MediaType.TV)).forEach { media ->
			emitAll(findSeriesTopImages(media))
		}
	}

	suspend fun findAllSeriesImagesByName(name: String): Flow<RawPicture> = flow {
		searchAgent.search(name, listOf(MediaType.ANIME, MediaType.TV)).forEach { media ->
			emitAll(findAllSeriesImages(media))
		}
	}

	suspend fun findSeriesImagesByName(name: String, page: Page = Page(1)): Flow<RawPicture> = flow {
		searchAgent.search(name, listOf(MediaType.ANIME, MediaType.TV)).forEach { media ->
			emitAll(findSeriesImages(media, page))
		}
	}
}
