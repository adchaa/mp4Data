#!/bin/bash
FILES="*/*.java Metadata.java"
for f in $FILES
do
    echo "compiling : $f"
    javac $f
done
java Metadata