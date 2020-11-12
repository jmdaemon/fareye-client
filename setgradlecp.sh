#!/bin/bash

GRADLE_CLASSPATH=$(cat .syntastic_javac_config | cut -d " " -f 4 | tr -d '"')
export CLASSPATH=./:$GRADLE_CLASSPATH 
