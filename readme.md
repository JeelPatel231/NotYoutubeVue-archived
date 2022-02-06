# NotYoutube
### yep! you guessed it, its not youtube.
-------

its a wanna be youtube clone website with more features like :
1. Audio only mode
2. Picture in picture mode aka floating window thingy (if on android)
3. no goolag spying
4. mutiple audio and video streams to select from (if possible)
5. direct download option
6. NO ADS (yay!)

Downsides :
1. no personal feed because no relation with google.
2. not good recommendations based on your viewed content (because data aint stored)
3. no interaction with site i.e cant like/dislike/comment

----
## What does this repo contains?
`API/` = contains the api utilising newpipeExtractor to parse youtube and give json response for the web client to work,
its absolutely standalone, you can just copy the api folder, `./gradle run` it and it would give out responses as expected

`CORS/` = contains the nginx reverse proxy for adding cors allow origin header

PS : i know i couldve added it in ktor api itself, BUT, that wasnt the case, i need to use a js lib for playing video and it fetches video from google's domains, which are ofc not related to my origin and hence playing it throws cors error, so 1 stone 2 birds ( 3 if you consider html files hosting xD )

`if you wanna use ktor api standalone, just add "Access-Control-Allow-Origin" header in Application.kt and it will be fine`

`WEB/` = contains the static files for web client, made in vue.js 3. This also can work standalone as a client if api is down, just change the host for api in `main.js` file in /src.

every folder contains its Dockerfile, WEB does NOT because those are just static web files and are compiled and moved to nginx dir to host them (checkout `build.sh` to have an idea)

----

## HOW IT WORKS?
-> builds api, copies fat jar and makes a docker image

-> build vue project and copies static files in nginx folder and builds the nginx docker

-> both docker communicate using port 8081 (api port), the exposed port is 8888 (changeable in docker-compose.yml), usable on host machine

-> host/api/(.\*) calls are rewritten to api:8081/(.\*) for calling api

if you want to locally host the whole project, you can do so by
running `./build.sh` 

-----

## This is still WIP (Work in Progress)

## where can i see it running?

no, this is not hosted anywhere as of now, locally host it to see stuff

(i dont think i will push broken code to github, but, might as well revert 1 or 2 commits if stuff doesnt work, or pull up an issue in this repo)