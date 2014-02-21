#!/bin/sh

if [ "$CYAN_13_11" = "" ]
then
   echo CYAN_13_11 undefined
   echo Download cyan.compilertools.net/cyan-13-11.zip
   echo Unpack this archiv and let CYAN_13_11 point to cyan-13-11
   exit 1
fi

set -e
set -x

java -Xmx1551500k -cp $CYAN_13_11/Cyan Cyan *.g
javac -cp . OT3COMPILER.java
