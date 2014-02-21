#!/bin/sh

set -e
set -x

echo TTCN COMPILER

java -cp ../OT3COMPILER:../OT3RUNTIME OT3COMPILER CoffeeSuite.ttcn

java -cp ../OT3COMPILER:../OT3RUNTIME OT3COMPILER HelloSuite.ttcn

echo JAVA COMPILER

javac -cp .:../OT3RUNTIME *.java
javac -cp .:../OT3RUNTIME HelloSuite/*.java
javac -cp .:../OT3RUNTIME CoffeeSuite/*.java


echo TESTER

java -cp .:../OT3TESTER:../OT3RUNTIME OT3TESTER &

echo SUT

java -jar ../CoffeeServer/CoffeeServer.jar CoffeeServer.Server &

