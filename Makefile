.PHONY: first-version bump-version

first-version:
	@python3 scripts/bump-version.py --first-version

bump-version:
	@python3 scripts/bump-version.py
