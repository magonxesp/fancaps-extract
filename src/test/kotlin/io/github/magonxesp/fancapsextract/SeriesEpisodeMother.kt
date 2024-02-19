package io.github.magonxesp.fancapsextract

object SeriesEpisodeMother {
	fun existingEpisode() = SeriesEpisode(
		episodeNumber = 1,
		images = listOf(
			Picture(url = "https://fancaps.net/anime/picture.php?/7123823"),
			Picture(url = "https://fancaps.net/anime/picture.php?/7124363"),
			Picture(url = "https://fancaps.net/anime/picture.php?/7124016"),
			Picture(url = "https://fancaps.net/anime/picture.php?/7123748"),
		),
		url = "https://fancaps.net/anime/episodeimages.php?10435-Non_Non_Biyori/Episode_1"
	)

	fun existingEpisodes() = listOf(
		SeriesEpisode(
			episodeNumber = 1,
			images = listOf(
				Picture(url = "https://fancaps.net/anime/picture.php?/7123823"),
				Picture(url = "https://fancaps.net/anime/picture.php?/7124363"),
				Picture(url = "https://fancaps.net/anime/picture.php?/7124016"),
				Picture(url = "https://fancaps.net/anime/picture.php?/7123748"),
			),
			url = "https://fancaps.net/anime/episodeimages.php?10435-Non_Non_Biyori/Episode_1"
		),
		SeriesEpisode(
			episodeNumber = 2,
			images = listOf(
				Picture(url = "https://fancaps.net/anime/picture.php?/7124451"),
				Picture(url = "https://fancaps.net/anime/picture.php?/7124450"),
				Picture(url = "https://fancaps.net/anime/picture.php?/7124455"),
				Picture(url = "https://fancaps.net/anime/picture.php?/7124462"),
			),
			url = "https://fancaps.net/anime/episodeimages.php?10436-Non_Non_Biyori/Episode_2"
		)
	)
}
