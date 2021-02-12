#!/usr/bin/env bash
nohup java -Djava.security.egd=file:/dev/./urandom -jar jeebbs-restful-spring-boot.jar >/dev/null 2>&1 &
