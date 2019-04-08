This web app connects to your MongoDB Atlas cluster and uses your data to populate the different web pages. 
It is designed to be a basic demo of how to interact with your data - so clone it, play around with it and 
tailor it to your data!

## Overview

Aside from the home page, are three pages on this site so far (corresponding code can be found in ApplicationController.java):
1. /data - displays a count of the number of documents in the specified database and collection, which are taken in
as query parameters. For example, if you wanted to view the count for the snails collection in the animals db, you
would navigate to '/data?db=animals&collection=snails'.
2. /views - displays the number of views each collection has had to date.
3. /reviews - displays reviews left by users.

This application creates a `siteData` database in your cluster and stores information about page views and user
reviews there.

## Running the Application

This application requires Java 11 and Maven 3.6.

This application runs on port 8081 by default. This can be changed in the resource/application.properties file.

For the application to connect to your cluster, you need to update the MONGODB_CONNECTION_STRING variable in 
Application.java to match the connection string for your cluster. You'll also need to whitelist the IP address
you're running the application from, and add a MongoDB database user to your Atlas project. Instructions on how to do
these things can be found here: https://docs.atlas.mongodb.com/driver-connection/. On step 5 of these instructions, select
Java as your driver language and version 3.6 or later.

Once you've done this, run the following command from the src directory of this application to run it:
`mvn spring-boot:run`. Then, open your browser and navigate to `localhost:8081` and you should see the homepage.

## Resources
This application uses the Spring framework to manage setup and routing for the web application, and the synchronous MongoDB Java Driver ([docs](http://mongodb.github.io/mongo-java-driver/3.10/)) to interface with the database.
