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

import uk.ac.open.crc.intt.IdentifierNameTokeniser;
import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;
import uk.ac.open.crc.intt.DictionaryConfiguration;

/**
 * The class is the entry point to the library. First create an instance 
 * of {@code Jim} and then use the {@code create()} method to 
 * obtain instances of {@code NameExtractor} that process Java files
 * To extract naming data.
 */
public class Jim {

    private final IdentifierNameTokeniserFactory inttFactory;
    private final IdentifierNameTokeniser intt;
    private TokenisationStrategy strategy = TokenisationStrategy.FULL;

    /**
     * Creates an instance of {@code Jim} which can be used to create
     * {@code NameExtractor} instances. 
     */
    public Jim() {
	this.inttFactory = new IdentifierNameTokeniserFactory();
	this.intt = this.inttFactory.create();
    }
    
    
    /**
     * Creates an instance of {@code Jim} initialised with a specific 
     * set of word lists which are used to create
     * {@code NameExtractor} instances. 
     * @param dc identifies a set of word lists to use with intt
     */
    public Jim( DictionaryConfiguration dc ) {
        this.inttFactory = new IdentifierNameTokeniserFactory( dc );
	this.intt = this.inttFactory.create();
    }
    
    /**
     * Selects a tokenisation strategy to use. FULL is the default.
     * @param strategy a tokenisation strategy
     */
    public Jim( TokenisationStrategy strategy ) {
        this();
        this.strategy = strategy;
    }
    
    /**
     * Creates an instance of {@code Jim} initialised with a specific 
     * set of word lists, which are used to create
     * {@code NameExtractor} instances, and a tokenisation strategy to use. 
     * @param dc identifies a set of word lists to use with intt
     * @param strategy a tokenisation strategy for intt
     */
    public Jim( DictionaryConfiguration dc, TokenisationStrategy strategy ) {
        this( dc );
        this.strategy = strategy;
    }
    
    
    /**
     * Creates instances of {@code NameExtractor} using the provided settings.
     * @return an instance of {@code NameExtractor} 
     */
    public NameExtractor create() {
	return new NameExtractor( this.intt, this.strategy );
    }
    
}
