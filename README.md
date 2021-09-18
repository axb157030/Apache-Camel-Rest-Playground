# Apache-Camel-Rest-Playground
An application I modify to play and see how Apache Camel works. 
<p>Apache Camel in additon to being used to route data between files, queues, databases, and web services, it can also be used to construct RESTful APIs. <a href="https://www.youtube.com/watch?v=spDjbC8mZf0"> I learn apache camel in part thanks to this video</a>.</p>

# Blog
The REST requests are all prefixed with "services" thank to `camel.servlet.mapping.context-path = /services/*` in the application.properties file. Without that, the requests would be prefixed with "camel".

## REST Routes 
- REST routes are defined by  rest().method("/url path")and from(<a href="https://camel.apache.org/components/latest/rest-component.html">rest://method:path[:uriTemplate]?[options]</a>) where method is an HTTP Method such as GET andd POST.
  - For example `
          rest().get("/hello") // Define url path
                .to("direct:hello"); // // Route to go to `
                
  - This example is in this application. If users make a GET request with `http://localhost:8080/services/hello` they will get a response of "Hello". The `to("direct:hello")` method maps the request to a route named "hello". This route returns a message of hello. 

## Routes
  - Routes are defined by the <a href= "https://camel.apache.org/manual/latest/routes.html">from</a> and to method. We are specifcally talking about routing web services. They modify the message using processor methods. The message body or what the last service in route returns is what is returned from the route. The from() methods route to other routes with the to() methods. When routing web service, the from() method contains name of route it defines and the to() method contains name of route to go to. The message in the route is held by `Exchange` objects.
