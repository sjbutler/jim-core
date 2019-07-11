/*
    Copyright (C) 2017-2019 Simon Butler

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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import uk.ac.open.crc.intt.IdentifierNameTokeniser;
import uk.org.facetus.jim.core.parser.java8.Java8Lexer;
import uk.org.facetus.jim.core.parser.java8.Java8Parser;
import uk.org.facetus.jim.core.parser.java8.Java8Visitor;
import uk.org.facetus.jim.core.parser.java8.Java8VisitorImplementation;

/**
 * A worker class to extract names from a Java file. Instances are 
 * created by the {@code Jim} class
 */
public class NameExtractor {
    
    private final IdentifierNameTokeniser tokeniser;
    private final TokenisationStrategy strategy;

    NameExtractor(IdentifierNameTokeniser tokeniser, TokenisationStrategy strategy) {
	this.tokeniser = tokeniser; 
        this.strategy = strategy;
    }
    
    /**
     * Parses the specified Java file to extract names. {@code FileData} object 
     * returned and the form of its contents are specified using the creator 
     * objects.
     * @param fileName absolute path of the file to parse
     * @return a {@code RawFileData} object 
     * @throws FileNotFoundException thrown by ANTLR
     * @throws IOException thrown by ANTLR 
     * @throws JimParserException aggregates ANTLR lexer and parser exceptions
     */
    public FileData process( String fileName ) 
	    throws FileNotFoundException, IOException, JimParserException {
	RawFileData rawFileData = new RawFileData(fileName); 
	return process( rawFileData, new FileInputStream( fileName ) );
    }
    
    FileData process( RawFileData data, InputStream is ) throws IOException, JimParserException {
        try {
            CharStream input = CharStreams.fromStream( is );
            Java8Lexer lexer = new Java8Lexer( input );
            CommonTokenStream tokens = new CommonTokenStream( lexer );
            Java8Parser java8Parser = new Java8Parser( tokens );
            java8Parser.removeErrorListeners();
            java8Parser.setErrorHandler( new BailErrorStrategy() );
            ParseTree parseTree = java8Parser.compilationUnit();  // grammar root.

            Java8Visitor java8Visitor = 
                    new Java8VisitorImplementation( data );
            java8Visitor.visit( parseTree );
            return new FileData( this.tokeniser, data, strategy );
        }
        catch ( RuntimeException e ) {
            Throwable cause = e.getCause();
            if ( cause instanceof RecognitionException 
                    || cause instanceof InputMismatchException ) {
                throw new JimParserException( e );
            }
            else {
                throw e ;
            }
        }
    }
    
    // Error strategy class for ANTLR see ANTLR 4 book pp172-3
    // this forces ANTLR to bail out on a syntax error
    // instead of trying to recover.
    public class BailErrorStrategy extends DefaultErrorStrategy {
        
        // wrap and rethrow exception
        @Override
        public void recover( Parser recognizer, RecognitionException e ) {
            throw new RuntimeException( e );
        }
        
        // ensure that attempts are not made to recover inline
        @Override
        public org.antlr.v4.runtime.Token recoverInline( Parser recognizer ) throws RecognitionException {
            throw new RuntimeException( new InputMismatchException( recognizer ));
        }
        
        // prevents recovery from problems in subrules
        @Override
        public void sync( Parser recognizer ) {}
    }
    
    
    // error listener to ensure ANTLR errors are sent to log
    // see ANTLR 4 book p154
//    public class LogListener extends BaseErrorListener {
//        @Override
//        public void syntaxError( 
//                Recognizer<?,?> recognizer, 
//                Object offendingSymbol, 
//                int line, 
//                int charPositionInLine, 
//                String msg, 
//                RecognitionException e ) {
//            List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
//            Collections.reverse( stack );
//            LOGGER.warn( "In file: {}", javaFile);
//            LOGGER.warn( "Rule stack: {}", stack);
//            LOGGER.warn( 
//                    "Line: {}\nSymbol: \"{}\"\nMessage: {}", 
//                    line, offendingSymbol, msg );
//        } 
//   }
}

 