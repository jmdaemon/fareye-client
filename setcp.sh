#!/bin/bash

#export CLASSPATH=$HOME/workspace/apps/BankApp/lib/*:lib
#export CLASSPATH=".:.*"
JUNIT_HOME=./lib/junit4.13.1.jar
#export CLASSPATH=.:./*:$HOME/workspace/apps/BankApp/lib/*:$JUNIT_HOME/*:./bin/*:./bin/crypt/*

#JUNIT_JUPITER="$HOME.local/share/gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-api/5.7.0/b25f3815c4c1860a73041e733a14a0379d00c4d5/*"
USER_CLASSES=./build/classes/java/main/crypt/
export CLASSPATH=.:./*:$HOME/workspace/apps/BankApp/lib/*:$HOME.local/share/gradle/caches/*:$USER_CLASSES/*:./bin/*:./bin/crypt/*
