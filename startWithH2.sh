#!/bin/bash

git pull

mvn clean
mvn package

mvn spring-boot:run
