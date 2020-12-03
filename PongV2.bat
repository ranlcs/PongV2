@echo off
rem emplacement du dossier pong
cd "D:\Projet\Java\SourceMesClasses\Pongv2" 
javac -d bin *.java 
cd bin
java Frame
@echo on