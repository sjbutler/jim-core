/*
    Copyright (C) 2017-2018 Simon Butler

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

import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.open.crc.idtk.Species;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 *
 * 
 */
public class FileDataBuilderTest {
    
    /**
     * Test of add method, of class FileDataBuilder.
     */
    @Test
    public void basicInstantiationAndAddTest () {
	ProgramEntity entity = new ProgramEntity(Species.CLASS, "AClass");
	RawFileData fileData = new RawFileData("a-file.name");
	FileDataBuilder builder = new FileDataBuilder( fileData );
	builder.add( entity );
	ProgramEntity anotherEntity = new ProgramEntity(Species.FIELD, "aField");
	builder.add( anotherEntity );
	
	assertThat("Expected one top level entity", 
		fileData.topLevelEntities(), 
		hasSize(1));
	
	ProgramEntity rootContainer = fileData.topLevelEntities().get(0);
	assertThat("Unexpected name returned by root class",
		rootContainer.identifierName(), 
		is("AClass"));
	assertThat("Expected only one child", 
		rootContainer.children(), 
		hasSize(1));
	ProgramEntity expectedField = rootContainer.children().get( 0 );
	assertThat("Expected field entity", 
		expectedField.species(), 
		is(Species.FIELD));
	assertThat("Unexpected name returned by field entity", 
		expectedField.identifierName(), 
		is("aField"));
    }

    /**
     * Test of addAsContainer method, of class FileDataBuilder.
     */
    @Test
    public void simpleAddContainerTest () {
	ProgramEntity entity = new ProgramEntity(Species.CLASS, "AClass");
	RawFileData fileData = new RawFileData("a-file.name");
	FileDataBuilder builder = new FileDataBuilder( fileData );
	builder.add( entity );
	ProgramEntity anotherEntity = new ProgramEntity(Species.FIELD, "aField");
	builder.add( anotherEntity );
	ProgramEntity aConstructorEntity = new ProgramEntity(Species.CONSTRUCTOR, "aConstructor");
	builder.addAsContainer( aConstructorEntity );
	ProgramEntity aMethodEntity = new ProgramEntity(Species.METHOD, "aMethod");
	builder.addAsContainer( aMethodEntity );
	
	assertThat("Expected one top level entity", 
		fileData.topLevelEntities(), 
		hasSize(1));
	
	ProgramEntity rootContainer = fileData.topLevelEntities().get(0);
	assertThat("Expected two children", 
		rootContainer.children(), 
		hasSize(2));
	ProgramEntity expectedMethod = builder.getCurrentContainer();
	assertThat("Expected method entity", 
		expectedMethod.species(), 
		is(Species.METHOD));
    }

    
    @Test
    public void simpleMoveToParentTest () {
	ProgramEntity entity = new ProgramEntity(Species.CLASS, "AClass");
	RawFileData fileData = new RawFileData("a-file.name");
	FileDataBuilder builder = new FileDataBuilder( fileData );
	builder.add( entity );
	ProgramEntity anotherEntity = new ProgramEntity(Species.FIELD, "aField");
	builder.add( anotherEntity );
	ProgramEntity aConstructorEntity = 
		new ProgramEntity(Species.CONSTRUCTOR, "aConstructor");
	builder.add( aConstructorEntity );
	ProgramEntity aMethodEntity = new ProgramEntity(Species.METHOD, "aMethod");
	builder.addAsContainer( aMethodEntity );
	
	ProgramEntity expectedMethod = builder.getCurrentContainer();
	assertThat("Expected method entity", 
		expectedMethod.species(), 
		is(Species.METHOD));

	ProgramEntity parentContainer = builder.moveToParent();
	assertThat("Null parent container returned by moveToParent", 
		parentContainer, 
		notNullValue() );
	assertThat("Expected class", 
		parentContainer.species(), 
		is(Species.CLASS));
	assertThat("Expected parent to be root container class", 
		parentContainer, 
		is(entity));
    }


    // TODO - move to parent failure - currently throws UOE
    // Determine whether to retain the exception for production
    // and if so, whether the exception should be changed
    @Test(expected=UnsupportedOperationException.class)
    public void unpopulatedTreeMoveToNonExistentParent() {
	RawFileData fileData = new RawFileData("a-file.name");
	FileDataBuilder builder = new FileDataBuilder( fileData );
	
	builder.moveToParent();
	fail("Move to non-existent parent in unpopulated tree "
		+ "should throw exception.");
    }
    
    
    // TODO - move to parent failure - currently throws UOE
    // Determine whether to retain the exception for production
    // and if so, whether the exception should be changed
    @Test(expected=UnsupportedOperationException.class)
    public void populatedTreeMoveToNonExistentParent() {
	RawFileData fileData = new RawFileData("a-file.name");
	FileDataBuilder builder = new FileDataBuilder( fileData );
	ProgramEntity entity = new ProgramEntity(Species.CLASS, "AClass");
	builder.add( entity );
	
	builder.moveToParent();
	// now stack is empty, move again
	builder.moveToParent();
	fail("Move to non-existent parent beyond root element "
		+ "should throw exception.");
    }  
    
}
