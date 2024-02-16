.PHONY: bump-version

bump-version:
	@VERSION=$$(git cliff --unreleased --bump --context | jq -r '.[0].version'); \
	git cliff --bump -o CHANGELOG.md; \
	git add CHANGELOG.md; \
	sed -i "s/version = \"v?[0-9.]+\.?[a-z]*\.?[0-9]*\"/version = \"$$VERSION\"/g" build.gradle.kts; \
	git add build.gradle.kts; \
    sed -i "s/io\.github\.magonxesp:([a-z\-]+):v?[0-9.]+\.?[a-z]*\.?[0-9]*/io\.github\.magonxesp:\1:$$VERSION/g" README.md; \
    git add README.md; \
    git commit -m "🚀 bump version to $$VERSION"; \
    git tag v$$VERSION
