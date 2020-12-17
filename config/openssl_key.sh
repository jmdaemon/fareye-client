#!/bin/bash

openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout localhost_self-signed.key -out localhost_self-signed.pem
