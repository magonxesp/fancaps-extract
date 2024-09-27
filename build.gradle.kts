import com.vanniktech.maven.publish.SonatypeHost

plugins {
	kotlin("jvm") version "2.0.20"
	kotlin("plugin.serialization") version "2.0.20"
	id("com.vanniktech.maven.publish") version "0.29.0"
}

group = "io.github.magonxesp"
version = "0.1.1"

mavenPublishing {
	coordinates(group as String, "fancaps-extract", version as String)
	publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
	signAllPublications()

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

kotlin {
	java {
		jvmToolchain(8)
	}
}

tasks.withType<Test>().all {
	useJUnitPlatform()
}



