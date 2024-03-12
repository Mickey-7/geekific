package custom_exception;
//reference : https://www.youtube.com/watch?v=OIozDnGYqIU&list=PLkeaG1zpPTHhXOfy-mFbdqd1Zz4GnjcpC&index=12

public class Main {
    public static void main(String[] args) throws AgeLessThanZeroException {
        validateAge(7);
        validateAge(-7);
    }

    private static void validateAge(int age) throws AgeLessThanZeroException {
        if (age < 0){
            //throw new AgeLessThanZeroException();
            //throw new AgeLessThanZeroException("Oh no!");
            //throw new AgeLessThanZeroException(new RuntimeException());
            throw new AgeLessThanZeroException("Oh no!", new RuntimeException());

        }
    }
}
