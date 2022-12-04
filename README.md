# Bot-Server

Bot-Server is a multi-threaded web server written in Java. It is a Maven project that can be compiled and run using the `mvn` command.

## Design

The `BotServer` class is the main class of the web server. It listens for incoming connections on port `8080` and creates a new `ClientHandler` thread for each incoming connection.

The `ClientHandler` class is responsible for handling each incoming request. It receives the request, parses it using the `HttpRequest` class, and sends a response using the `HttpResponse` class.

The `HttpRequest` class is responsible for parsing the request line and headers of the incoming request. It also extracts any query parameters from the request path.

The `HttpResponse` class is responsible for creating the response to the incoming request. It uses the `HttpResponseHeaders` and `HttpResponseBody` classes to generate the response headers and body, respectively.

## Compiling

To compile the project, run the following command in the root directory of the project:

```mvn clean install```

## Running

To run the web server, run the following command in the root directory of the project:

```mvn exec:java -Dexec.mainClass="ch.marth.BotServer"```

The web server serves files from the `web-root` directory in the project. Only `html` and `jpeg` files are supported. Only GET and HEAD methods are supported. Unsupported media types and methods will result in a `404 Not Found` response.

## Running the test cases

To run the test cases for the project, run the following command in the root directory of the project:

```mvn test```

This will run all of the test cases for the project using JUnit 4.12.

## License

The Bot-Server web server is licensed under the terms of the Apache License 2.0.