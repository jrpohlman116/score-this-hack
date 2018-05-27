# score-this-hack

This is an application to score presenters based on several different criteria. It has the functionality to register and login and administrator once they have proper authorization. ScoreThisHack uses a MySQL database to store all the the results from the competition. These results can be viewed from the AdminPage.

## Running

Run this using [sbt](http://www.scala-sbt.org/).  If you downloaded this project from http://www.playframework.com/download then you'll find a prepackaged version of sbt in the project directory:

```
sbt run
```

And then go to http://localhost:9000 to see the running web application.

## Controllers

- HomeController.java:

  Handles the judge side of this application.

- AdminController.java:

  Handles the administrator side of this application. This includes logging in, registering the user, and accessing results after they have been submitted.

## Components

- Module.java:

  Shows how to use Guice to bind all the components needed by the application.
