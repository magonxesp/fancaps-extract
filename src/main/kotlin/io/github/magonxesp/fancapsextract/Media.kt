package io.github.magonxesp.fancapsextract

data class Media(
	val title: String,
	val type: MediaType,
	val url: String
) {
	fun isSeries() = type == MediaType.TV || type == MediaType.ANIME
}
