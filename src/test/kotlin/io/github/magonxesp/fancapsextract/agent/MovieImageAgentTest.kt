package io.github.magonxesp.fancapsextract.agent

import io.github.magonxesp.fancapsextract.DefaultContext
import io.github.magonxesp.fancapsextract.Media
import io.github.magonxesp.fancapsextract.MediaType
import io.github.magonxesp.fancapsextract.PictureMother
import io.github.magonxesp.fancapsextract.exception.MediaNotSupportedException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContainAnyOf
import kotlinx.coroutines.flow.toList

class MovieImageAgentTest : ShouldSpec({
	val agent = MovieImageAgent(DefaultContext)

	val media = Media(
		title = "A Silent Voice: The Movie (2016)",
		url = "https://fancaps.net/movies/MovieImages.php?name=A_Silent_Voice_The_Movie_2016&movieid=2040",
		type = MediaType.MOVIES,
	)

	should("getImages should don't get images from a media is not a movie") {
		val notMovieMedia = Media(
			title = "A Silent Voice: The Movie (2016)",
			url = "https://fancaps.net/movies/MovieImages.php?name=A_Silent_Voice_The_Movie_2016&movieid=2040",
			type = MediaType.ANIME,
		)

		shouldThrow<MediaNotSupportedException> {
			agent.getImages(notMovieMedia)
		}
	}

	should("getImages should get images from a movie") {
		val pictures = PictureMother.aSilentVoicePictures()
		val extracted = agent.getImages(media)

		extracted shouldContainAnyOf pictures
	}

	should("getImages should get all images from a movie") {
		val pictures = PictureMother.aSilentVoicePictures()
		val extracted = agent.getAllImages(media).toList()

		extracted shouldContainAnyOf pictures
	}
})
