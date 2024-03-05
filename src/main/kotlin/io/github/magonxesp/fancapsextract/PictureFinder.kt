package io.github.magonxesp.fancapsextract

import io.github.magonxesp.fancapsextract.agent.MovieImageAgent
import io.github.magonxesp.fancapsextract.agent.SearchAgent
import io.github.magonxesp.fancapsextract.agent.SeriesImageAgent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take

/**
 * The Picture finder
 */
class PictureFinder(context: Context) {
	private val searchAgent = SearchAgent(context)
	private val seriesImagesAgent = SeriesImageAgent(context)
	private val movieImageAgent = MovieImageAgent(context)

	suspend fun findSeriesTopImagesByName(name: String): Flow<Picture> = flow {
		searchAgent.search(name, listOf(MediaType.ANIME, MediaType.TV)).forEach { media ->
			seriesImagesAgent.getAllEpisodes(media).collect { episode ->
				episode.images.forEach { emit(it) }
			}
		}
	}

	suspend fun findSeriesFirstEpisodeTopImagesByName(name: String): Flow<Picture> = flow {
		searchAgent.search(name, listOf(MediaType.ANIME, MediaType.TV)).forEach { media ->
			seriesImagesAgent.getAllEpisodes(media).take(1).collect { episode ->
				episode.images.forEach { emit(it) }
			}
		}
	}

	suspend fun findAllSeriesImagesByName(name: String): Flow<Picture> = flow {
		searchAgent.search(name, listOf(MediaType.ANIME, MediaType.TV)).forEach { media ->
			seriesImagesAgent.getAllEpisodes(media).collect { episode ->
				seriesImagesAgent.getEpisodeAllImages(episode).collect { picture ->
					emit(picture)
				}
			}
		}
	}

	suspend fun findSeriesImagesByName(name: String, page: Page = Page(1)): Flow<Picture> = flow {
		searchAgent.search(name, listOf(MediaType.ANIME, MediaType.TV)).forEach { media ->
			seriesImagesAgent.getAllEpisodes(media).collect { episode ->
				val episodeImages = seriesImagesAgent.getEpisodeImages(episode, page)

				(episodeImages?.images ?: listOf()).forEach { picture ->
					emit(picture)
				}
			}
		}
	}

	suspend fun findMovieImagesByName(name: String, page: Page = Page(1)): Flow<Picture> = flow {
		searchAgent.search(name, listOf(MediaType.MOVIES)).forEach { media ->
			movieImageAgent.getImages(media, page).forEach { picture ->
				emit(picture)
			}
		}
	}

	suspend fun findAllMovieImagesByName(name: String): Flow<Picture> = flow {
		searchAgent.search(name, listOf(MediaType.MOVIES)).forEach { media ->
			movieImageAgent.getAllImages(media).collect { picture ->
				emit(picture)
			}
		}
	}
}
