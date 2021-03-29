#!/bin/bash

echo "Beginning Test Execution"

environment=$1
suiteName=$2

mvn clean -P$environment
mvn compile -P$environment
mvn test -DsuiteXmlFile=$suiteName