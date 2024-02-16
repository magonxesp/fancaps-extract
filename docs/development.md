# Development

## Table of contents

* [Requirements](#requirements)
* [Test](#test)
* [Creating the Changelog](#creating-the-changelog)
* [Publish artifacts](#publish-artifacts)
    * [Publish to Sona Type (Maven central)](#publish-to-sona-type-maven-central)
    * [Publish to Maven local](#publish-to-maven-local)

## Requirements

* OpenJDK 21
* jq - [Install jq](https://jqlang.github.io/jq/download/)
* Git cliff - [Install git cliff](./install-git-cliff.md)
* Make
* Gradle 8.5 (wrapper is recommended)

## Test

For test the project you should run this command

```sh
$ ./gradlew cleanTest build test
```

## Creating the Changelog

Before creating the `CHANGELOG.md` you should ensure you are using [conventional commits](https://www.conventionalcommits.org/en/v1.0.0/).

You should create the tags following [semantic versioning](https://semver.org/).

Create the first version tag.
```sh
$ git tag v0.0.0
```

Create the `CHANGELOG.md` file.
```sh
$ git cliff -o CHANGELOG.md
```

When you have new commits for the new version you should calculate the new version before create the tag.

Calculate the new version and create the tag.
```sh
$ git tag v$(git cliff --unreleased --bump --context | jq -r '.[0].version')
```

Update the `CHANGELOG.md`.
```sh
$ git cliff --bump -o CHANGELOG.md
```

You can generate the changelog only for the current version.
```sh
$ git cliff --current
```

Alternatively you can use the action `bump-verion` from the Makefile for bump the version including updating the `CHANGELOG.md`
```sh
$ make bump-version
```

## Publish artifacts

⚠️ **CAUTION** ⚠️

**The artifacts should be published only by the owner of the repository.**

Before publish you need to create the `gradle.properties` file on the `.gradle` directory on your home directory
and create the GPG keys.

Create the GPG keys.
```sh
$ gpg --gen-key
```

Get the GPG key id.
```sh
$ gpg -K
```

Upload the keys to the ubuntu keyserver.
```sh
$ gpg --keyserver keyserver.ubuntu.com --send-keys <your gpg key id>
```

Export the secret keys to a keyring on your home directory.
```sh
$ gpg --keyring secring.gpg --export-secret-keys > ~/.gnupg/secring.gpg
```

Edit or create the `gradle.properties` file with your favorite text editor.
```sh
$ vim ~/.gradle/gradle.properties
```

And put the following content at the end of the file.
```text
signing.keyId=<the last 8 digits of the key id>
signing.password=<your gpg key passphrase>
signing.secretKeyRingFile=<absolute path to your home directory>/.gnupg/secring.gpg

centralPortal.username=<sonatype user token>
centralPortal.password=<sonatype password token>
```

If you want to publish a gradle plugin you need to append the following content.
You may find your keys on the user page of the [Gradle Plugin Portal](https://plugins.gradle.org/)
```text
gradle.publish.key=<your publish key>
gradle.publish.secret=<your publish secret>
```

### Publish to Sona Type (Maven central)

```sh
$ ./gradlew publishToCentralPortal
```

### Publish to Maven local

```sh
$ ./gradlew publishToMavenLocal
```
