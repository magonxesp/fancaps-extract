package io.github.magonxesp.fancapsextract

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*

class PictureTest : ShouldSpec({
	val httpClient = HttpClient(CIO)

	should("rawUrl of a movie should return a image") {
		val pictures = PictureMother.aSilentVoicePictures()

		for (picture in pictures) {
			val response = httpClient.get(picture.rawUrl!!)
			response.contentType() shouldBe ContentType.Image.JPEG
		}
	}

	should("rawUrl of a anime should return a image") {
		val pictures = PictureMother.nonNonBiyoriFirstEpisodeTopPictures()

		for (picture in pictures) {
			val response = httpClient.get(picture.rawUrl!!)
			response.contentType() shouldBe ContentType.Image.JPEG
		}
	}

})
