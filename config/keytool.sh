#!/bin/bash

keytool -genkey -keyalg RSA -keystore keystore.jks -storepass password -validity 365 -keysize 2048 -dname "CN=Joseph Diza, OU=Creator, O=N/A, L=Delta, S=British Columbia, C=CA" -alias jmd
