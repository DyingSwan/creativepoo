# !/bin/bash

clear

echo -n "Input Problem Number: "

read input

echo -n "Making directory ... "

mkdir -p "company/"$input

echo "Done!"

echo -n "Copying skel files... "

cp -R "skel/"* "company/"$input"/"

echo "Done!"
