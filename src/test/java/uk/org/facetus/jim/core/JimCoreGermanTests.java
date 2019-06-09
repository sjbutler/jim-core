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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import uk.ac.open.crc.intt.DictionaryConfiguration;

/**
 * Tests to exercise jim-core with a German dictionary loaded for intt.
 * 
 */
public class JimCoreGermanTests {
    private static final String JAVA_TEST_FILE_FOLDER = "/testfiles/";
    @Test
    public void simpleEmptyClassTest() {
	Jim library = new Jim( DictionaryConfiguration.GERMAN );
	
	NameExtractor n = library.create();
	
	try {
	    FileData d = n.process( 
		    new RawFileData("EinfacherLeereKlasseTest.java"), 
		    JimCoreTests.class.getResourceAsStream( 
		    JAVA_TEST_FILE_FOLDER + "EinfacherLeereKlasseTest.java"));
	    assertThat("Expected to find only one identifier name", 
		    d.names().size(), 
		    is(1));
	    assertThat("Failed to recover class name", 
		    d.names(), 
		    hasItem("EinfacherLeereKlasseTest"));
	    assertThat("einfacher not recovered from class name", 
		    d.tokens(), 
		    hasItem("einfacher"));
	    assertThat("leere not recovered from class name", 
		    d.tokens(), 
		    hasItem("leere"));
	    assertThat("klasse not recovered from class name", 
		    d.tokens(), 
		    hasItem("klasse"));
	    assertThat("test not recovered from class name", 
		    d.tokens(), 
		    hasItem("test"));
	    assertThat(
		    String.format("Unexpected number of tokens returned. "
			    + "Found %d tokens, expected 4", d.tokens().size()), 
		    d.tokens().size(), 
		    is(4));
	    // the package name is 'more.than.one.component'
	    assertThat(
		    String.format( "Expected package name to be 'mehr.als.eine.komponente'. "
			    + "Instead found '%s'", d.packageName()), 
		    d.packageName(), 
		    is("mehr.als.eine.komponente"));
	    // and check the java name of the file is 'mehr.als.eine.komponente.EinfacherLeereKlasseTest.java'
	    assertThat(
		    String.format( "Expected file name to be 'mehr.als.eine.komponente.EinfacherLeereKlasseTest.java'. "
			    + "Instead found '%s'", d.javaFileName()), 
		    d.javaFileName(), 
		    is("mehr.als.eine.komponente.EinfacherLeereKlasseTest.java"));
	    // and check the name of the file is 'EinfacherLeereKlasseTest.java'
	    assertThat(
		    String.format( "Expected file name to be 'EinfacherLeereKlasseTest.java'. "
			    + "Instead found '%s'", d.fileName()), 
		    d.fileName(), 
		    is("EinfacherLeereKlasseTest.java"));
	}
	catch (IOException e) {
	    System.err.println( "unable to access test class EinfacherLeereKlasseTest" );
	}
    }
}
