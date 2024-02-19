package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.DefaultContext
import io.github.magonxesp.fancapsextract.Media
import io.github.magonxesp.fancapsextract.MediaMother
import io.github.magonxesp.fancapsextract.MediaType
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import it.skrape.core.htmlDocument

class SearchExtractorTest : ShouldSpec({

	should("extract the results from the html") {
		val expected = MediaMother.existing()

		val html = this::class.java.getResource("/non-non-biyori-search-result.html")!!.readText()
		val document = htmlDocument(html)
		val result = SearchExtractor(DefaultContext).extract(document)

		result shouldBeEqual expected
	}

})
