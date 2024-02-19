package io.github.magonxesp.fancapsextract

import io.github.magonxesp.fancapsextract.exception.InvalidValueException

class Page(val value: Int) {
	init {
	    if (value < 1) {
			throw InvalidValueException("The page value should be grater or equals than 1")
		}
	}
}
