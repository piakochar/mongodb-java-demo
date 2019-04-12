# mongodb-java-demo
This web app connects to your MongoDB Atlas cluster and uses your data to populate the different web pages. 
It is designed to be a basic demo of how to interact with your data - so clone it, play around with it and 
tailor it to your data!

## Setup
This application requires git, Java 11 and Maven 3.6. Follow the setup instructions below for the relevant OS.

#### OSX
+ Install IDE of your choice
    + [Sublime](http://docs.sublimetext.info/en/latest/getting_started/install.html#id2)
    + [Eclipse](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2019-03/R/eclipse-java-2019-03-R-macosx-cocoa-x86_64.dmg)
    + [IntelliJ Community](https://www.jetbrains.com/idea/download/#section=mac)
    + [Visual Studio Code](https://code.visualstudio.com/download)
+ Install HomeBrew
    + `/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`
    + OR see detailed install instructions [here](https://docs.brew.sh/Installation)
+ Install JDK
    + `curl -sL https://github.com/shyiko/jabba/raw/master/install.sh | bash && . ~/.jabba/jabba.sh`
    + `jabba install adopt@1.11.0-1`
    + `jabba alias default adopt@1.11.0-1`
    + `jabba use default`
    + Confirm that running `java -version` has `11.0.1` in the output
+ Install Maven
    + `brew install mvn`
    + OR see detailed install instructions [here](https://maven.apache.org/install.html)
+ If you haven't already installed git
    + `brew install git`
    + Create or login to your Github account [here](https://github.com/)
    + Follow instructions [here](https://gist.github.com/adamjohnson/5682757) to create a new SSH key for your Github account.
    + Restart terminal session for changes to take effect 
+ Clone this repo
    + `cd [wherever you want the repo]`
    + `git clone git@github.com:piakochar/mongodb-java-demo.git`
    + OR see detailed instructions for cloning an existing repository [here](https://git-scm.com/book/en/v2/Git-Basics-Getting-a-Git-Repository)

#### Windows
+ Install IDE of your choice
    + [Sublime](http://docs.sublimetext.info/en/latest/getting_started/install.html#id1)
    + [Eclipse](http://docs.sublimetext.info/en/latest/getting_started/install.html#id1)
    + [IntelliJ Community](https://www.jetbrains.com/idea/download/#section=windows)
    + [Visual Studio Code](https://code.visualstudio.com/download)
+ Install JDK
    + Follow installation instructions [here](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-DAF345BA-B3E7-4CF2-B87A-B6662D691840)
    + Confirm that running `java -version` has `11.0.1` in the output
+ Install Maven
    + Follow installation instructions [here](https://maven.apache.org/install.html)
+ If you haven't already installed git
    + Follow Windows download instructions [here](https://git-scm.com/downloads)
    + Use all defaults EXCEPT for choosing `Git from the command line and 3rd party software` which will automatically 
    add Git to PATH
    + Follow instructions [here](https://gist.github.com/adamjohnson/5682757) to create a new SSH key for your (potentially new) Github account
    + Restart Command Prompt session for changes to take effect 
+ Clone this repo
    + `chdir [wherever you want the repo]`
    + `git clone git@github.com:piakochar/mongodb-java-demo.git`
    + Or see detailed instructions for cloning an existing repository [here](https://git-scm.com/book/en/v2/Git-Basics-Getting-a-Git-Repository)

## Overview
Aside from the home page, are three pages on this site so far (corresponding code can be found in ApplicationController.java):
1. /data - displays a count of the number of documents in the specified database and collection
2. /analytics - displays the number of times each type of request has been made on the site
3. /reviews - displays reviews left by users.

This application creates a `siteData` database in your cluster and stores information about page views and user
reviews there.

## Running the Application
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
