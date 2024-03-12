package functional_interface;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    /*

    Functional Interfaces
    -> Java 8 brought
        -> lambda expressions is an
            -> anonymous function is an
                -> abstract method defined in
                    -> special interface because its
                        -> single abstract method

    */

    public static void main(String[] args) {
        // Function Interface

        /* apply() method */
        Function<Integer, Double> function = t -> t / 3.0;
        System.out.println(function.apply(12)); // output: 4.0
        /* apply() takes in only one parameter of any type
        and returns a result variable of any other type */

        // old implementation
        List<Customer> customerList = List.of(new Customer(1, "Geekific1"), new Customer(2, "Geekific2"));
        List<Integer> ids = customerList.stream()
                .map(customer -> customer.getId())
                .collect(Collectors.toList());
        System.out.println(ids); // output: [1, 2]
        // using function interface
        List<Integer> idsWithFunc = customerList.stream()
                .map(Customer::getId)
                .toList();
        System.out.println(idsWithFunc); // output: [1, 2]

        /* andThen() method */
        Function<Double, Double> divideFunction = t -> t / 3.0;
        Function<Double, Double> subtractFunction = t -> t - 2.0;
        System.out.println(divideFunction.andThen(subtractFunction).apply(12.0)); // output: 2.0

        /* compose() method */
        Function<Double, Double> divideFunctionComp = t -> t / 3.0;
        Function<Double, Double> subtractFunctionComp = t -> t - 2.0;
        System.out.println(divideFunction.compose(subtractFunction).apply(12.0)); // output: 3.33

        /* other functions
            - IntFunction
            - LongFunction
            - DoubleFunction
            - ToIntFunction
            - ToLongFunction
            - ToDoubleFUnction
            - IntToDouble
            - IntToLong
            - DoubleToLong
            - DoubleToInt
            - LongToInt
            - LongToDouble
        */

        /* IntToDoubleFunction */
        IntToDoubleFunction divideFunc = t -> t / 3.0;
        System.out.println(divideFunc.applyAsDouble(12)); //output: 4.0

        /* BiFunction
            - takes two parameters instead of only one but also returns a single result
        */
        BiFunction<Integer, Integer, Integer> biFunction = (t , u) -> t * u;
        System.out.println(biFunction.apply(6,6)); // output: 36

        /* other functions
            - ToDoubleBiFunction
            - ToLongBiFunction
            - ToIntBiFunction
        */

        /* Predicate
            - takes one parameter of any type as input but always returns a boolean
            - default methods and(), or() and negate()
         */
        Predicate<String> predicate = t -> t.startsWith("Geek");
        System.out.println(predicate.test("Geekific")); // output: true

        /* using and() */
        Predicate<String> predicate1 = t -> t.startsWith("Geek") && t.length() == 8;
        System.out.println(predicate1.test("Geekific")); // output: true

        Predicate<String> firstPredicate = t -> t.startsWith("Geek");
        Predicate<String> seconfPredicate = t -> t.length() == 8;
        System.out.println(firstPredicate.and(seconfPredicate).test("Geekific")); // output : true

        Predicate<String> predicate2 = t -> t.startsWith("Geek");
        System.out.println(predicate2.negate().test("Geekific")); // output: false

        /* other functions
            - IntPredicate
            - LongPredicate
            - DoublePredicate
            - BiPredicate
        */

        /* Supplier
            - accepts no input but produces output
         */
        Supplier<Double> supplier = new Supplier<Double>() {
            @Override
            public Double get() {
                return new Random().nextDouble();
            }
        };
        System.out.println(supplier.get()); // output: 0.2874309122380069

        // other implementation
        Supplier<Double> supplier1 = Math::random;
        System.out.println(supplier1.get()); // output: 0.7524626682259841

        List<Double> tenRandomNbrs = Stream.generate(supplier1)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(tenRandomNbrs); // output: [0.8724655598849487, 0.9728581620552152, 0.16626704618058197, 0.975167605885923, 0.2614556979775142, 0.7632227846217259, 0.43774250562655326, 0.7337198634751191, 0.6276822912010083, 0.12319524432506623]


        /* Consumer
            - accepts input but produces no output
        */
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String string) {
                System.out.println(string);
            }
        };
        consumer.accept("Geekific"); // output: Geekific
        // other implementation
        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("Geekific"); // output: Geekific

        Stream.generate(() -> "Geekific")
                .limit(3)
                .forEach(System.out::println);
        /* output:
                Geekific
                Geekific
                Geekific
        */

        Consumer<String> c1 = s1 -> System.out.println(s1 + "Consumer 1 | ");
        Consumer<String> c2 = s2 -> System.out.println(s2 + "Consumer 2 | ");
        c1.andThen(c2).accept("Geekific | ");
        /* output:
            Geekific | Consumer 1 |
            Geekific | Consumer 2 |
        */

        /* other functions
            - IntSupplier
            - LongSupplier
            - DoubleSupplier
            - BooleanSupplier
            - IntConsumer
            - LongConsumer
            - DoubleConsumer
            - ObjIntConsumer
            - ObjLongConsumer
            - ObjDoubleConsumer
            - BiConsumer
        */

        // Operators - receive and return the same value type as it is specified in their class definition
        /* UnaryOperator */
        List<String> list = Arrays.asList("GEEKIFIC","OPERATORS");
        list.replaceAll(s -> s.toLowerCase(Locale.ROOT));
        System.out.println(list); // output: [geekific, operators]
        //other implementation
        List<String> list1 = Arrays.asList("GEEKIFIC","OPERATORS");
        list1.replaceAll(String::toLowerCase);
        System.out.println(list1); // output: [geekific, operators]

        /* BinaryOperator */
        Integer sum = Stream.of(20, 10, -40, 80, 30)
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum); // output: 100
        // other implementation
        Integer sum1 = Stream.of(20, 10, -40, 80, 30)
                .reduce(0, Integer::sum);
        System.out.println(sum1); // output: 100

        /* other functions
            - IntUnaryOperator
            - LongUnaryOperator
            - DoubleUnaryOperator
            - IntBinaryOperator
            - LongBinaryOperator
            - DoubleBinaryOperator
        */

        /* Custom Function Interface */

        //Using Lambda Expressions
        MyFunctionalInterface myFunctionalInterface = (id) -> "Data for id: "+id;
        System.out.println(myFunctionalInterface.getData(123));
        //output : Data for id: 123

        //Using Anonymous Inner Classes
        MyFunctionalInterface myFunctionalInterface1 = new MyFunctionalInterface() {
            @Override
            public String getData(int id) {
                return "Data for id: "+id;
            }
        };
        System.out.println(myFunctionalInterface1.getData(123));
        //output: Data for id: 123

        // custom function interface to reverse a string
        ReverseStringFunctionalInterface reverseStringFunctionalInterface = (input) -> {
          StringBuilder sb = new StringBuilder(input);
          return sb.reverse().toString();
        };
        System.out.println(reverseStringFunctionalInterface.reverseString("Geekific"));
        //output : cifikeeG
        //other implementation
        ReverseStringFunctionalInterface functionalInterface = new ReverseStringFunctionalInterface() {
            @Override
            public String reverseString(String input) {
                StringBuilder sb = new StringBuilder(input);
                return sb.reverse().toString();
            }
        };
        System.out.println(functionalInterface.reverseString("Geekific"));
        //cifikeeG
    }


}
