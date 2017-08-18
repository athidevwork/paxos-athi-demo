# paxos-athi-demo

paxos-athi-demo is a springboot application that implements 3 challenges from FullStack Engineer Challenges.
 
## To Build And run demo
>*** mvn spring-boot:run***

### Challenge 1
Develop a webservice with two endpoints : /messages and /messages/<hash>

#### To test for the end points for Challenge 1
>curl -X POST -H "Content-Type: application/json" -d 'foo' http://localhost:8080/messages

>curl -X POST -H "Content-Type: application/json" -d '{"message": "foo"}' http://localhost:8080/messages

>curl -s -X GET http://localhost:8080/messages/2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae

>curl -s -X GET http://localhost:8080/messages/123452c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e88626

>curl -s -X GET http://localhost:8080/messages/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

>curl -s -i -X GET http://localhost:8080/messages/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

Optional : We could scale this service 
1. if we run multiple versions of the microservice app that is load balanced through an edge service and service discovery.
2. Use Kafka to add multiple consumers with one producer adding to a topic so multiple consumers will do this in parallel.
3. Use Multi threading with Executor service to parallel the work in multiple threads.

### Challenge 2
Get two distinct items to get on a gift card balance and also max items that could be got on a gift card balance.

#### To test for the end points for Challenge 2
>curl -s -X GET http://localhost:8080/findpair/2500

>curl -s -X GET http://localhost:8080/maxpairs/2500

Big O notation for the program : O(n * n)

### Challenge 3
Print possible combinations of X with 0 and 1 from the pattern string as input.

#### To test for the end points for Challenge 3
>curl -s -X GET http://localhost:8080/replaceX/X0

>curl -s -X GET http://localhost:8080/replaceX/X0X

>curl -s -X GET http://localhost:8080/replaceX/10X10X0

Big O notation for the program : O(n log n)
