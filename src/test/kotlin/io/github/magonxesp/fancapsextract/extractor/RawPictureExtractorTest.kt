package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.RawPictureMother
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import it.skrape.core.htmlDocument

class RawPictureExtractorTest : ShouldSpec({
	should("extract should get the full size picture file url") {
		val picture = RawPictureMother.existing()

		val html = this::class.java.getResource("/non-non-biyori-picture.html")!!.readText()
		val document = htmlDocument(html)
		val extracted = RawPictureExtractor().extract(document)

		extracted shouldBe picture
	}
})
