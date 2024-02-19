package io.github.magonxesp.fancapsextract

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import it.skrape.core.htmlDocument

class SearchExtractorTest : ShouldSpec({

	should("extract the results from the html") {
		val expected = listOf(
			SearchResult(title = "Non Non Biyori", url = "https://fancaps.net/anime/showimages.php?10434-Non_Non_Biyori", type = SearchType.ANIME),
			SearchResult(title = "Non Non Biyori Repeat", url = "https://fancaps.net/anime/showimages.php?34699-Non_Non_Biyori_Repeat", type = SearchType.ANIME),
		)

		val html = this::class.java.getResource("/non-non-biyori-search-result.html")!!.readText()
		val document = htmlDocument(html)
		val result = SearchExtractor(DefaultContext).extract(document)

		result shouldBeEqual expected
	}

})
