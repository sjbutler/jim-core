/*
    Copyright (C) 2018-2019 Simon Butler

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

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import uk.ac.open.crc.intt.IdentifierNameTokeniser;
import uk.ac.open.crc.intt.TaggedToken;

/**
 * A class containing the identifier name data extracted from a Java source 
 * code file. Instances contain a little metadata, and the identifier names 
 * declared in the file. The names have also been tokenised and the tokens 
 * are also made available. 
 */
public class FileData {
    private final List<String> names;
    private final List<String> tokens; 
    private final TokenisationStrategy strategy;
    
    private final List<TokenisedName> tokenisedNames;
    
    private final String systemPathToFile;
    private final String fileName;
    private final String packageName;
    
    private final IdentifierNameTokeniser tokeniser;
    
    private final RawFileData rawData;
    
    FileData(IdentifierNameTokeniser tokeniser, RawFileData extractedData, TokenisationStrategy strategy) {
        this.names = new ArrayList<>();
	this.tokens = new ArrayList<>();
        this.tokenisedNames = new ArrayList<>();
	this.tokeniser = tokeniser;
        this.strategy = strategy;
	this.rawData = extractedData;
	
	this.systemPathToFile = this.rawData.fileName();
	int separatorIndex = this.systemPathToFile.lastIndexOf( File.separator );
	if (separatorIndex != -1 ) {
	    this.fileName = this.rawData.fileName().substring( separatorIndex + 1 );
	}
	else {
	    this.fileName = this.systemPathToFile;
	}
	this.packageName = this.rawData.packageName();
	this.rawData.topLevelEntities().forEach( pe -> getNames(pe) );
        // tokens list populated by #getFullTokenisation
	this.names.forEach( n -> tokeniseWithOrigins( n ) );
//        this.names.forEach(n -> getFullTokenisation( n ) );
    }
    
    /**
     * The name of the file processed.
     * @return a file name
     */
    public String fileName() {
	return this.fileName;
    }
    
    /**
     * The package name declared in the processed file.
     * @return a package name
     */
    public String packageName() {
	return this.packageName;
    }
    
    /**
     * The full Java file name {e.g.} {@code java.util.ArrayList.java}.
     * @return the Java name of the file including the package
     */
    public String javaFileName() {
	return this.packageName + "." + this.fileName;
    }
    
    /**
     * The path and name of the source code file on the file system. 
     * @return the full path and file name used to access the source file
     */
    public String systemFileName() {
	return this.systemPathToFile;
    }
    
    /**
     * Retrieves a list of the identifier names declared in the 
     * processed file.
     * @return a {@code List} of names
     */
    public List<String> names() {
	return this.names;
    }
    
    
    /**
     * Recovers a list of the tokens found in identifier names declared
     * in the processed file.
     * @return a {@code List} of tokens
     */
    public List<String> tokens() {
	return this.tokens;
    }
    
    
    /**
     * Recovers a list of names of the identifier names declared in the 
     * processed file and the tokens returned tokenisation
     * with information about the origins of the tokens.
     * @return a list of {@code TokenisedName} instances
     */
    public List<TokenisedName> tokenisedNames() {
        return this.tokenisedNames;
    }
    
    /**
     * Recovers the set of tokens extracted from identifier names in the file.
     * @return a {@code Set} of tokens
     */
    public Set<String> tokenSet() {
	return new HashSet<>(this.tokens);
    }
    
    private void getNames(ProgramEntity entity) {
	this.names.add( entity.identifierName());
	entity.children().forEach( e -> getNames(e) );
    }
    
    private void tokenise( String name ) {
	this.tokeniser.tokenise( name )
		.forEach( t -> this.tokens.add( t.toLowerCase() ) );
    }
        
    private void tokeniseWithOrigins( String name ) {
        List<TaggedToken> taggedTokens;
        switch (this.strategy) {
            case FULL:
                taggedTokens = this.tokeniser.tokeniseWithOrigins( name );
                break;
                
            case SIMPLE:
                taggedTokens = this.tokeniser.naiveTokenisationWithOrigins( name );
            break;
            
            default:
                throw new IllegalStateException("Unrecognised tokenisation option!!");
        }
        this.tokenisedNames.add( new TokenisedName( name, taggedTokens ) );
        taggedTokens.forEach( t -> this.tokens.add( t.getContent().toLowerCase() ) );
    }
        
}
