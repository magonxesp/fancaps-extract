package io.github.magonxesp.fancapsextract

data class Media(
	val title: String,
	val type: MediaType,
	val url: String,
	val preview: List<Picture> = listOf()
) {
	fun isSeries() = type == MediaType.TV || type == MediaType.ANIME
}
