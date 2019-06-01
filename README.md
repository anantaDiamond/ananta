#manual build images and push to hub
docker build -t siriphatakrudkaew/anantax:1.0.0.1 .
docker login
user:siriphatakrudkaew
pwd:
docker push siriphatakrudkaew/anantax:1.0.0.1

#setup docker-compose 

#docker run project
docker run -p 8080:8080 [imageId]

#setup mysql
docker run --name=ananta -e MYSQL_ROOT_PASSWORD=abc123 -e MYSQL_DATABASE=ananta -p 3306:3306 -d mysql
docker exec -it ananta bash
mysql -u root -p
abc123

use ananta;
#run script >>>env folder>>dev folder
#place script into mysql by order as below
#s_schema
#s_data
#x_schema
#x_data
