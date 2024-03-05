package io.github.magonxesp.fancapsextract.agent

import io.github.magonxesp.fancapsextract.DefaultContext
import io.github.magonxesp.fancapsextract.Picture
import io.github.magonxesp.fancapsextract.RawPictureMother
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class ImageAgentTest : ShouldSpec({
	should("getRawPicture should get the original file url") {
		val picture = Picture(
			url = "https://fancaps.net/anime/picture.php?/7123823",
			rawUrl = "https://cdni.fancaps.net/file/fancaps-animeimages/7123823.jpg"
		)
		val rawPicture = RawPictureMother.existing()

		val extracted = ImageAgent(DefaultContext).getRawPicture(picture)

		extracted shouldBe rawPicture
	}
})
