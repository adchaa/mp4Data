#!/bin/bash
DIRB="build"
FILES="*/*.java *.java"
if [ ! -d $DIRB ]; then
  mkdir $DIRB
fi
javac -d ./$DIRB/ $FILES
java -cp ./$DIRB/ Metadata