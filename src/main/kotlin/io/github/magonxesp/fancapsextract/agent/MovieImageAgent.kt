package io.github.magonxesp.fancapsextract.agent

import io.github.magonxesp.fancapsextract.*
import io.github.magonxesp.fancapsextract.exception.MediaNotSupportedException
import io.github.magonxesp.fancapsextract.extractor.MovieImagesExtractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieImageAgent(context: Context) : Agent(context) {

	fun getImages(media: Media, page: Page = Page(1)): List<Picture> {
		if (media.type != MediaType.MOVIES) {
			throw MediaNotSupportedException("The media should be movie")
		}

		val query = media.url.parseQueryString()
		query["page"] = page.value.toString()

		return extractWithGetRequest(MovieImagesExtractor(context), media.url.setQueryStringToUrl(query))
	}

	fun getAllImages(media: Media): Flow<Picture> = flow {
		val page = Page(1)
		var lastSize: Int? = null

		while (true) {
			val images = getImages(media, page)

			if (images.isEmpty()) {
				break
			}

			for (image in images) {
				emit(image)
			}

			if (lastSize != null && images.size < lastSize) {
				break
			}

			lastSize = images.size
			page.next()
		}
	}

}
