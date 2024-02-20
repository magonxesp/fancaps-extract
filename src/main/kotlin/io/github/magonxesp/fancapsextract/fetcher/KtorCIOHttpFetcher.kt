package io.github.magonxesp.fancapsextract.fetcher

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import it.skrape.fetcher.BlockingFetcher
import it.skrape.fetcher.Request
import kotlinx.coroutines.runBlocking
import it.skrape.fetcher.Result

object KtorCIOHttpFetcher : BlockingFetcher<Request> {

	override val requestBuilder: Request get() = Request()

	override fun fetch(request: Request): Result = runBlocking{
		val response = configuredClient(request)

		Result(
			responseBody = response.bodyAsText(),
			responseStatus = Result.Status(
				code = response.status.value,
				message = response.status.description
			),
			baseUri = response.request.url.toString(),
			cookies = listOf(),
			headers = response.headers.toMap().mapValues { it.value.first() },
			contentType = response.headers["Content-Type"]
		)
	}

	@Suppress("MagicNumber")
	private suspend fun configuredClient(request: Request): HttpResponse =
		HttpClient(CIO).request(request.url) {
			userAgent(request.userAgent)

			if (request.body != null) {
				// TODO: configure method and request body
				contentType(ContentType.parse(request.headers["Content-Type"]!!))
				setBody(request.body)
			}
		}

}
