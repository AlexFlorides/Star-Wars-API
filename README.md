# Star Wars API

Java program that requests and retrieves JSON API data about all characters of all the Star Wars movies from the website: https://swapi.dev/.

## Installing

* <ins>Clone or download repository: </ins>

        git clone https://github.com/AlexFlorides/Star-Wars-API.git


## Running Demo

First make sure you have install on you pc the latest version of Java found here:
    
    https://www.java.com/en/download/

<ins> WAY 1: </ins>

* Locate Star_Wars.jar file and double click or right click on it and press Open to run it

<ins> WAY 2: </ins>

* Locate Star_Wars.jar file and copy its path location. Open cmd and type the following:
cd "the copied path location of the file"
Then, write:
java -jar Star_Wars.jar

After the program opens, you can double click on any character name in the list on the left side of the program to see more details about that character on the right side of the program.

![demo](https://user-images.githubusercontent.com/47948084/95651054-18d56880-0af0-11eb-95cb-02c6952997ca.PNG)

## External Libraries Used:
* Apache Commons Logging:
    * http://commons.apache.org/proper/commons-logging/download_logging.cgi
* Gson:
    * https://search.maven.org/artifact/com.google.code.gson/gson/2.8.5/jar
* Hamcrest Core:
    * https://search.maven.org/artifact/org.hamcrest/hamcrest-core/1.3/jar
* Apache HttpClient:
    * https://search.maven.org/artifact/org.apache.httpcomponents/httpclient/4.5.8/jar
* Apache HttpCore:
    * https://javadoc.io/doc/org.apache.httpcomponents/httpcore/4.4.11/org/apache/http/protocol/HTTP.html
