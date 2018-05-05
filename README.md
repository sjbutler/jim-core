# JIM Core
jim-core provides the core functionality of a Java identifier name miner, a tool to extract identifier names from Java source code files for later analysis. jim-core currently processes Java code that conforms to Java 8. Java 9 processing is not yet implemented

## Alpha
jim-core is  currently being developed and is very much alpha software. It is error-prone and the API is subject to change, especially as functionality is added.

## Usage
Binaries are released to Maven central, so Gradle users should add the following line to their build file:

  ``compile 'uk.org.facetus:jim-core:0.0.1'``

The API is currently minimalist and easy to use. For example:

```  
    Jim library = new Jim();
    NameExtractor n = library.create();
    try {
        FileData d = n.process( "FileName.java"));
		...
	}
	catch (IOException e) { ... }
```

The ``FileData`` class provides the methods, one that returns the list of identifier names, one that returns a list of the tokens found in the names, and another that returns the set of tokens. Names retain the capitalisation used by the software developers and the tokens are normalised to lower case. 

## Requirements and Dependencies

The jim-core library requires Java 8 to run. It has not been tested with Java 9 (yet). 

jim-core uses ANTLR4 to parse Java and the Apache Commons Collections v4. Identifier name processing relies on intt and idtk-j. Logging relies on SLF4J. JUnit 4 and Hamcrest 1.3 are used for unit testing. The following illustrates the dependencies for Gradle.

```   
    api 'org.slf4j:slf4j-api:1.7.25'
    api 'org.slf4j:slf4j-jdk14:1.7.25'
    compile 'org.antlr:antlr4-runtime:4.7.1'
    compile 'uk.org.facetus:idtk-j:0.5.2'
    compile 'uk.org.facetus:intt:0.8.1'
    compile 'org.apache.commons:commons-collections4:4.1'
    
    testImplementation 'junit:junit:4.12'
    testCompile 'org.hamcrest:hamcrest-library:1.3'
```

## Build
A Gradle build script and a pom.xml are provided. For other build systems see the list of dependencies above. 


