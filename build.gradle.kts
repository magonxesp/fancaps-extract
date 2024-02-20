
plugins {
	kotlin("jvm") version "1.9.0"
	kotlin("plugin.serialization") version "1.9.0"
	`java-library`
	`maven-publish`
	signing
	id("net.thebugmc.gradle.sonatype-central-portal-publisher") version "1.1.1"
}

group = "io.github.magonxesp"
version = "0.0.0"

publishing {
	publications {
		register<MavenPublication>("fancaps-extract") {
			artifactId = "fancaps-extract"
			from(components["java"])
		}
	}
}

signing {
	sign(publishing.publications["fancaps-extract"])
}

tasks.javadoc {
	if (JavaVersion.current().isJava9Compatible) {
		(options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
	}
}

centralPortal {
	pom {
		name = "FanCaps extract"
		description = "Extract search results from the https://fancaps.net/ site"
		url = "https://github.com/magonxesp/fancaps-extract"
		licenses {
			license {
				name = "The MIT License (MIT)"
				url = "https://mit-license.org/"
			}
		}
		developers {
			developer {
				id = "magonxesp"
				name = "MagonxESP"
				email = "magonxesp@gmail.com"
				url = "https://github.com/magonxesp"
			}
		}
		scm {
			connection = "scm:git:git://github.com/magonxesp/fancaps-extract.git"
			developerConnection = "scm:git:ssh://github.com/magonxesp/fancaps-extract.git"
			url = "https://github.com/magonxesp/fancaps-extract"
		}
	}
}

repositories {
	mavenCentral()
}

dependencies {
	val kotest_version: String by project
	val scrapeit_version: String by project
	val ktor_version: String by project

	implementation("it.skrape:skrapeit:$scrapeit_version")
	implementation("io.ktor:ktor-client-core:$ktor_version")
	implementation("io.ktor:ktor-client-cio:$ktor_version")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
	testImplementation(kotlin("test"))
	testImplementation("io.kotest:kotest-runner-junit5:$kotest_version")
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(8)
}

