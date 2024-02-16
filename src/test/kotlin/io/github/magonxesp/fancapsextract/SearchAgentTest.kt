package io.github.magonxesp.fancapsextract

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual

class SearchAgentTest : ShouldSpec({

	should("extract the results scrapping FanCap search results") {
		val expected = listOf(
			SearchResult(title = "Non Non Biyori", url = "https://fancaps.net/anime/showimages.php?10434-Non_Non_Biyori", type = SearchType.ANIME),
			SearchResult(title = "Non Non Biyori Repeat", url = "https://fancaps.net/anime/showimages.php?34699-Non_Non_Biyori_Repeat", type = SearchType.ANIME),
		)

		val result = SearchAgent.search("Non Non Biyori", listOf(SearchType.ANIME))

		result shouldBeEqual expected
	}

})
