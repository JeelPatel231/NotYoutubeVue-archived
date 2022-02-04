#!/usr/bin/env bash

cd web
npm run build # build vue project
rm -rf ../cors/dist # remove existing dir in cors folder, if exists
cp -r dist ../cors/dist # copy production build in cors-nginx for serving static files


cd ../api
./gradlew shadowjar # build api FAT jar for running in java docker

docker-compose build
docker-compose up