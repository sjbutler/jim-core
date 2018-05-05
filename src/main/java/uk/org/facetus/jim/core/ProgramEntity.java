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

import java.util.ArrayList;
import java.util.List;
import uk.ac.open.crc.idtk.Species;
import uk.ac.open.crc.idtk.TypeName;

/**
 * Representation of a named program entity - a class, method, field etc - in
 * source code.
 *
 */
public class ProgramEntity {

    private final Species species;

    private final String identifierName; // this is the raw string in the code
    private final List<ProgramEntity> children;
    private final TypeName typeName;

    /**
     * Creates a program entity from some basic data.
     * @param species the {@code Species} of the entity
     * @param typeName the declared type name of the entity
     * @param identifierName the identifier name of the entity
     */
    public ProgramEntity ( Species species,
	    TypeName typeName,
	    String identifierName ) {
	this.species = species;
	this.children = new ArrayList<>();
	this.identifierName = identifierName;
	this.typeName = typeName;
    }

    /**
     * Creates a program entity.
     * @param species the {@code Species} of the entity
     * @param identifierName the identifier name of the entity
=     */
    public ProgramEntity ( Species species, String identifierName ) {
	this( species, null, identifierName );
    }

    boolean addChild ( ProgramEntity child ) {
	return this.children.add( child );
    }

    List<ProgramEntity> children () {
	return this.children;
    }
    
    String identifierName() {
	return this.identifierName;
    }
    
    Species species() {
	return this.species;
    }
}
