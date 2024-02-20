package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.SeriesEpisodeImagesMother
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import it.skrape.core.htmlDocument

class SeriesEpisodeImagesExtractorTest : ShouldSpec({
	should("extract all images of an episode") {
		val episodeImages = SeriesEpisodeImagesMother.existingEpisode()

		val html = this::class.java.getResource("/non-non-biyori-episode-images.html")!!.readText()
		val document = htmlDocument(html)
		val extracted = SeriesEpisodeImagesExtractor().extract(document)

		episodeImages shouldBe extracted
	}

})
