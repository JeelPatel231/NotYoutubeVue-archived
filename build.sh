#!/usr/bin/env sh

if [[ $(command -v yarn) ]]; then 
    pkgman=yarn;
elif [[ $(command -v npm) ]]; then 
    pkgman=npm;
else 
    echo -e "no node package managers found,\ninstall yarn or npm to continue\n";
    exit 1;
fi

cd web
$pkgman install # install deps
$pkgman run build # build vue project
rm -rf ../cors/dist # remove existing dir in cors folder, if exists
cp -r dist ../cors/dist # copy production build in cors-nginx for serving static files


cd ../api
chmod +x gradlew
./gradlew shadowjar # build api FAT jar for running in java docker

docker-compose build
docker-compose up