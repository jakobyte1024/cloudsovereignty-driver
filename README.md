### Driver for CS-Showcase
based on: https://github.com/richardhendricksen/gatling-docker-on-aws/tree/v1 

Read more about it in the blog post [here](https://medium.com/@richard.hendricksen/distributed-load-testing-with-gatling-using-docker-and-aws-d497605692db).

## Environment Variables

| Environment Variable               | Description                                                  |
|------------------------------------|--------------------------------------------------------------|
| GATLING_BASEURL_SUPPLIERDOMAIN     | Base URL for the supplier domain used in Gatling simulations |
| GATLING_BASEURL_ORDERDOMAIN        | Base URL for the order domain used in Gatling simulations    |
| GATLING_BASEURL_MANUFACTUREDOMAIN  | Base URL for the manufacture domain used in Gatling simulations |
| GATLING_NR_USERS                   | Total number of virtual users in the Gatling simulation      |
| GATLING_MAX_DURATION               | Maximum duration of the Gatling simulation                   |
| GATLING_RAMPUP_TIME                | Time in seconds to ramp up all virtual users                 |
| GATLING_NR_USERS_AT_ONCE           | Number of virtual users started immediately at simulation start |

## Prerequisites  
Make sure you have docker and docker-compose installed locally. 

### Run Gatling tests directly without Docker
`mvn clean gatling:test`  

### Creating docker image locally
Use default time zone:
`docker build -t gatling-runner .` 

Set local time zone:
`docker build --build-arg TIME_ZONE="$(curl -s https://ipapi.co/timezone)" -t gatling-runner .`    

### Run docker image locally
`docker-compose up --scale gatling-runner=10` 
 