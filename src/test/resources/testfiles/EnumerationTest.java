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
package how.many.names.to.invent;

/**
 * Identifies program entity species and provides a number of utility methods 
 * for grouping species.
 */
public enum EnumerationTest {

    ENUMERATION_MEMBER_ONE( "enumeration member one" ),
    ENUMERATION_MEMBER_TWO( "enumeration member two" ),
    ENUMERATION_MEMBER_THREE( "enumeration member three" );

    private final String description;

    private EnumerationTest( String description ) {
        this.description = description;
    }

    public boolean isOneOrTwo() {
        return (this == ENUMERATION_MEMBER_ONE
                || this == ENUMERATION_MEMBER_TWO);
    }

    public String aMethod() {
        return this.description;
    }

}

