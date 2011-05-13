dropwizard-example
===================

Welcome to the world of **dropwizard**. With this example, you'll have an simple, fully
instrumented "Hello, World" application up and running in minutes. Let's do it.

Make sure you have [simple-build-tool](http://code.google.com/p/simple-build-tool/) installed.

1. Clone this example repo.
	
		git clone https://github.com/codahale/dropwizard-example.git

2. `cd` into the new directory.

3. Now we'll easily build a fat JAR of our project and its dependencies:
		
		sbt update	
		sbt assembly 
   
   The `sbt assembly` command uses [assembly-sbt](https://github.com/codahale/assembly-sbt) (included with dropwizard) 
   to handle the hard work. That JAR should end up somewhere like 
   `target/scala_2.8.1/dropwizard-example-assembly-1.0-SNAPSHOT.jar`
		
4. Start the HTTP server, using the provided configuration file. 

		java -jar target/scala_2.8.1/dropwizard-example-assembly-1.0-SNAPSHOT.jar server example.conf.template

5. Open up your browser and point it at `http://localhost:8080/hello-world`.
   Boom.

6. Throw in a query parameter. `http://localhost:8080/hello-world?name=Martin`. 
   The id is auto-incremented and the given name will appear.

7. Now for the really fun part. Point your browser to `http://localhost:8081/` and you'll find operational data, health checks,
   and more. Dropwizard ships with [metrics](https://github.com/codahale/metrics), which provides tons of valuable data 
   about your application and its production environment. 

8. And now go build some awesome services. 
