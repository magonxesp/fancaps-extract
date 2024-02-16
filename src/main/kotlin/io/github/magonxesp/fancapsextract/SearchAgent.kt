package io.github.magonxesp.fancapsextract

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import java.net.URLEncoder

object SearchAgent {

	private fun MutableMap<String, String>.buildQueryString() =
		entries.joinToString("&") { "${it.key}=${it.value}" }

	fun search(name: String, types: List<SearchType>): List<SearchResult> {
		val encodedName = URLEncoder.encode(name, "utf-8")
		val query = mutableMapOf(
			"q" to encodedName,
			"submit" to "Submit+Query"
		)

		types.forEach {
			query[it.parameter] = it.value
		}

		return skrape(HttpFetcher) {
			request {
				url = Context.getFullUrl("/search.php?${query.buildQueryString()}")
				userAgent = Context.userAgent
			}
			response {
				htmlDocument {
					SearchExtractor.extract(this)
				}
			}
		}
	}
}
