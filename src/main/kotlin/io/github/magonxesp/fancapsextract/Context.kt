package io.github.magonxesp.fancapsextract

abstract class Context {
	val baseUrl = "https://fancaps.net"
	val cdnBaseUrl = "https://cdni.fancaps.net"
	open val userAgent = "FanCapExtractLib/0.0.0"

	/**
	 * Get the full url including the protocol and domain.
	 */
	fun getFullUrl(url: String): String {
		if (url.startsWith("$baseUrl/")) {
			return url
		}

		return "$baseUrl/${url.removePrefix("/")}"
	}

	/**
	 * Get the full url including the protocol and domain.
	 */
	fun getCdnFullUrl(url: String): String {
		if (url.startsWith("$cdnBaseUrl/")) {
			return url
		}

		return "$cdnBaseUrl/${url.removePrefix("/")}"
	}
}

object DefaultContext : Context()
