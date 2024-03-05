package io.github.magonxesp.fancapsextract

fun String.getImageIdFromUrl(): String =
	Regex("([0-9]+)").findAll(this).first().groupValues[1]

fun String.getMediaTypeFromUrl(): String =
	Regex("(tv|anime|movies)").findAll(this).first().groupValues[1]

fun String.resolveRawImageUrlFromUrl(context: Context): String? =
	try {
		when (this.getMediaTypeFromUrl()) {
			"anime" -> context.getCdnFullUrl("/file/fancaps-animeimages/${getImageIdFromUrl()}.jpg")
			"tv" -> context.getCdnFullUrl("/file/fancaps-tvimages/${getImageIdFromUrl()}.jpg")
			"movies" -> context.getCdnFullUrl("/file/fancaps-movieimages/${getImageIdFromUrl()}.jpg")
			else -> null
		}
	} catch (exception: Exception) {
		null
	}
