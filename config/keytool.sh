#!/bin/bash

keytool -genkey -keyalg RSA -alias selfsigned -keystore keystore.jks -storepass password -validity 3650 -keysize 2048
