package io.github.magonxesp.fancapsextract.agent

import io.github.magonxesp.fancapsextract.Context
import io.github.magonxesp.fancapsextract.Picture
import io.github.magonxesp.fancapsextract.RawPicture
import io.github.magonxesp.fancapsextract.extractor.RawPictureExtractor

class ImageAgent(context: Context) : Agent(context) {
	fun getRawPicture(picture: Picture): RawPicture? =
		extractWithGetRequest(RawPictureExtractor(), picture.url)
}
