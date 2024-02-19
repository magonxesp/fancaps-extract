package io.github.magonxesp.fancapsextract.agent

import io.github.magonxesp.fancapsextract.Context
import io.github.magonxesp.fancapsextract.Media
import io.github.magonxesp.fancapsextract.MediaType
import io.github.magonxesp.fancapsextract.extractor.SearchExtractor
import java.net.URLEncoder

class SearchAgent(context: Context) : Agent(context) {
	fun search(name: String, types: List<MediaType>): List<Media> {
		val encodedName = URLEncoder.encode(name, "utf-8")
		val query = mutableMapOf(
			"q" to encodedName,
			"submit" to "Submit+Query"
		)

		types.forEach {
			query[it.parameter] = it.value
		}

		return extractWithGetRequest(SearchExtractor(context), "/search.php?${query.buildQueryString()}")
	}
}
