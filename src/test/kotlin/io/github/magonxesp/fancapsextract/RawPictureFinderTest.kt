package io.github.magonxesp.fancapsextract

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

@Deprecated(message = "This test will be deleted")
class RawPictureFinderTest : ShouldSpec({
	val finder = RawPictureFinder(DefaultContext)

	xshould("findSeriesTopImagesByName should search top images by series name") {
		val result = finder.findSeriesTopImagesByName("Non non biyori").toList()

		result.isNotEmpty() shouldBeEqual true
	}

	xshould("findAllSeriesImagesByName should search top images by series name") {
		val result = finder.findAllSeriesImagesByName("Non non biyori").take(10).toList()

		result.isNotEmpty() shouldBeEqual true
	}

	xshould("findSeriesImagesByName should search top images by series name") {
		val result = finder.findSeriesImagesByName("Non non biyori").take(10).toList()

		result.isNotEmpty() shouldBeEqual true
	}

	xshould("findMovieImagesByName should search images by movie name") {
		val result = finder.findMovieImagesByName("A silent voice").take(10).toList()

		result.isNotEmpty() shouldBeEqual true
	}

	xshould("findAllMovieImagesByName should search all images by movie name") {
		val result = finder.findAllMovieImagesByName("A silent voice").take(10).toList()

		result.isNotEmpty() shouldBeEqual true
	}
})
