/*
    Copyright (C) 2018 Simon Butler

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

package uk.org.facetus.jim.core;

import java.io.IOException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Ignore;


/**
 * Provides tests for simple Java 8 language structures. 
 * 
 */
public class JimCoreTests {
    private static final String JAVA_TEST_FILE_FOLDER = "/testfiles/";
    @Test
    public void simpleEmptyClassTest() {
	Jim library = new Jim();
	
	NameExtractor n = library.create();
	
	try {
	    FileData d = n.process( 
		    new RawFileData("SimpleEmptyClassTest.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "SimpleTestEmptyClass.java"));
	    assertThat("Expected to find only one identifier names", 
		    d.names().size(), 
		    is(1));
	    assertThat("Failed to recover class name", 
		    d.names(), 
		    hasItem("SimpleTestEmptyClass"));
	    assertThat("simple not recovered from class name", 
		    d.tokens(), 
		    hasItem("simple"));
	    assertThat("empty not recovered from class name", 
		    d.tokens(), 
		    hasItem("empty"));
	    assertThat("class not recovered from class name", 
		    d.tokens(), 
		    hasItem("class"));
	    assertThat("test not recovered from class name", 
		    d.tokens(), 
		    hasItem("test"));
	    assertThat(
		    String.format("Unexpected number of tokens returned. "
			    + "Found %d tokens, expected 4", d.tokens().size()), 
		    d.tokens().size(), 
		    is(4));
	    // the package name is 'none'
	    assertThat(
		    String.format( "Expected package name to be 'none'. "
			    + "Instead found '%s'", d.packageName()), 
		    d.packageName(), 
		    is("none"));
	}
	catch (IOException e) {
	    System.err.println( "unable to access test class SimpleEmptyClassTest" );
	}
    }
    
    @Test
    public void constructorAndMethodTest() {
	Jim library = new Jim();
	NameExtractor n = library.create();
	
	try {
	    FileData d = n.process( 
		    new RawFileData("SimpleConstructorAndMethodTest.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "SimpleConstructorAndMethodTest.java"));
	    assertThat("Did not find the method name 'aMethod'", 
		    d.names(), 
		    hasItem("aMethod"));
	    // note that in the simple returned data the constructor name cannot
	    // be explicitly identified
	    int counter = 0;
	    counter = d.names().stream()
		    .filter( name -> ("SimpleConstructorAndMethodTest"
			    .equals( name )) )
		    .map( _item -> 1 )
		    .reduce( counter, Integer::sum );
	    assertThat("Only collected class or constructor name, not both", 
		    counter, 
		    is(2));
	    // there is no package name set in this test file
	    // and the package name should be an empty string
	    assertThat(
		    String.format( "Empty package name expected "
			    + "found '%s' instead", d.packageName()), 
		    d.packageName().isEmpty(), 
		    is(true));
	}
	catch (IOException e) {
	    System.err.println( "unable to access test class SimpleConstructorAndMethodTest" );
	}
    }
    
    
    
    @Test
    public void lambdaTest() {
	Jim library = new Jim();
	NameExtractor n = library.create();
	
	try {
	    FileData d = n.process( 
		    new RawFileData("LambdaPlain.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "LambdaPlain.java"));
	    assertThat("Did not find the lambda parameter 'line'", 
		    d.names(), 
		    hasItem("line"));
	    assertThat("Did not find the lambda parameter 'anotherLine'", 
		    d.names(), 
		    hasItem("anotherLine"));
	}
	catch (IOException e) {
	    System.err.println( "unable to access test class LambdaPlain" );
	}
    }
    
    @Ignore
    @Test
    public void annotationTest() {
	Jim library = new Jim();
	NameExtractor n = library.create();
	
	try {
	    FileData d = n.process( 
		    new RawFileData("AnnotationTest.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "AnnotationTest.java"));
	    assertThat("Did not find the lambda parameter 'line'", 
		    d.names(), 
		    hasItem("line"));
	    assertThat("Did not find the lambda parameter 'anotherLine'", 
		    d.names(), 
		    hasItem("anotherLine"));
	}
	catch (IOException e) {
	    System.err.println( "unable to access test class AnnotationTest" );
	}
	
    }
    	
    
    @Test
    public void EnumerationTest() {
	Jim library = new Jim();
	NameExtractor n = library.create();
	
	try {
	    FileData d = n.process( 
		    new RawFileData("EnumerationTest.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "EnumerationTest.java"));
	    assertThat("Did not find the enumeration member 'ENUMERATION_MEMBER_ONE'", 
		    d.names(), 
		    hasItem("ENUMERATION_MEMBER_ONE"));
	    assertThat("Did not find the enumeration method 'aMethod'", 
		    d.names(), 
		    hasItem("aMethod"));
	    assertThat("Did not find the enumeration 'EnumerationTest'", 
		    d.names(), 
		    hasItem("EnumerationTest"));
	    assertThat("Did not find the enumeration field 'description'", 
		    d.names(), 
		    hasItem("description"));
	}
	catch (IOException e) {
	    System.err.println( "unable to access test class EnumerationTest" );
	}
    }
    
    @Test
    public void interfaceTest() {
	Jim library = new Jim();
	NameExtractor n = library.create();
	
	try {
	    FileData d = n.process( 
		    new RawFileData("InterfaceTest.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "InterfaceTest.java"));
	    assertThat("Did not find the method 'anInterfaceMethod'", 
		    d.names(), 
		    hasItem("anInterfaceMethod"));
	    assertThat("Did not find the interface method parameter 'text'", 
		    d.names(), 
		    hasItem("text"));
	    assertThat("Did not find the method 'anInterfaceMethod'", 
		    d.names(), 
		    hasItem("anInterfaceMethod"));
	    assertThat("Did not find the interface default method 'defaultMethod'", 
		    d.names(), 
		    hasItem("defaultMethod"));
	    assertThat("Did not find the interface local variable 'someText'", 
		    d.names(), 
		    hasItem("someText"));
	    assertThat("Did not find the interface constant 'A_CONSTANT'", 
		    d.names(), 
		    hasItem("A_CONSTANT"));
	}
	catch (IOException e) {
	    System.err.println( "unable to access test class InterfaceTest" );
	}
	
    }
    
    
    @Test
    public void localVariableTest() {
	Jim library = new Jim();
	NameExtractor n = library.create();
	
	try {
	    FileData d = n.process( 
		    new RawFileData("LocalVariableTest.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "LocalVariableTest.java"));
	    assertThat("Did not find the local variable parameter 'localString'", 
		    d.names(), 
		    hasItem("localString"));
	    assertThat("Did not find the local variable 'collection'", 
		    d.names(), 
		    hasItem("collection"));
	    assertThat("Did not find the loop counter 'forLoopCounter'", 
		    d.names(), 
		    hasItem("forLoopCounter"));
	    assertThat("Did not find the loop variable 'forLoopVariable'", 
		    d.names(), 
		    hasItem("forLoopVariable"));
	}
	catch (IOException e) {
	    System.err.println( "unable to access test class LocalVariableTest" );
	}
	
    }
    
    @Test
    public void nestedClassTest() {
	Jim library = new Jim();
	NameExtractor n = library.create();
	
	try {
	    FileData d = n.process( 
		    new RawFileData("NestedClassTest.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "NestedClassTest.java"));
	    assertThat("Did not find the nested class 'InnerClass'", 
		    d.names(), 
		    hasItem("InnerClass"));
	    assertThat("Did not find the nested class 'InnerInnerClass'", 
		    d.names(), 
		    hasItem("InnerInnerClass"));
	}
	catch (IOException e) {
	    System.err.println( "unable to access test class NestedClassTest" );
	}
	
    }
    
    @Test
    public void moreThanOneTopLevelClassTest() {
	Jim library = new Jim();
	NameExtractor n = library.create();
	
	try {
	    FileData d = n.process( 
		    new RawFileData("MoreThanOneTopLevelClass.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "MoreThanOneTopLevelClass.java"));
	    assertThat(
		    String.format("Expected 5 names, found '%d' instead.", 
			    d.names().size()), 
		    d.names().size(), 
		    is(5));
	    
	    assertThat("Failed to recover class name: MoreThanOneTopLevelClass", 
		    d.names(), 
		    hasItem("MoreThanOneTopLevelClass"));
	    assertThat("Failed to recover class name: ASecondClass", 
		    d.names(), 
		    hasItem("ASecondClass"));
	    assertThat("Failed to recover class name: AThirdClass", 
		    d.names(), 
		    hasItem("AThirdClass"));
	    assertThat("Failed to recover class name: AFourthClass", 
		    d.names(), 
		    hasItem("AFourthClass"));
	    assertThat("Failed to recover class name: AFifthClass", 
		    d.names(), 
		    hasItem("AFifthClass"));
	}
	catch (IOException e) {
	    System.err.println( "unable to access test class MoreThanOneTopLevelClass" );
	}
    }
}
