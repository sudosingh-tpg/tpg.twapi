#!/bin/bash

echo "Beginning Test Execution"

environment=$1

echo $environment

mvn compile -P$environment