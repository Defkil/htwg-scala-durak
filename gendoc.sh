#!/usr/bin/env bash
set -euo pipefail
IFS=$'\t\n'

git clone -b gh-pages "https://github.com/${TRAVIS_REPO_SLUG}.git" gh-pages

wget https://gist.githubusercontent.com/killercup/5917178/raw/40840de5352083adb2693dc742e9f75dbb18650f/pandoc.css
DOCS_DIR=gh-pages/$TRAVIS_BRANCH
rm -rf "$DOCS_DIR"
mkdir -p "$DOCS_DIR"

# "Build" the documentation
sbt doc


cp -a "target/scala-2.12/api/." "$DOCS_DIR/docs/"
pandoc "README.md" -f markdown -t html -s -o "gh-pages/index.html" --css pandoc.css
echo "Documentation successfully built"
if [[ -n $TRAVIS_PULL_REQUEST_BRANCH ]]; then
    # We are building a pull request, nothing to do here
    echo "Building a PR with ID=$TRAVIS_PULL_REQUEST, skipping the deployment."
    exit 0
fi

cd "$DOCS_DIR"

git config push.default simple
git config user.name "Travis CI"
git config user.email "travis@travis-ci.org"

git add --all
git commit -m "Deploy code docs to GitHub Pages Travis build: $TRAVIS_BUILD_NUMBER" -m "Commit: $TRAVIS_COMMIT"
git push --force "https://${GH_TOKEN}@github.com/${TRAVIS_REPO_SLUG}.git"

echo "Documentation successfully published"