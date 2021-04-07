#!/usr/bin/env bash

# Install NVM
curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.6/install.sh | bash
export NVM_DIR="$HOME/.nvm"
if [ -s "$NVM_DIR/nvm.sh" ]; then
    . "$NVM_DIR/nvm.sh"
fi
nvm install 8

# Install MDS
git clone $MDS_SOURCE --branch master --single-branch --depth=2
pushd mobile-in_house_distribution/client
npm install -g
popd

version=$(mds -v)
echo "MDS version installed: $version"

# Run MDS
mds