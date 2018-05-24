#!/bin/bash

export CLASSPATH=$CLASSPATH:jgroups.jar

javac ValChat.java
javac Grupo.java

java ValChat
