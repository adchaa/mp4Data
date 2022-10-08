#!/bin/bash
FILES="*/*.java Metadata.java"
echo $FILES
for f in $FILES
do
    echo "compiling : $f"
    javac $f
done
java Metadata