package io.github.magonxesp.fancapsextract.agent

import io.github.magonxesp.fancapsextract.Context
import io.github.magonxesp.fancapsextract.extractor.Extractor
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape

abstract class Agent(protected val context: Context) {
	protected fun MutableMap<String, String>.buildQueryString() =
		entries.joinToString("&") { if (it.key != it.value) "${it.key}=${it.value}" else it.value }

	protected fun String.parseQueryString(): MutableMap<String, String> {
		val query = mutableMapOf<String, String>()

		if (!contains("?")) {
			return query
		}

		substring(indexOf("?") + 1).split("&").forEach {
			if (it.contains("=")) {
				val keyValue = it.split("=")
				query[keyValue[0]] = keyValue[1]
			} else {
				query[it] = it
			}
		}

		return query
	}

	protected fun String.setQueryStringToUrl(query: Map<String, String>) =
		if (contains("?")) {
			substring(0, indexOf("?")) + "?" + query.toMutableMap().buildQueryString()
		} else {
			this + "?" + query.toMutableMap().buildQueryString()
		}

	protected fun <T> extractWithGetRequest(extractor: Extractor<T>, endpoint: String): T {
		return skrape(HttpFetcher) {
			request {
				url = context.getFullUrl(endpoint)
				userAgent = context.userAgent
			}
			response {
				htmlDocument {
					extractor.extract(this)
				}
			}
		}
	}
}
