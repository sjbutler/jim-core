/*
    Copyright (C) 2018 Simon Butler

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
package a.name;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTest {
    public void aMethod( String parameter ) {
	String localString = parameter;
	
	List<String> collection = new ArrayList<String>();
	
	for( int forLoopCounter = 0; forLoopCounter < 10; forLoopCounter++ ) {
	    collection.add(localString);
	}
	
	for ( String forLoopVariable : collection ) {
	    System.out.println( forLoopVariable);
	}
	
    }
}