### TEXT CONVERTER  ###

### ABOUT ###
Application for converting any text data file into another file format.
Converted text is divided into sentences, and sentences are divided into words, words are sorted alphabetically
Supported formats: csv, xml

### REQUIREMENTS ###

Java 8 installed (or higher)
Maven 3 (3.5.3 used in project)

### BUILD ###
Run from project root directory:

mvn clean install

### RUN ###
Run from project root directory:

java -jar target/text-converter-1.0-SNAPSHOT.jar [source] [destination]

[source] - input text file location
[destination] - output file after convertion

sample:

java -jar target/text-converter-1.0-SNAPSHOT.jar input.in output.csv