#!/bin/bash

# https://web.pa.msu.edu/reference/jdk-1.2.2-docs/tooldocs/win32/keytool.html
keytool -genkey -keyalg RSA -keystore keystore.jks -storepass password -validity 365 -keysize 2048 \
    -dname "CN=Joseph Diza, L=Delta, S=British Columbia, C=CA" -alias jmd
    #-dname "CN=Joseph Diza, OU=Creator, O=N/A, L=Delta, S=British Columbia, C=CA" -alias jmd
