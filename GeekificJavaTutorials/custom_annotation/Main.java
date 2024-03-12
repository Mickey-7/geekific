package custom_annotation;

import java.lang.reflect.Field;

public class Main {
    /* @Annotations
        - assigns extra metadata to the source code it is bound to
        - can be added to methods, interfaces, classes or fields
        - can inform the compiler about warnings and errors
        - can manipulate source code at compilation time
        - can modify behaviors at runtime
    */

    public static void main(String[] args) throws IllegalAccessException {
        BankAccount ba = new BankAccount(123, "Geekific",1000);
        System.out.println(getXMLString(ba));
        /* output
            <bankAccount>
	            <id>123</id>
	            <name>Geekific</name>
	            <balance>1000.0</balance>
            </bankAccount>
        */


    }

    private static String getXMLString(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : clazz.getDeclaredFields()){
            field.setAccessible(true);
            if (field.isAnnotationPresent(XMLElement.class)){
                stringBuilder
                        .append("\t<").append(getFieldTag(field)).append(">")
                        .append(field.get(object).toString())
                        .append("</").append(getFieldTag(field)).append(">\n");
            }
        }
        String classTag = getClassTag(clazz);
        return "<"+classTag+">\n" + stringBuilder +"</"+classTag+">";
    }

    private static String getFieldTag(Field field) {
        String value = field.getAnnotation(XMLElement.class).tag();
        return value.isEmpty() ? field.getName() : value;

    }

    private static String getClassTag(Class<?> clazz) {
        String value = clazz.getAnnotation(XMLSerializable.class).tag();
        return value.isEmpty() ? clazz.getSimpleName() : value;
    }
}
