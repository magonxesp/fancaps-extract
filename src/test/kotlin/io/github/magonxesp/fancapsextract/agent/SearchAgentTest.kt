package io.github.magonxesp.fancapsextract.agent

import io.github.magonxesp.fancapsextract.DefaultContext
import io.github.magonxesp.fancapsextract.Media
import io.github.magonxesp.fancapsextract.MediaMother
import io.github.magonxesp.fancapsextract.MediaType
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual

class SearchAgentTest : ShouldSpec({

	should("extract the results scrapping FanCap search results") {
		val expected = MediaMother.existing()

		val result = SearchAgent(DefaultContext).search("Non Non Biyori", listOf(MediaType.ANIME))

		result shouldBeEqual expected
	}

})
