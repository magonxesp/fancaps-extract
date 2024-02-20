package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.RawPicture
import it.skrape.selects.Doc
import it.skrape.selects.ElementNotFoundException

class RawPictureExtractor : Extractor<RawPicture?> {
	override fun extract(document: Doc): RawPicture? =
		try {
			val link = document.findFirst(".single_post_content a[href*=\"cdni\"]")
			RawPicture(url = link.attribute("href"))
		} catch (excepton: ElementNotFoundException) {
			null
		}
}
