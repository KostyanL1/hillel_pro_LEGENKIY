package org.lesson21.app;

import java.lang.reflect.Method;

public class AnnotationProcessor {
    public static void process(Class<?> someClass){
        for (Method method : someClass.getDeclaredMethods()){
            MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
            if (methodInfo != null){
                System.out.println("Method name: " + method.getName() +
                        "\nReturn options: " + method.getReturnType() +
                        "\nDescription: " + method.toGenericString());
            }
            Author author = method.getAnnotation(Author.class);
            if (author != null){
                System.out.println("\nName: " + author.authorName() +
                        "\nSurname: " + author.authorSurname());
            }
        }
    }
}
