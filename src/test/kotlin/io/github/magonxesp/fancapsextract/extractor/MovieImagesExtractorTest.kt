package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.DefaultContext
import io.github.magonxesp.fancapsextract.PictureMother
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContainAnyOf
import it.skrape.core.htmlDocument

class MovieImagesExtractorTest : ShouldSpec({

	should("extract pictures of movie media") {
		val pictures = PictureMother.aSilentVoicePictures()

		val html = this::class.java.getResource("/a-silent-voice-images.html")!!.readText()
		val document = htmlDocument(html)
		val extracted = MovieImagesExtractor(DefaultContext).extract(document)

		extracted shouldContainAnyOf pictures
	}

})
