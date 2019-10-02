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

import java.util.List;

import uk.ac.open.crc.intt.TaggedToken;

/**
 * Provides a representation of a token found in an identifier name consisting
 * of the token identifier by the splitter and a list of 0..n wordlists in 
 * which the token was found.
 */
public class Token {
    private final String content;
    private final List<String> wordLists;
    
    Token(TaggedToken tt) {
        this.content = tt.getContent();
        this.wordLists = tt.wordLists();
    }
    
    
    /** 
     * Recovers the token itself.
     * @return a string containing the token
     */
    public String getContent() {
        return this.content;
    }
    
    /**
     * Recovers a list of names of word lists in which the token is found.
     * @return a list of 0..n word list names.
     */
    public List<String> wordLists() {
        return this.wordLists;
    }
    
}
