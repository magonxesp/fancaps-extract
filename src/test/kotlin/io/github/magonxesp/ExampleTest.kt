package io.github.magonxesp

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual

class ExampleTest : ShouldSpec({
	should("return the greeting") {
		val greet = greeting("Kotlin library")

		greet shouldBeEqual "Hello Kotlin library!"
	}
})
