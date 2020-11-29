# MongoDB Demo

This is simple spring boot application intended to demo the integration of 
MongoDB. 

## Running the Application
1. Run the following command to start the application
```
docker-compose up
```
This will start the application at `http://localhost:8080` with mongodb.

## cURLs
### Create Log
Request
```
curl --request POST \
  --url http://localhost:8080/logs \
  --header 'content-type: application/json' \
  --data '{
	"message":"hello World!"
}'
```
Response
```
{
  "id": "604a0228-866b-4491-8900-16b4b44238fd",
  "version": 1,
  "timestamp": "2020-11-29T18:44:11.441+00:00",
  "message": "hello World!"
}
```

### Get Logs
Request
```
curl --request GET \
  --url http://localhost:8080/logs
```
Response
```
[{
  "id": "604a0228-866b-4491-8900-16b4b44238fd",
  "version": 1,
  "timestamp": "2020-11-29T18:44:11.441+00:00",
  "message": "hello World!"
}]
```

### Get Log by Id
Request
```
curl --request GET \
  --url http://localhost:8080/logs/604a0228-866b-4491-8900-16b4b44238fd
```
Response
```
{
  "id": "604a0228-866b-4491-8900-16b4b44238fd",
  "version": 1,
  "timestamp": "2020-11-29T18:44:28.484+00:00",
  "message": "hello World"
}
```

### Update a Log
Request
```
curl --request PATCH \
  --url http://localhost:8080/logs/604a0228-866b-4491-8900-16b4b44238fd \
  --header 'content-type: application/json' \
  --data '{
	"message":"hello Galaxy"
}'
```
Response
```
{
  "id": "604a0228-866b-4491-8900-16b4b44238fd",
  "version": 2,
  "timestamp": "2020-11-29T18:44:28.484+00:00",
  "message": "hello Galaxy"
}
```

### Get Count of Logs
Request
```
curl --request GET \
  --url http://localhost:8080/logs/count
```

Response
```
1
```
