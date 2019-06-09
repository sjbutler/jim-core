/*
 * Copyright (C) 2019 Simon Butler.
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

import java.util.ArrayList;
import java.util.List;
import uk.ac.open.crc.intt.TaggedToken;

/**
 * Represents a tokenised identifier name as a name and a list of tokens. 
 * Each token in each list is tagged with a list of the word lists in 
 * which it was found. 
 * 
 */
public class TokenisedName {
    private String name;
    private List<Token> tokens;
    
    TokenisedName( String name, List<TaggedToken> taggedTokens ) {
        this.name = name;
        this.tokens = new ArrayList<>();
        taggedTokens.forEach( tt -> tokens.add(new Token( tt )) );
    }
    
    public String name() {
        return this.name;
    }
    
    public List<Token> tokens() {
        return this.tokens;
    }
    
}
