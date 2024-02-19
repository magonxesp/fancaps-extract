package io.github.magonxesp.fancapsextract.extractor

import it.skrape.selects.Doc

interface Extractor<T> {
	fun extract(document: Doc): T
}
