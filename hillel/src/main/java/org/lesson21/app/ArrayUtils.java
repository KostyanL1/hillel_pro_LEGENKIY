package org.lesson21.app;

public class ArrayUtils {

    @Author
    @MethodInfo
    public static void method1(){}
    @Author(authorName = "Kostya", authorSurname = "Legenkiy")
    @MethodInfo
    public static int method2(int a, int b){
        return a + b;
    }

    public static void main(String[] args) {
        AnnotationProcessor.process(ArrayUtils.class);
    }
}
