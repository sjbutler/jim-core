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
import org.antlr.runtime.RecognitionException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests for exceptions with BrokenSyntax* test classes.
 *
 */
public class JimCoreParserExceptionTest {

    private static final String JAVA_TEST_FILE_FOLDER = "/testfiles/";

    @Test(expected = JimParserException.class)
    public void JimParserExceptionLexerTest() throws JimParserException {
        Jim library = new Jim();

        NameExtractor n = library.create();

        try {
            FileData d = n.process(
                    new RawFileData("BrokenSyntaxLexer.java"),
                    JimCoreTest.class.getResourceAsStream(
                            JAVA_TEST_FILE_FOLDER + "BrokenSyntaxLexer.java"));
            fail("Expected JimParserException to be thrown");

        } 
        catch (IOException e) {
            fail( "unable to access test class BrokenSyntaxLexer" );
        }

    }

    
    // 
    // this test catches a AssertionError thrown when parsing a malformed
    // file. 
    // really this should be an exception, so this test acts as a canary
    // for a possible correction being made to ANTLR. 
    @Test(expected = AssertionError.class)
    public void JimParserExceptionParserTest()  {
        Jim library = new Jim();

        NameExtractor n = library.create();

        try {
            FileData d = n.process(
                    new RawFileData("BrokenSyntaxParser.java"),
                    JimCoreTest.class.getResourceAsStream(
                            JAVA_TEST_FILE_FOLDER + "BrokenSyntaxParser.java"));
            fail("had anticipated JimParserException to be thrown");

        } catch (IOException e) {
            fail( "unable to access test class BrokenSyntaxParser" );
        } 
        catch ( JimParserException e ) {
            fail( "JimParserException thrown when AssertionError expected." );
        }

    }

}
