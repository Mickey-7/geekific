package string_stringBuilder_stringBuffer;

public class Main {
    /*
    StringBuilder
        - not synchronized
        - better in single-threaded environments
        - mutable
        - any change induced will modify the existing sequence of characters created in the memory
    StringBuffer - synchronized
    String
        - immutable
        - created in the memory every time we changed the value
    */

    public static void main(String[] args) {
        String str = "Like";
        str.concat(" and Subscribe");

        StringBuilder sb = new StringBuilder("Like");
        sb.append(" and Subscribe");

        System.out.println(str+" | "+ sb);
        //output : Like | Like and Subscribe

        //to print the and Subscribe
        String result = str.concat(" and Subscribe");
        System.out.println(result);
        //output: Like and Subscribe

        
    }
}
