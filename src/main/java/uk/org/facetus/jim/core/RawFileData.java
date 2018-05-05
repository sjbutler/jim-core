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

/**
 * Internal class for collecting data from the visitor.
 * 
 */
public class RawFileData {
    private String packageName;
    private final String fileName;
    
    private final List<ProgramEntity> topLevelEntities;
    
    RawFileData(String fileName) {
	this.fileName = fileName;
	this.packageName = ""; 
	this.topLevelEntities = new ArrayList<>();
    }
    
    
    List<ProgramEntity> topLevelEntities() {
	return this.topLevelEntities;
    }
    
    /**
     * Adds a {@code programEntity} to the list of top level program entities.
     * No checks are made and this entity is expected to be a top-level class, 
     * interface, enum in the file. If it is not then the output will be 
     * partial. 
     * @param entity a top-level program entity
     * @return the {@code boolean} value returned by {@code List.add()}
     */
    public boolean add(ProgramEntity entity) {
	return this.topLevelEntities.add(entity);
    }
    
    public void packageName(String packageName) {
	this.packageName = packageName;
    }
    
    String packageName() {
	return this.packageName;
    }
    
    String fileName() {
	return this.fileName;
    }
    
}
