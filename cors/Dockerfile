FROM nginx:alpine
COPY default.conf /etc/nginx/conf.d/default.conf

# copy built webapp files to /data
COPY dist/ /data/

EXPOSE 80
ENTRYPOINT ["nginx","-g","daemon off;"]
#docker build -t cors . && docker run --add-host=host.docker.internal:host-gateway -p 8888:80 cors
