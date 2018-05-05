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


package uk.org.facetus.jim.core.parser.java8;

import java.util.ArrayDeque;
import java.util.ArrayList;
import org.antlr.v4.runtime.RuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.open.crc.idtk.Species;
import uk.ac.open.crc.idtk.TypeName;
import uk.org.facetus.jim.core.RawFileData;
import uk.org.facetus.jim.core.FileDataBuilder;
import uk.org.facetus.jim.core.ProgramEntity;

/**
 * Visitor. 
 * 
 */
public class Java8VisitorImplementation extends Java8BaseVisitor<String> {
    /**
     * An illegal Java identifier name used to positively mark program entities
     * that are anonymous.
     */
    private static final String ANONYMOUS = "#anonymous#";

    private static final String VOID = "void";

    private static final TypeName NO_TYPE = new TypeName( "#no type#" );

    private static final Logger LOGGER
            = LoggerFactory.getLogger( Java8VisitorImplementation.class );

    private static final int COLUMN_ADJUSTMENT = 1; // ANTLR counts from 0

    
    /**
     * A store for the FQNs of imported types
     */
    private final ArrayList<String> imports;

    /**
     * A store of FQNs of types declared within the parsed file
     */
//    private final ArrayList<String> locallyDeclaredTypes;

    private final ArrayDeque<Species> declarationContextStack = new ArrayDeque<>();
    private Species variableContext = null;
    
    private final FileDataBuilder dataBuilder;

    public Java8VisitorImplementation ( RawFileData fileData ) {
        super();

	this.imports = new ArrayList<>();
//        this.locallyDeclaredTypes = new ArrayList<>();

        this.dataBuilder = new FileDataBuilder(fileData);
    }

    	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitType(Java8Parser.TypeContext context) { 
            return visitChildren(context); 
        }
        
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitPrimitiveType(
		Java8Parser.PrimitiveTypeContext context) { 
            return visitChildren(context); 
        }
        
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitNumericType(
		Java8Parser.NumericTypeContext context) { 
            return visitChildren(context); 
        }
        
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitIntegralType(
		Java8Parser.IntegralTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitFloatingPointType(
		Java8Parser.FloatingPointTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitReferenceType(
		Java8Parser.ReferenceTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitClassOrInterfaceType(
		Java8Parser.ClassOrInterfaceTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitClassType(
		Java8Parser.ClassTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitClassType_lf_classOrInterfaceType(
		Java8Parser.ClassType_lf_classOrInterfaceTypeContext context) { 
	    // classType_lf_classOrInterfaceType
	    //		:	'.' annotation* Identifier typeArguments?
	    //		;
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitClassType_lfno_classOrInterfaceType(
		Java8Parser.ClassType_lfno_classOrInterfaceTypeContext context) { 
	    //	classType_lfno_classOrInterfaceType
	    //	    :	annotation* Identifier typeArguments?
	    //	    ;
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitInterfaceType(
		Java8Parser.InterfaceTypeContext context) { 
	    // interfaceType
	    //	    :	classType
	    //	    ;
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitInterfaceType_lf_classOrInterfaceType(
		Java8Parser.InterfaceType_lf_classOrInterfaceTypeContext context) { 
	    return visitChildren(context); 
	}
	
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitInterfaceType_lfno_classOrInterfaceType(
		Java8Parser.InterfaceType_lfno_classOrInterfaceTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitTypeVariable(
		Java8Parser.TypeVariableContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitArrayType(
		Java8Parser.ArrayTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitDims(Java8Parser.DimsContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitTypeParameter(
		Java8Parser.TypeParameterContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitTypeParameterModifier(
		Java8Parser.TypeParameterModifierContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitTypeBound(
		Java8Parser.TypeBoundContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitAdditionalBound(
		Java8Parser.AdditionalBoundContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitTypeArguments(
		Java8Parser.TypeArgumentsContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitTypeArgumentList(
		Java8Parser.TypeArgumentListContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitTypeArgument(
		Java8Parser.TypeArgumentContext context) { 
	    return visitChildren(context); 
	}

        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitPackageName(
		Java8Parser.PackageNameContext context) { 
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitTypeName(
		Java8Parser.TypeNameContext context) { 
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitPackageOrTypeName(
		Java8Parser.PackageOrTypeNameContext context) { 
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitExpressionName(
		Java8Parser.ExpressionNameContext context) { 
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitMethodName(
		Java8Parser.MethodNameContext context) { 
            
	    
	    return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitAmbiguousName(
		Java8Parser.AmbiguousNameContext context) { 
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitCompilationUnit(
		Java8Parser.CompilationUnitContext context) { 
	    //	compilationUnit
	    //	:	packageDeclaration? importDeclaration* typeDeclaration* EOF
	    //	;
            return visitChildren(context); 
        }

        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitPackageDeclaration(
		Java8Parser.PackageDeclarationContext context) { 
	    // packageDeclaration	
	    //      :	packageModifier* 'package' packageName ';'
	    //      ;
	    //
	    // packageName
	    //	:	Identifier
	    //	|	packageName '.' Identifier
	    //	;
            this.dataBuilder.packageName( context.packageName().Identifier().getText() );
            
            return visitChildren(context); 
        }
	
        //----------------
        // IMPORTS
        // Grammar is:
//        importDeclaration
//	:	singleTypeImportDeclaration
//	|	typeImportOnDemandDeclaration
//	|	singleStaticImportDeclaration
//	|	staticImportOnDemandDeclaration
//	;
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitImportDeclaration(
		Java8Parser.ImportDeclarationContext context) {
            // this is just a grouping production in the grammar
	    // individal imports are recovered from the children
            return visitChildren(context); 
        }
	
//        singleTypeImportDeclaration
//	:	'import' typeName ';'
//	;
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitSingleTypeImportDeclaration(
		Java8Parser.SingleTypeImportDeclarationContext context) { 
            imports.add(context.typeName().getText());
            return visitChildren(context); 
        }
	
//        typeImportOnDemandDeclaration
//	:	'import' packageOrTypeName '.' '*' ';'
//	;
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitTypeImportOnDemandDeclaration(
		Java8Parser.TypeImportOnDemandDeclarationContext context) { 
            imports.add(context.packageOrTypeName().getText() + ".*");
            return visitChildren(context); 
        }
	

//        singleStaticImportDeclaration
//	:	'import' 'static' typeName '.' Identifier ';'
//	;
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitSingleStaticImportDeclaration(
		Java8Parser.SingleStaticImportDeclarationContext context) { 
            imports.add(context.typeName().getText() + "." 
		    + context.Identifier().toString());
            return visitChildren(context); 
        }
	

//        staticImportOnDemandDeclaration
//	:	'import' 'static' typeName '.' '*' ';'
//	;
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitStaticImportOnDemandDeclaration(
		Java8Parser.StaticImportOnDemandDeclarationContext context) { 
            imports.add(context.typeName().getText() + ".*");
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitTypeDeclaration(
		Java8Parser.TypeDeclarationContext context) { 
            return visitChildren(context); 
        }
	
//	classDeclaration
//	:	normalClassDeclaration
//	|	enumDeclaration
//	;
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitClassDeclaration(
		Java8Parser.ClassDeclarationContext context) { 
            return visitChildren(context); 
        }
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext context) { 
	    //	normalClassDeclaration
	    //	:	classModifier* 'class' Identifier typeParameters? superclass? superinterfaces? classBody
	    this.dataBuilder.addAsContainer( 
		    new ProgramEntity(Species.CLASS, context.Identifier().getText()));
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitClassModifier(
		Java8Parser.ClassModifierContext context) { 
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitTypeParameters(
		Java8Parser.TypeParametersContext context) { 
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitTypeParameterList(
		Java8Parser.TypeParameterListContext context) { 
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitSuperclass(Java8Parser.SuperclassContext context) { 
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
        public String visitSuperinterfaces(
		Java8Parser.SuperinterfacesContext context) { 
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitInterfaceTypeList(
		Java8Parser.InterfaceTypeListContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitClassBody(
		Java8Parser.ClassBodyContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitClassBodyDeclaration(
		Java8Parser.ClassBodyDeclarationContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitClassMemberDeclaration(
		Java8Parser.ClassMemberDeclarationContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitFieldDeclaration(
		Java8Parser.FieldDeclarationContext context) { 
	    this.declarationContextStack.push( Species.FIELD);
	    visitChildren(context); 
	    this.declarationContextStack.pop();
	    return "";
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitFieldModifier(
		Java8Parser.FieldModifierContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitVariableDeclaratorList(
		Java8Parser.VariableDeclaratorListContext context) { 
	    // variableDeclaratorList
	    //	    :	variableDeclarator (',' variableDeclarator)*
	    //	    ;
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitVariableDeclarator(
		Java8Parser.VariableDeclaratorContext context) { 
	    // variableDeclarator
	    //	    :	variableDeclaratorId ('=' variableInitializer)?
	    //	    ;
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitVariableDeclaratorId(
		Java8Parser.VariableDeclaratorIdContext context) { 
	    //variableDeclaratorId
	    //	:	Identifier dims?
	    //	;
	    
	    // variableDeclaratorId is used in a variety of contexts
	    // The species for the context is set on entry to the containing context
	    // in the container's visitor. Possible contexts include
	    //   - grandchild of fieldDeclaration
	    //   - child of formalParameter
	    //   - child of lastFormalParameter
	    //   - child of enhancedForStatement
	    //   - child of encancedForStatementNoShortIf
	    //   - child of catchFormalParameter
	    //   - child of resource
	    //   - child of lambdaParameters
	    
	    String name = context.Identifier().getText();
	    this.dataBuilder.add( new ProgramEntity(this.declarationContextStack.peekFirst(), name) );
	    boolean isArrayDeclaration = (context.dims() != null);
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitVariableInitializer(
		Java8Parser.VariableInitializerContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitUnannType(
		Java8Parser.UnannTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitUnannPrimitiveType(
		Java8Parser.UnannPrimitiveTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitUnannReferenceType(
		Java8Parser.UnannReferenceTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitUnannClassOrInterfaceType(
		Java8Parser.UnannClassOrInterfaceTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitUnannClassType(
		Java8Parser.UnannClassTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitUnannClassType_lf_unannClassOrInterfaceType(
		Java8Parser.UnannClassType_lf_unannClassOrInterfaceTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitUnannClassType_lfno_unannClassOrInterfaceType(
		Java8Parser.UnannClassType_lfno_unannClassOrInterfaceTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitUnannInterfaceType(
		Java8Parser.UnannInterfaceTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitUnannInterfaceType_lf_unannClassOrInterfaceType(
		Java8Parser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitUnannInterfaceType_lfno_unannClassOrInterfaceType(
		Java8Parser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitUnannTypeVariable(
		Java8Parser.UnannTypeVariableContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitUnannArrayType(
		Java8Parser.UnannArrayTypeContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitMethodDeclaration(
		Java8Parser.MethodDeclarationContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitMethodModifier(
		Java8Parser.MethodModifierContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitMethodHeader(
		Java8Parser.MethodHeaderContext context) { 
	    // methodHeader
	    //	:	result methodDeclarator throws_?
	    //	|	typeParameters annotation* result methodDeclarator throws_?
	    //	;
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitResult(
		Java8Parser.ResultContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitMethodDeclarator(
		Java8Parser.MethodDeclaratorContext context) { 
	    // methodDeclarator
	    //	    :	Identifier '(' formalParameterList? ')' dims?
	    //	    ;
	    this.dataBuilder.addAsContainer( 
		    new ProgramEntity(Species.METHOD, 
			    context.Identifier().getText()));
	    this.declarationContextStack.push( Species.FORMAL_ARGUMENT);
	    visitChildren(context); 
	    this.declarationContextStack.pop();
	    return "";
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitFormalParameterList(
		Java8Parser.FormalParameterListContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitFormalParameters(
		Java8Parser.FormalParametersContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitFormalParameter(
		Java8Parser.FormalParameterContext context) { 
	    // formalParameter
	    //	    :	variableModifier* unannType variableDeclaratorId
	    //	    ;
	    this.declarationContextStack.push( Species.FORMAL_ARGUMENT);
	    visitChildren(context); 
	    this.declarationContextStack.pop();
	    return "";
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitVariableModifier(
		Java8Parser.VariableModifierContext context) { 
	    return visitChildren(context); 
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitLastFormalParameter(
		Java8Parser.LastFormalParameterContext context) { 
	    this.declarationContextStack.push( Species.FORMAL_ARGUMENT);
	    visitChildren(context); 
	    this.declarationContextStack.pop();
	    return "";
	}
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitReceiverParameter(
		Java8Parser.ReceiverParameterContext context) {
	    // can be safely ignored as it declares nothing
	    return visitChildren(context); 
	}
	
        /**
         * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitThrows_(
		// throws_
		//	:	'throws' exceptionTypeList
		//	;
		Java8Parser.Throws_Context context) { 
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitExceptionTypeList(
		Java8Parser.ExceptionTypeListContext context) { 
	    // exceptionTypeList
	    //	:	exceptionType (',' exceptionType)*
	    //	;
            return visitChildren(context); 
        }
	
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitExceptionType(
		Java8Parser.ExceptionTypeContext context) { 
	    // exceptionType
	    //		:	classType
	    //		|	typeVariable
	    //		;
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitMethodBody(
		Java8Parser.MethodBodyContext context) { 
	    visitChildren(context); 
	    this.dataBuilder.moveToParent();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitInstanceInitializer(
		Java8Parser.InstanceInitializerContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitStaticInitializer(
		Java8Parser.StaticInitializerContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitConstructorDeclaration(
		Java8Parser.ConstructorDeclarationContext context) { 
	    // constructorDeclaration
	    //	    :	constructorModifier* constructorDeclarator throws_? constructorBody
	    //	    ;
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitConstructorModifier(
		Java8Parser.ConstructorModifierContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitConstructorDeclarator(
		Java8Parser.ConstructorDeclaratorContext context) { 
	    // constructorDeclarator
	    //	    :	typeParameters? simpleTypeName '(' formalParameterList? ')'
	    //	    ;
	    this.dataBuilder.addAsContainer( new ProgramEntity(
		    Species.CONSTRUCTOR, 
		    context.simpleTypeName().Identifier().getText()));
	    this.declarationContextStack.push(Species.CONSTRUCTOR);
	    visitChildren(context); 
	    this.declarationContextStack.pop();
	    this.dataBuilder.moveToParent();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitSimpleTypeName(
		Java8Parser.SimpleTypeNameContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitConstructorBody(
		Java8Parser.ConstructorBodyContext context) { 
	    return visitChildren(context); 
	}
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitExplicitConstructorInvocation(
		Java8Parser.ExplicitConstructorInvocationContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitEnumDeclaration(
		Java8Parser.EnumDeclarationContext context) { 
	    this.dataBuilder.addAsContainer( new ProgramEntity(
		    Species.ENUMERATION, context.Identifier().getText()));
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitEnumBody(
		Java8Parser.EnumBodyContext context) { 
	    visitChildren(context); 
	    this.dataBuilder.moveToParent();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitEnumConstantList(
		Java8Parser.EnumConstantListContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitEnumConstant(
		Java8Parser.EnumConstantContext context) { 
	    this.dataBuilder.addAsContainer( new ProgramEntity(
		    Species.ENUMERATION_CONSTANT, context.Identifier().getText()));
	    visitChildren(context); 
	    this.dataBuilder.moveToParent();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitEnumConstantModifier(
		Java8Parser.EnumConstantModifierContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitEnumBodyDeclarations(
		Java8Parser.EnumBodyDeclarationsContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitInterfaceDeclaration(
		Java8Parser.InterfaceDeclarationContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitNormalInterfaceDeclaration(
		Java8Parser.NormalInterfaceDeclarationContext context) { 
	    // normalInterfaceDeclaration
	    //	    :	interfaceModifier* 'interface' Identifier typeParameters? extendsInterfaces? interfaceBody
	    //	    ;
	    this.dataBuilder.addAsContainer( new ProgramEntity(
		    Species.INTERFACE, context.Identifier().getText()));
	    visitChildren(context); 
	    this.dataBuilder.moveToParent();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitInterfaceModifier(
		Java8Parser.InterfaceModifierContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitExtendsInterfaces(
		Java8Parser.ExtendsInterfacesContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitInterfaceBody(
		Java8Parser.InterfaceBodyContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitInterfaceMemberDeclaration(
		Java8Parser.InterfaceMemberDeclarationContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitConstantDeclaration(
		Java8Parser.ConstantDeclarationContext context) { 
	    // constantDeclaration
	    //	:	constantModifier* unannType variableDeclaratorList ';'
	    //  ;
	    this.declarationContextStack.push( Species.FIELD);
	    visitChildren(context);
	    this.declarationContextStack.pop();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitConstantModifier(
		Java8Parser.ConstantModifierContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitInterfaceMethodDeclaration(
		Java8Parser.InterfaceMethodDeclarationContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitInterfaceMethodModifier(
		Java8Parser.InterfaceMethodModifierContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitAnnotationTypeDeclaration(
		Java8Parser.AnnotationTypeDeclarationContext context) { 
	    // annotationTypeDeclaration
	    //	    :	interfaceModifier* '@' 'interface' Identifier annotationTypeBody
	    //	    ;
	    this.dataBuilder.addAsContainer( new ProgramEntity(
		    Species.INTERFACE, context.Identifier().getText()));
	    visitChildren(context); 
	    this.dataBuilder.moveToParent();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitAnnotationTypeBody(
		Java8Parser.AnnotationTypeBodyContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitAnnotationTypeMemberDeclaration(
		Java8Parser.AnnotationTypeMemberDeclarationContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitAnnotationTypeElementDeclaration(
		Java8Parser.AnnotationTypeElementDeclarationContext context) { 
	    // annotationTypeElementDeclaration
	    //	    :	annotationTypeElementModifier* unannType Identifier '(' ')' dims? defaultValue? ';'
	    //	    ;
	    this.dataBuilder.add( new ProgramEntity(
		    Species.ANNOTATION_MEMBER, context.Identifier().getText()));
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitAnnotationTypeElementModifier(
		Java8Parser.AnnotationTypeElementModifierContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitDefaultValue(
		Java8Parser.DefaultValueContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitAnnotation(
		Java8Parser.AnnotationContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitNormalAnnotation(
		Java8Parser.NormalAnnotationContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitElementValuePairList(
		Java8Parser.ElementValuePairListContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitElementValuePair(
		Java8Parser.ElementValuePairContext context) { 
	    //    elementValuePair
	    //		:	Identifier '=' elementValue
	    //		;

	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitElementValue(
		Java8Parser.ElementValueContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitElementValueArrayInitializer(
		Java8Parser.ElementValueArrayInitializerContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitElementValueList(
		Java8Parser.ElementValueListContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitMarkerAnnotation(
		Java8Parser.MarkerAnnotationContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitSingleElementAnnotation(
		Java8Parser.SingleElementAnnotationContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitArrayInitializer(
		Java8Parser.ArrayInitializerContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitVariableInitializerList(
		Java8Parser.VariableInitializerListContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitBlock(Java8Parser.BlockContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitBlockStatements(
		Java8Parser.BlockStatementsContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitBlockStatement(
		Java8Parser.BlockStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitLocalVariableDeclarationStatement(
		Java8Parser.LocalVariableDeclarationStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitLocalVariableDeclaration(
		Java8Parser.LocalVariableDeclarationContext context) { 
	    // localVariableDeclaration
	    //		:	variableModifier* unannType variableDeclaratorList
	    this.declarationContextStack.push( Species.LOCAL_VARIABLE);
	    visitChildren(context); 
	    this.declarationContextStack.pop();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitStatement(Java8Parser.StatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitStatementNoShortIf(
		Java8Parser.StatementNoShortIfContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitStatementWithoutTrailingSubstatement(
		Java8Parser.StatementWithoutTrailingSubstatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitEmptyStatement(
		Java8Parser.EmptyStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitLabeledStatement(
		Java8Parser.LabeledStatementContext context) { 
	    this.dataBuilder.add( new ProgramEntity(
		    Species.LABEL, context.Identifier().getText()));
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitLabeledStatementNoShortIf(
		Java8Parser.LabeledStatementNoShortIfContext context) { 
	    this.dataBuilder.add( new ProgramEntity(
		    Species.LABEL, context.Identifier().getText()));
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitExpressionStatement(
		Java8Parser.ExpressionStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitStatementExpression(
		Java8Parser.StatementExpressionContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitIfThenStatement(
		Java8Parser.IfThenStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitIfThenElseStatement(
		Java8Parser.IfThenElseStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitIfThenElseStatementNoShortIf(
		Java8Parser.IfThenElseStatementNoShortIfContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitAssertStatement(
		Java8Parser.AssertStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitSwitchStatement(
		Java8Parser.SwitchStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitSwitchBlock(
		Java8Parser.SwitchBlockContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitSwitchBlockStatementGroup(
		Java8Parser.SwitchBlockStatementGroupContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitSwitchLabels(
		Java8Parser.SwitchLabelsContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitSwitchLabel(
		Java8Parser.SwitchLabelContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitEnumConstantName(
		Java8Parser.EnumConstantNameContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitWhileStatement(
		Java8Parser.WhileStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitWhileStatementNoShortIf(
		Java8Parser.WhileStatementNoShortIfContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitDoStatement(
		Java8Parser.DoStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitForStatement(
		Java8Parser.ForStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitForStatementNoShortIf(
		Java8Parser.ForStatementNoShortIfContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitBasicForStatement(
		Java8Parser.BasicForStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitBasicForStatementNoShortIf(
		Java8Parser.BasicForStatementNoShortIfContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitForInit(Java8Parser.ForInitContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitForUpdate(
		Java8Parser.ForUpdateContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitStatementExpressionList(
		Java8Parser.StatementExpressionListContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitEnhancedForStatement(
		Java8Parser.EnhancedForStatementContext context) { 
	    this.declarationContextStack.push(Species.LOCAL_VARIABLE);
	    visitChildren(context); 
	    this.declarationContextStack.pop();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitEnhancedForStatementNoShortIf(
		Java8Parser.EnhancedForStatementNoShortIfContext context) { 
	    this.declarationContextStack.push(Species.LOCAL_VARIABLE);
	    visitChildren(context); 
	    this.declarationContextStack.pop();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitBreakStatement(
		Java8Parser.BreakStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitContinueStatement(
		Java8Parser.ContinueStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitReturnStatement(
		Java8Parser.ReturnStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitThrowStatement(
		Java8Parser.ThrowStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitSynchronizedStatement(
		Java8Parser.SynchronizedStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitTryStatement(
		Java8Parser.TryStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitCatches(
		Java8Parser.CatchesContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitCatchClause(
		Java8Parser.CatchClauseContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitCatchFormalParameter(
		Java8Parser.CatchFormalParameterContext context) { 
	    this.declarationContextStack.push( Species.FORMAL_ARGUMENT);
	    visitChildren(context); 
	    this.declarationContextStack.pop();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitCatchType(
		Java8Parser.CatchTypeContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitFinally_(
		Java8Parser.Finally_Context context) { 
	    // finally_
	    //	:	'finally' block
	    //	;
	    this.declarationContextStack.push( Species.LOCAL_VARIABLE);
	    visitChildren(context);
	    this.declarationContextStack.pop();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitTryWithResourcesStatement(
		Java8Parser.TryWithResourcesStatementContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitResourceSpecification(
		Java8Parser.ResourceSpecificationContext context) { 
	    this.declarationContextStack.push( Species.LOCAL_VARIABLE);
	    visitChildren(context); 
	    this.declarationContextStack.pop();
	    return "";
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitResourceList(
		Java8Parser.ResourceListContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitResource(
		Java8Parser.ResourceContext context) { 
	    // resource
	    //	:	variableModifier* unannType variableDeclaratorId '=' expression
	    //	;

	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitPrimary(Java8Parser.PrimaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitPrimaryNoNewArray(
		Java8Parser.PrimaryNoNewArrayContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitPrimaryNoNewArray_lf_arrayAccess(
		Java8Parser.PrimaryNoNewArray_lf_arrayAccessContext context) { 
	    return visitChildren(context);  
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitPrimaryNoNewArray_lfno_arrayAccess(
		Java8Parser.PrimaryNoNewArray_lfno_arrayAccessContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitPrimaryNoNewArray_lf_primary(
		Java8Parser.PrimaryNoNewArray_lf_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(
		Java8Parser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(
		Java8Parser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitPrimaryNoNewArray_lfno_primary(
		Java8Parser.PrimaryNoNewArray_lfno_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(
		Java8Parser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(
		Java8Parser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitClassInstanceCreationExpression(
		Java8Parser.ClassInstanceCreationExpressionContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitClassInstanceCreationExpression_lf_primary(
		Java8Parser.ClassInstanceCreationExpression_lf_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitClassInstanceCreationExpression_lfno_primary(
		Java8Parser.ClassInstanceCreationExpression_lfno_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitTypeArgumentsOrDiamond(
		Java8Parser.TypeArgumentsOrDiamondContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override 
	public String visitFieldAccess(
		Java8Parser.FieldAccessContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitFieldAccess_lf_primary(
		Java8Parser.FieldAccess_lf_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitFieldAccess_lfno_primary(
		Java8Parser.FieldAccess_lfno_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitArrayAccess(
		Java8Parser.ArrayAccessContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitArrayAccess_lf_primary(
		Java8Parser.ArrayAccess_lf_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitArrayAccess_lfno_primary(
		Java8Parser.ArrayAccess_lfno_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitMethodInvocation(
		Java8Parser.MethodInvocationContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitMethodInvocation_lf_primary(
		Java8Parser.MethodInvocation_lf_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitMethodInvocation_lfno_primary(
		Java8Parser.MethodInvocation_lfno_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitArgumentList(
		Java8Parser.ArgumentListContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitMethodReference(
		Java8Parser.MethodReferenceContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitMethodReference_lf_primary(
		Java8Parser.MethodReference_lf_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitMethodReference_lfno_primary(
		Java8Parser.MethodReference_lfno_primaryContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitArrayCreationExpression(
		Java8Parser.ArrayCreationExpressionContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitDimExprs(
		Java8Parser.DimExprsContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitDimExpr(
		Java8Parser.DimExprContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitConstantExpression(
		Java8Parser.ConstantExpressionContext context) { 
	    return visitChildren(context); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitExpression(
		Java8Parser.ExpressionContext context) { 
	    return visitChildren(context); 
	}



//	lambdaExpression
//	:	lambdaParameters '->' lambdaBody
//	;
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitLambdaExpression(
		Java8Parser.LambdaExpressionContext context) { 
	    return visitChildren(context); 
	}
	
	
//	lambdaParameters
//	:	Identifier
//	|	'(' formalParameterList? ')'
//	|	'(' inferredFormalParameterList ')'
//	;
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitLambdaParameters(
		Java8Parser.LambdaParametersContext context) { 
	    if (context.Identifier() != null ) {
		this.dataBuilder.add( new ProgramEntity(
			Species.LAMBDA_PARAMETER, context.Identifier().getText()));
	    }
	    else {
		this.declarationContextStack.push( Species.LAMBDA_PARAMETER);
		visitChildren(context); 
		this.declarationContextStack.pop();
	    }
	    return "";
	}
	
	
//	inferredFormalParameterList
//	:	Identifier (',' Identifier)*
//	;

        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitInferredFormalParameterList(
		Java8Parser.InferredFormalParameterListContext context) { 
	    context.Identifier().forEach( i -> { 
		this.dataBuilder.add( new ProgramEntity(
			Species.LAMBDA_PARAMETER, i.getText() ) );
	    }); 
            return visitChildren(context); 
        }

	// Contained declarations should be picked up by other visitors
//	lambdaBody
//	:	expression
//	|	block
//	;
        /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code context}.</p>
	 */
	@Override public String visitLambdaBody(
		Java8Parser.LambdaBodyContext context) { 
            return visitChildren(context); 
        }
}
