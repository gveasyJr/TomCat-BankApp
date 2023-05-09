#!/bin/bash
echo "shutting down any running tomcat servlet instances..."
../../../../bin/shutdown.sh

echo "cleaning database files..."
> ../../usersdb
> ../../accountsdb

echo "compiling java code..."
javac -classpath ../../../../lib/servlet-api.jar *.java
echo "starting up tomcat servlet instance"
../../../../bin/startup.sh
