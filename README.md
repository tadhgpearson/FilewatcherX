FilewatcherX
============

A simple command-line tool to validate that Java's Filewatcher is working correctly for you on a given system.
Useful for unusual mount types, identifying case where a Filewatcher execution thread may have got starved, etc.

To build
========
Requires Maven 3.x and Java 7.x or better

Run

    mvn clean package

to build the executable JAR

To Run
======

    java -jar <jarname> <folder_path_to_monitor>

For example

    java -jar target/FilewatcherX-1.0.jar ~/Downloads

Tip: This can usefully be run in the background by a server to compare expected and actual server behavior.
