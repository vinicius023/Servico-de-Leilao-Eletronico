#!/bin/bash

find ./ -type d | while read DIR; do 

    rm -r $DIR/*.class

done

export CLASSPATH=$CLASSPATH:../lib/jgroups.jar:../lib/json-simple-1.1.1.jar:./modelo:./visao:./controle

find ./ -type d | while read DIR; do 

    javac -cp $CLASSPATH $DIR/*.java

done

java -cp $CLASSPATH Principal



#export CLASSPATH=$CLASSPATH:../lib/jgroups.jar
#java -cp $CLASSPATH -jar leilao.jar 
