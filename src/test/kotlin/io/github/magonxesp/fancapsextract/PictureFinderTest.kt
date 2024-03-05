package io.github.magonxesp.fancapsextract

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

class PictureFinderTest : ShouldSpec({
	val finder = PictureFinder(DefaultContext)

	should("findSeriesTopImagesByName should search top images by series name") {
		val result = finder.findSeriesTopImagesByName("Non non biyori").toList()

		result.isNotEmpty() shouldBeEqual true
	}

	should("findAllSeriesImagesByName should search top images by series name") {
		val result = finder.findAllSeriesImagesByName("Non non biyori").take(10).toList()

		result.isNotEmpty() shouldBeEqual true
	}

	should("findSeriesImagesByName should search top images by series name") {
		val result = finder.findSeriesImagesByName("Non non biyori").take(10).toList()

		result.isNotEmpty() shouldBeEqual true
	}

	should("findMovieImagesByName should search images by movie name") {
		val result = finder.findMovieImagesByName("A silent voice").take(10).toList()

		result.isNotEmpty() shouldBeEqual true
	}

	should("findAllMovieImagesByName should search all images by movie name") {
		val result = finder.findAllMovieImagesByName("A silent voice").take(10).toList()

		result.isNotEmpty() shouldBeEqual true
	}

	should("findSeriesFirstEpisodeTopImagesByName should search all top images of first episode by series name") {
		val result = finder.findSeriesFirstEpisodeTopImagesByName("Non non biyori").toList()

		result.isNotEmpty() shouldBeEqual true
	}
})
