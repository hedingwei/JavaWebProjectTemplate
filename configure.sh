#!/bin/sh
export PROJECT_HOME=`pwd`
export ANT_HOME=${PROJECT_HOME}/tools/ant
export MAVEN_HOME=${PROJECT_HOME}/tools/maven
export PATH=.:${ANT_HOME}/bin:${MAVEN_HOME}/bin:${PATH}
chmod 755 ${ANT_HOME}/bin/*
chmod 755 ${MAVEN_HOME}/bin/*
