package com.github.devment.codetomermaid.reader.impl;

import com.github.devment.codetomermaid.reader.AccessModifier;
import com.github.devment.codetomermaid.reader.Attribute;
import com.github.devment.codetomermaid.reader.Class;
import com.github.devment.codetomermaid.reader.File;
import com.github.devment.codetomermaid.reader.Method;
import com.github.devment.codetomermaid.reader.Parameter;
import com.github.devment.codetomermaid.reader.Project;
import com.github.devment.codetomermaid.reader.intf.IFileReader;
import com.github.devment.codetomermaid.reader.intf.IReader;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Reads Java Code and map the project/file structure to the internal data structures.
 *
 * @author Devment
 */
public class JavaReader implements IReader {

    private final IFileReader fileReader;

    public JavaReader(IFileReader fileReader) {
        this.fileReader = fileReader;
    }

    public JavaReader() {
        this.fileReader = new FileReader();
    }

    /**
     * @see IReader#parseProject(String)
     */
    @Override
    public Project parseProject(String input) {
        // TODO get all files
        // TODO parse all files
        return null;
    }

    /**
     * @see IReader#parseFile(String)
     */
    @Override
    public File parseFile(String input) {
        // Parse some code
        CompilationUnit cu = StaticJavaParser.parse(input);

        // Classes
        List<Class> classList = new ArrayList<>();
        VoidVisitor<List<Class>> classVisitor = new ClassVisitor();
        classVisitor.visit(cu, classList);

        final File result = new File("TestFile", classList.toArray(new Class[classList.size()])); // TODO set file name
        // TODO print log
        return result;
    }

    /**
     * Visitor class to get all {@link ClassOrInterfaceDeclaration} in the AST, with there Field and Methode
     * information.
     */
    private static class ClassVisitor extends VoidVisitorAdapter<List<Class>> {
        @Override
        public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, List<Class> classList){
            super.visit(classOrInterfaceDeclaration, classList); // visit nodes behind this one

            // Find attributes and methods of this class/interface
            // Attributes
            List<Attribute> attributeList = new ArrayList<>();
            VoidVisitor<List<Attribute>> attributeVisitor = new AttributeVisitor();
            attributeVisitor.visit(classOrInterfaceDeclaration, attributeList);

            // Methods
            List<Method> methodList = new ArrayList<>();
            VoidVisitor<List<Method>> methodeVisitor = new MethodVisitor();
            methodeVisitor.visit(classOrInterfaceDeclaration, methodList);

            final Class aClass = new Class(
                    classOrInterfaceDeclaration.getNameAsString(),
                    attributeList.toArray(new Attribute[attributeList.size()]),
                    methodList.toArray(new Method[methodList.size()])
                    // TODO add more field if supported by mermaid, like interface support
            );
            classList.add(aClass);
        }

        /**
         * Visitor class to get all {@link FieldDeclaration} in the AST.
         */
        private static class AttributeVisitor extends VoidVisitorAdapter<List<Attribute>> {
            @Override
            public void visit(FieldDeclaration fieldDeclaration, List<Attribute> attributeList){
                super.visit(fieldDeclaration, attributeList); // visit nodes behind this one

                final AccessModifier accessModifier = accessSpecifierToModifier(fieldDeclaration.getAccessSpecifier());
                for (int i = 0; i < fieldDeclaration.getVariables().size(); i++){
                    final VariableDeclarator variableDeclarator = fieldDeclaration.getVariables().get(i);
                    final Attribute attribute = new Attribute(
                            variableDeclarator.getNameAsString(),
                            variableDeclarator.getTypeAsString(),
                            accessModifier
                    );
                    attributeList.add(attribute);
                }
            }
        }

        /**
         * Visitor class to get all {@link MethodDeclaration} in the AST.
         */
        private static class MethodVisitor extends VoidVisitorAdapter<List<Method>> {
            @Override
            public void visit(MethodDeclaration md, List<Method> methodList){
                super.visit(md, methodList); // visit nodes behind this one
                final Method method = new Method(md.getNameAsString(),
                        md.getTypeAsString(),
                        accessSpecifierToModifier(md.getAccessSpecifier()),
                        convertParameter(md.getParameters()));
                methodList.add(method);
            }
        }

        /**
         * Converts a {@link NodeList} of JavaParser {@link com.github.javaparser.ast.body.Parameter} to an array of
         * the internal class {@link Parameter}.
         *
         * @param parameters list to convert.
         * @return the converted array.
         */
        private static Parameter[] convertParameter(NodeList<com.github.javaparser.ast.body.Parameter> parameters){
            final List<Parameter> result = new ArrayList<>();
            for (com.github.javaparser.ast.body.Parameter parameter : parameters) {
                result.add(new Parameter(
                        parameter.getNameAsString(),
                        parameter.getTypeAsString()
                        // There are some more information to each parameter available in 'com.github.javaparser.ast.body.Parameter'
                ));
            }
            return result.toArray(new Parameter[result.size()]);
        }

        /**
         * Converts JavaParser {@link AccessSpecifier} to the internal class {@link AccessModifier}.
         *
         * @param accessSpecifier to convert.
         * @return converted Object as {@link AccessModifier}.
         */
        private static AccessModifier accessSpecifierToModifier(AccessSpecifier accessSpecifier){
            switch (accessSpecifier){
                case PRIVATE:
                    return AccessModifier.PRIVATE;
                case PUBLIC:
                    return AccessModifier.PUBLIC;
                case PROTECTED:
                    return AccessModifier.PROTECTED;
                case PACKAGE_PRIVATE:
                    return AccessModifier.PACKAGEPRIVATE;
                default:
                    //TODO write log entry
                    throw new RuntimeException("No suitable Access Modifier found.");
            }
        }
    }


}
