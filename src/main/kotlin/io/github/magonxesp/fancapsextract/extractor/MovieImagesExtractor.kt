package io.github.magonxesp.fancapsextract.extractor

import io.github.magonxesp.fancapsextract.*
import it.skrape.selects.Doc
import it.skrape.selects.ElementNotFoundException

class MovieImagesExtractor(private val context: Context) : Extractor<List<Picture>> {
	override fun extract(document: Doc): List<Picture> =
		document.findAllOrEmpty(".row .col-lg-3.col-md-4.col-sm-6.col-xs-12 a").map {
			Picture(url = context.getFullUrl("/movies/" + it.attribute("href")))
		}
}
