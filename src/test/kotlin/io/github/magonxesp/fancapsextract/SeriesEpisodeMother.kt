package io.github.magonxesp.fancapsextract

object SeriesEpisodeMother {
	fun existingEpisode() = SeriesEpisode(
		episodeNumber = 1,
		images = listOf(
			Picture(
				url = "https://fancaps.net/anime/picture.php?/7123823",
				rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7123823.jpg"
			),
			Picture(
				url = "https://fancaps.net/anime/picture.php?/7124363",
				rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7124363.jpg"
			),
			Picture(
				url = "https://fancaps.net/anime/picture.php?/7124016",
				rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7124016.jpg"
			),
			Picture(
				url = "https://fancaps.net/anime/picture.php?/7123748",
				rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7123748.jpg"
			),
		),
		url = "https://fancaps.net/anime/episodeimages.php?10435-Non_Non_Biyori/Episode_1"
	)

	fun existingEpisodes() = listOf(
		SeriesEpisode(
			episodeNumber = 1,
			images = listOf(
				Picture(
					url = "https://fancaps.net/anime/picture.php?/7123823",
					rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7123823.jpg"
				),
				Picture(
					url = "https://fancaps.net/anime/picture.php?/7124363",
					rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7124363.jpg"
				),
				Picture(
					url = "https://fancaps.net/anime/picture.php?/7124016",
					rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7124016.jpg"
				),
				Picture(
					url = "https://fancaps.net/anime/picture.php?/7123748",
					rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7123748.jpg"
				),
			),
			url = "https://fancaps.net/anime/episodeimages.php?10435-Non_Non_Biyori/Episode_1"
		),
		SeriesEpisode(
			episodeNumber = 2,
			images = listOf(
				Picture(
					url = "https://fancaps.net/anime/picture.php?/7124451",
					rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7124451.jpg"
				),
				Picture(
					url = "https://fancaps.net/anime/picture.php?/7124450",
					rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7124450.jpg"
				),
				Picture(
					url = "https://fancaps.net/anime/picture.php?/7124455",
					rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7124455.jpg"
				),
				Picture(
					url = "https://fancaps.net/anime/picture.php?/7124462",
					rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7124462.jpg"
				),
			),
			url = "https://fancaps.net/anime/episodeimages.php?10436-Non_Non_Biyori/Episode_2"
		)
	)
}
