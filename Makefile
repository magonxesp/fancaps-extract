.PHONY: first-version bump-version

first-version:
	@PYTHONPATH="$$(pwd)/scripts" python scripts/bump-version.py --first-version

bump-version:
	@PYTHONPATH="$$(pwd)/scripts" python scripts/bump-version.py
