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

/**
 * Values that can be used to set the tokenisation strategy used by Jim. 
 * {@code FULL} runs the underlying name tokeniser in an aggressive setting 
 * where it tries to tokenise the name as best it can. {@code SIMPLE} selects 
 * a naive of conservative tokenisation where the tokeniser uses only separators 
 * and camel case (unambiguous boundaries) to tokenise names. The latter may 
 * be faster for most names, but relies on the assumption that names have a 
 * simple construction. 
 * 
 */
public enum TokenisationStrategy {
   FULL,
   SIMPLE;
}
