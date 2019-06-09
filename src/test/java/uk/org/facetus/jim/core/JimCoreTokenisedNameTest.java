/*
 * Copyright (C) 2019 Simon Butler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.org.facetus.jim.core;

import java.io.IOException;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the detailed tokenisation information.
 * 
 */
public class JimCoreTokenisedNameTest {
    private static final String JAVA_TEST_FILE_FOLDER = "/testfiles/";
    private static Jim defaultLibrary;
    private static Jim simpleLibrary;
    
    @BeforeClass
    public static void setUp() {
        defaultLibrary = new Jim();
        simpleLibrary = new Jim( TokenisationStrategy.SIMPLE );
    }
    
    @Test
    public void basicSimpleTest() {
        NameExtractor n = simpleLibrary.create();
        try {
            FileData d = n.process( 
		    new RawFileData("LocalVariableTest.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "LocalVariableTest.java"));
            
            List<TokenisedName> names = d.tokenisedNames();
            assertThat("Null list found", 
		    names, 
		    notNullValue());
            assertThat(
                    String.format("Expected to find seven names. Found %s", 
                            names.size()), 
                    names.size(), 
                    is(7));
            for ( TokenisedName name : names ) {
                List<Token> simpleTokenisation = name.tokens();
                assertThat(
                        String.format("Null simple tokenisation found for %s", name.name()),
                        simpleTokenisation, 
                        notNullValue());
                assertThat(
                        String.format("Empty token list found for simple tokenisation of %s", name.name()),
                        simpleTokenisation.size(), 
                        not(0));
                for ( Token t : simpleTokenisation ) {
                    assertThat(
                            String.format("Token content null in simple tokenisation of %s", name.name()), 
                            t.getContent(), 
                            notNullValue());
                    assertThat(
                            String.format("Null tag list for simple tokenisation in token \'%s\' in %s", t.getContent(), name.name() ),
                            t.wordLists(), 
                            notNullValue());
                    assertThat(
                            String.format("Empty tag list for simple tokenisation in token \'%s\' in %s",t.getContent(), name.name() ),
                            t.wordLists().size(), 
                            not(0));
                }
                                
            }
        }
	catch (IOException e) {
	    System.err.println( "unable to access test class LocalVariableTest" );
	}
    }
    
    
    @Test
    public void basicFullTest() {
        NameExtractor n = defaultLibrary.create();
        try {
            FileData d = n.process( 
		    new RawFileData("LocalVariableTest.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "LocalVariableTest.java"));
            
            List<TokenisedName> names = d.tokenisedNames();
            assertThat("Null list found", 
		    names, 
		    notNullValue());
            assertThat(
                    String.format("Expected to find seven names. Found %s", 
                            names.size()), 
                    names.size(), 
                    is(7));
            for ( TokenisedName name : names ) {
                List<Token> fullTokenisation = name.tokens();
                assertThat(String.format("Null fulll tokenisation found for %s", name.name()),
                        fullTokenisation, 
                        notNullValue());
                assertThat(String.format("Empty token list found for full tokenisation of %s", name.name()),
                        fullTokenisation.size(), 
                        not(0));
                
                for ( Token t : fullTokenisation ) {
                    assertThat(
                            String.format("Token content null in full tokenisation of %s", name.name()), 
                            t.getContent(), 
                            notNullValue());
                    assertThat(
                            String.format("Null tag list for full tokenisation in token \'%s\' in %s", t.getContent(), name.name() ),
                            t.wordLists(), 
                            notNullValue());
                    assertThat(
                            String.format("Empty tag list for full tokenisation in token \'%s\' in %s",t.getContent(), name.name() ),
                            t.wordLists().size(), 
                            not(0));
                }
                
            }
        }
	catch (IOException e) {
	    System.err.println( "unable to access test class LocalVariableTest" );
	}
    }
}
