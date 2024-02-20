package io.github.magonxesp.fancapsextract

import io.github.magonxesp.fancapsextract.exception.InvalidValueException

class Page(value: Int) {

	var value: Int = 1
		private set

	init {
	    if (value < 1) {
			throw InvalidValueException("The page value should be grater or equals than 1")
		}

		this.value = value
	}

	fun next() {
		value++
	}
}
