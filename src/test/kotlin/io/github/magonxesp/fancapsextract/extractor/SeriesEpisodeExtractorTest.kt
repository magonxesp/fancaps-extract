package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.DefaultContext
import io.github.magonxesp.fancapsextract.SeriesEpisodeMother
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import it.skrape.core.htmlDocument

class SeriesEpisodeExtractorTest : ShouldSpec({
	should("extract all episodes images") {
		val episodes = SeriesEpisodeMother.existingEpisodes()

		val html = this::class.java.getResource("/non-non-biyori-episodes.html")!!.readText()
		val document = htmlDocument(html)
		val extracted = SeriesEpisodeExtractor(DefaultContext).extract(document)

		extracted[0] shouldBeEqual episodes[0]
		extracted[1] shouldBeEqual episodes[1]
	}

})
