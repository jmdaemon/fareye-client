#!/bin/bash

GRADLE_CLASSPATH="cat ./syntastic_javac_config | cut -d " " -f 3"
export CLASSPATH=$GRADLE_CLASSPATH 
