package io.github.magonxesp.fancapsextract.agent

import io.github.magonxesp.fancapsextract.*
import io.github.magonxesp.fancapsextract.exception.MediaNotSupportedException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe

class SeriesImageAgentTest : ShouldSpec({

	should("getEpisodesImages should search only for series") {
		val media = Media(
			title = "Non Non Biyori",
			type = MediaType.MOVIES,
			url = "https://fancaps.net/anime/showimages.php?10434-Non_Non_Biyori"
		)

		val agent = SeriesImageAgent(DefaultContext)

		shouldThrow<MediaNotSupportedException> {
			agent.getEpisodes(media)
		}
	}

	should("getEpisodesImages should search episodes images") {
		val media = Media(
			title = "Non Non Biyori",
			type = MediaType.ANIME,
			url = "https://fancaps.net/anime/showimages.php?10434-Non_Non_Biyori"
		)
		val episodes = SeriesEpisodeMother.existingEpisodes()

		val agent = SeriesImageAgent(DefaultContext)
		val extracted = agent.getEpisodes(media)

		extracted[0] shouldBeEqual episodes[0]
		extracted[1] shouldBeEqual episodes[1]
	}

	should("getEpisodesImages should return empty list if not found results") {
		val media = Media(
			title = "Non Non Biyori",
			type = MediaType.ANIME,
			url = "https://fancaps.net/anime/showimages.php?10434-Non_Non_Biyori"
		)

		val agent = SeriesImageAgent(DefaultContext)
		val extracted = agent.getEpisodes(media, Page(40))

		extracted.isEmpty() shouldBeEqual true
	}

	should("getEpisodeImages should return episode images") {
		val episode = SeriesEpisodeMother.existingEpisode()
		val episodeImages = SeriesEpisodeImagesMother.existingEpisode()
		val agent = SeriesImageAgent(DefaultContext)
		val extracted = agent.getEpisodeImages(episode)

		extracted shouldBe episodeImages
	}

	should("getEpisodeImages should return null if there is no images") {
		val episode = SeriesEpisodeMother.existingEpisode()
		val agent = SeriesImageAgent(DefaultContext)

		// FanCaps always returns the top images when it not found more
		val extracted = agent.getEpisodeImages(episode, Page(1000))

		extracted?.topImages?.isEmpty() shouldBe false
		extracted?.images?.isEmpty() shouldBe true
	}

})
