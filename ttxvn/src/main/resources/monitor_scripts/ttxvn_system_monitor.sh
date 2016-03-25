#!/bin/bash

#check status of solr English, restart if need
if (( $(netstat -lnp | grep 8984 | wc -l) == 0 ))
then
cd /setup/solr-ttxvn-en/example
java -jar start.jar &
fi

#check status of solr Vietnamese, restart if need
if (( $(netstat -lnp | grep 8983 | wc -l) == 0 ))
then
cd /setup/solr-ttxvn-vi/example
java -jar start.jar &
fi

#check status of web app, restart if need
if (( $(netstat -lnp | grep 8080 | wc -l) == 0 ))
then
cd /setup/apache-tomcat-8.0.12
./bin/startup.sh
fi
