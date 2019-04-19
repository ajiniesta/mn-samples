# Samples for Micronaut

This repository has a couple of samples of usage for micronaut

## Testing messages api

Docker images before run locally out of docker-compose

	docker run -d -p 8500:8500 consul
	docker run -d -p 27017:27017 mongo

HTTP calls

	curl -XGET http://localhost:8080/v1/msgs
	curl -XPOST http://localhost:8080/v1/msgs -H "Content-Type: application/json" --data '{"ts":"1554568744","topic":"zway/salonpower","payload":"47.8"}'
	curl -XPOST http://localhost:8080/v1/msgs -H "Content-Type: application/json" --data '{"ts":"1554568754","topic":"zway/salonpower","payload":"57.8"}'
	curl -XPOST http://localhost:8080/v1/msgs -H "Content-Type: application/json" --data '{"ts":"1554562754","topic":"testing","payload":"100.8"}'
	curl -XGET http://localhost:8080/v1/msgs/topic/testing
	curl -XGET http://localhost:8080/v1/msgs/1554568744

Note that the port can change