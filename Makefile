.PHONY: first-version bump-version

first-version:
	@if ! git show-ref --tags v0.0.0 --quiet; then \
		git tag v0.0.0; \
  	  	git cliff -o CHANGELOG.md; \
  	  	git add CHANGELOG.md; \
      	sed -i "s/version = \"v?[0-9.]+\.?[a-z]*\.?[0-9]*\"/version = \"0.0.0\"/g" build.gradle.kts; \
      	git add build.gradle.kts; \
      	sed -i -r "s/io\.github\.magonxesp:([a-z\-]+):v?[0-9.]+\.?[a-z]*\.?[0-9]*/io\.github\.magonxesp:\1:0.0.0/g" README.md; \
      	git add README.md; \
      	git commit -m "🚀 create first version"; \
    fi

bump-version:
	@VERSION=$$(git cliff --unreleased --bump --context | jq -r '.[0].version'); \
	if [[ ! "$VERSION" -eq "null" ]]; then \
		git cliff --bump -o CHANGELOG.md; \
		git add CHANGELOG.md; \
		sed -i "s/version = \"v?[0-9.]+\.?[a-z]*\.?[0-9]*\"/version = \"$$VERSION\"/g" build.gradle.kts; \
		git add build.gradle.kts; \
		sed -i -r "s/io\.github\.magonxesp:([a-z\-]+):v?[0-9.]+\.?[a-z]*\.?[0-9]*/io\.github\.magonxesp:\1:$$VERSION/g" README.md; \
		git add README.md; \
		git commit -m "🚀 bump version to $$VERSION"; \
		git tag v$$VERSION; \
    else \
    	echo "There is not a new version available"; \
    fi
