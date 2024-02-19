package io.github.magonxesp.fancapsextract

import it.skrape.selects.Doc
import it.skrape.selects.DocElement
import it.skrape.selects.ElementNotFoundException

fun Doc.findSecondOrNull(cssSelector: String): DocElement? =
	try {
		findSecond(cssSelector)
	} catch (exception: ElementNotFoundException) {
		null
	}

fun Doc.findAllOrEmpty(cssSelector: String) =
	try {
		findAll(cssSelector)
	} catch (exception: ElementNotFoundException) {
		listOf()
	}
