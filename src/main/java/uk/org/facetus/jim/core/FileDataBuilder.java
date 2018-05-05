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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Provides a mechanism for tracking containing program entities 
 * while collecting data in the visitor.
 *
 */
public class FileDataBuilder {
private static final Logger LOGGER
            = LoggerFactory.getLogger(FileDataBuilder.class );
    private final RawFileData fileData;
    private final ArrayDeque<ProgramEntity> stack;

    private final ArrayList<String> typeStack;

    private ProgramEntity current = null;  // remove if this proves unnecessary!!
    
    /** 
     * Do not use this class directly.
     * @param fileData an internal data collection object
     */
    public FileDataBuilder (final RawFileData fileData) {
	this.fileData = fileData;
        this.stack = new ArrayDeque<>();
        this.typeStack = new ArrayList<>();
    }

    
    
    /**
     * Adds a program entity to the data object.
     * @param entity a program entity
     */
    public void add( ProgramEntity entity ) {
	if (stack.isEmpty()) {
	    this.current = entity;
	    this.stack.push( entity );
	    this.fileData.add( entity );
	}
	else {
	    this.current = this.stack.peekFirst();
	    this.current.addChild( entity );
	}
    }
    
    /**
     * Adds a program entity as a container.
     * @param entity a program entity
     */
    public void addAsContainer( ProgramEntity entity ) {
	this.add( entity );
	this.stack.push( entity );
    }
    
    /**
     * Retrieves a reference to the current containing program entity.
     * @return the containing (or parent) program entity
     */
    public ProgramEntity getCurrentContainer() {
	this.current = this.stack.peekFirst();  // is this intermediary required? i.e. does this.current ever get used directly?
	return this.current;
    }
    
    /**
     * Moves from a child to its parent.
     * @return the containing  (or parent) program entity
     */
    public ProgramEntity moveToParent(){
	if ( this.stack.isEmpty() ) {
	    // catch the stupid during development, then remove before production
	    throw new UnsupportedOperationException( 
		    "attempted to recover parent entity from empty stack" );
	}
	else {
	    this.stack.pop();
	    this.current = this.stack.isEmpty() ? null : this.stack.peekFirst();
	}
	return this.current;
    }
    
    /**
     * Retrieves the name of the file being processed.
     * @return a {@code String} containing a file name
     */
    public String fileName() {
	return this.fileData.fileName();
    }
    
    /**
     * Stores the package name found by the parser/visitor.
     * @param packageName the name of the package 
     */
    public void packageName(String packageName) {
	this.fileData.packageName(packageName);
    }
}
