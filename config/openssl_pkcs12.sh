#!/bin/bash

openssl pkcs12 -export -in localhost_self-signed.pem -inkey localhost_self-signed.key -name "SelfSignedServer" -out localhost_self-signed.p12

