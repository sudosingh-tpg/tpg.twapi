@echo off

echo "Beginning Test Execution"

set environment=$1
set suiteName=$2

call mvn clean -P@%environment%
call mvn compile -P@%environment%
call mvn test -DsuiteXmlFile=@%suiteName%