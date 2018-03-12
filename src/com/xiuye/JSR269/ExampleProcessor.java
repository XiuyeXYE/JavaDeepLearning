package com.xiuye.JSR269;

import java.io.*;
import javax.tools.*;
import java.util.*;
import javax.annotation.processing.*;
import javax.lang.model.*;
import javax.lang.model.element.*;


@SupportedAnnotationTypes("HelloWorld")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ExampleProcessor extends AbstractProcessor {

    private Filer filer;

    public void init(ProcessingEnvironment env) {
        filer = env.getFiler();
    }

    public boolean process(Set elements, RoundEnvironment env) {
        // Discover anything marked with @SuppressWarnings
        for (Element element : env
                .getElementsAnnotatedWith(SuppressWarnings.class)) {
            if (element.getKind() == ElementKind.METHOD) {
                // For any methods we find, create an aspect:
                String methodName = element.getSimpleName().toString();
                String aspectText = "public aspect Advise_" + methodName
                        + " {\n" + "  before(): execution(* " + methodName
                        + "(..)) {\n" + "    System.out.println(\""
                        + methodName + " running\");\n" + "  }\n" + "}\n";
                try {
                    JavaFileObject file = filer.createSourceFile("Advise_"
                            + methodName, element);
                    file.openWriter().append(aspectText).close();
                    System.out.println("Generated aspect to advise "
                            + methodName);
                } catch (IOException ioe) {
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {

	}
}
