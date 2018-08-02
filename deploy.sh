#!/bin/bash

name=$1
place=$2
serv=$3

echo "name: ${name}"
echo "place: ${place}"
echo "serv: ${serv}"


docker build -t ${name} ${place}

if docker tag ${name} ${serv}:5000/${name}
then
  docker push ${serv}:5000/${name}
else
  echo "FAILED to push the image to the Docker Registry" 1>&2
fi

docker rmi ${name}
docker rmi ${serv}:5000/${name}
