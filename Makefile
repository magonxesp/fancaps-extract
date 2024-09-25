.PHONY: first-version bump-version

first-version:
	@python scripts/bump-version.py --first-version

bump-version:
	@python scripts/bump-version.py
