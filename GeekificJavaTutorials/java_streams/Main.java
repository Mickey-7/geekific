package java_streams;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.*;

public class Main {
    /*
    Java Streams
        -> have nothing to do with java I/O Streams
        -> wrappers for data provided to them
        -> is not used to store data hence is not a data structure
        -> never modifies the underlying data given to it
        -> two categories : Intermediate and Terminal

    Intermediate
        -> always return a stream as a result can be chained one after the other

    Terminal
        -> mark the end of chained stream calls and can return any result

    */
    public static void main(String[] args) {
        //creating a stream - generic stream class
        Stream<Integer> stream = Stream.of(20,10,-40,80,30,-90);
        stream.forEach(i -> System.out.printf("%s ",i));
        //output: 20 10 -40 80 30 -90
        System.out.println();

        //creating primitive stream
        IntStream intStream = IntStream.of(20,10,-40,80,30,-90);
        intStream.forEach(j -> System.out.printf("%s ", j));
        //output: 20 10 -40 80 30 -90
        System.out.println();

        /* other types - LongStream, DoubleStream */

        IntStream streamRange = IntStream.range(1,10); // 1 -> 9
        streamRange.forEach(i -> System.out.printf("%s ", i));
        //output: 1 2 3 4 5 6 7 8 9
        System.out.println();

        IntStream closedStream =IntStream.rangeClosed(1,10); // 1 -> 10
        closedStream.forEach(i -> System.out.printf("%s ", i));
        //output: 1 2 3 4 5 6 7 8 9 10
        System.out.println();

        Stream<String> stream1 = Stream.<String>builder()
                .add("Like").add("Subscribe")
                .add("to").add("Geekific")
                .build();
        stream1.forEach(string -> System.out.printf("%s ", string));
        //output: Like Subscribe to Geekific
        System.out.println();

        /* collection to stream */

        //obtain a stream from an existing array
        Integer[] array = {20, 10, -40, 80, 30, -90 };
        Stream<Integer> streamFromArray = Arrays.stream(array);
        streamFromArray.forEach(string -> System.out.printf("%s ", string));
        //output: 20 10 -40 80 30 -90
        System.out.println();

        //obtain a stream from an existing list
        List<Integer> list = Arrays.asList(array);
        Stream<Integer> streamFromList = list.stream();
        streamFromList.forEach(string -> System.out.printf("%s ", string));
        //output: 20 10 -40 80 30 -90
        System.out.println();

        /* toArray */
        Book book1 = new Book("Book1","author1",10.0);
        Book book2 = new Book("Book2","author2",20.0);
        Book book3 = new Book("Book3","author3",30.0);
        Book book4 = new Book("Book4","author4",40.0);
        Book book5 = new Book("Book5","author5",50.0);

        Stream<Book> bookStream = Stream.of(book1,book2,book3,book4,book5);
        Object[] objectsArray = bookStream.toArray();
        System.out.println(Arrays.stream(objectsArray).toList());
        //[Book{title='Book1', author='author1', price=10.0}, Book{title='Book2', author='author2', price=20.0}, Book{title='Book3', author='author3', price=30.0}, Book{title='Book4', author='author4', price=40.0}, Book{title='Book5', author='author5', price=50.0}]

        Stream<Book> bookStream1 = Stream.of(book1,book2,book3,book4,book5);
        //Book[] bookArray = bookStream1.toArray(size -> new Book[size]);
        //other implementation
        Book[] bookArray = bookStream1.toArray(Book[]::new);
        System.out.println(Arrays.stream(bookArray).toList());
        //[Book{title='Book1', author='author1', price=10.0}, Book{title='Book2', author='author2', price=20.0}, Book{title='Book3', author='author3', price=30.0}, Book{title='Book4', author='author4', price=40.0}, Book{title='Book5', author='author5', price=50.0}]

        /* count */
        Stream<Book> bookStream2 = Stream.of(book1,book2,book3,book4,book5);
        long count  = bookStream2.count();
        System.out.println(count); //output: 5

        /* min / max */
        Stream<Book> bookStream3 = Stream.of(book1,book2,book3,book4,book5);
        Optional<Book> mostExpensive = bookStream3
                //.max((b1,b2) -> Double.compare(b1.getPrice(),b2.getPrice()));
                //other implementation
                //.reduce((b1,b2) -> b1.getPrice() > b2.getPrice() ? b1 : b2);
                //other implementation
                .max(Comparator.comparing(Book::getPrice));
        System.out.println(mostExpensive);
        //output : Optional[Book{title='Book5', author='author5', price=50.0}]

        Stream<Book> bookStream4 = Stream.of(book1,book2,book3,book4,book5);
        Optional<Book> alphabeticallyFirstBook = bookStream4
                .min(Comparator.comparing(Book::getTitle));
        System.out.println(alphabeticallyFirstBook);
        //output : Optional[Book{title='Book1', author='author1', price=10.0}]

        /* optional */

        /* isPresent */
        Stream<Book> bookStream5 = Stream.of(book1,book2,book3,book4,book5);
        Optional<Book> mostExpensiveBook = bookStream5
                .max(Comparator.comparing(Book::getPrice));
        if (mostExpensiveBook.isPresent()){
            Book book = mostExpensiveBook.get();
            System.out.println(book);
        }
        //output: Book{title='Book5', author='author5', price=50.0}

        /* ifPresent */
        Stream<Book> bookStream6 = Stream.of(book1,book2,book3,book4,book5);
        bookStream6.max(Comparator.comparing(Book::getPrice))
                .ifPresent(System.out::println);
        //output: Book{title='Book5', author='author5', price=50.0}

        /* orElse */
        Stream<Book> bookStream7 = Stream.of(book1,book2,book3,book4,book5);
        Book book = bookStream7.max(Comparator.comparing(Book::getPrice))
                .orElse(book1);
        System.out.println(book);
        //output: Book{title='Book5', author='author5', price=50.0}

        /* orElseGet */
        Stream<Book> bookStream9 = Stream.of(book1,book2,book3,book4,book5);
        Book book7 = bookStream9.max(Comparator.comparing(Book::getPrice))
                .orElseGet(() -> getDefaultBookToReturn());
        System.out.println(book7);
        //output: Book{title='Book5', author='author5', price=50.0}

        /* orElseThrow */
        try {
           Stream<Book> bookStream8 = Stream.of(book1,book2,book3,book4,book5);
           Book book6 = bookStream8.max(Comparator.comparing(Book::getPrice))
                    .orElseThrow(Exception::new);
           System.out.println(book6);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
       //output: Book{title='Book5', author='author5', price=50.0}


        /* orElse /orElseGet */
        String value = Optional.of("Geekific").orElse(printMethod());
        System.out.println(value);
        /*
            output:
                Like
                Geekific
        */
        String value1 = Optional.of("Geekific").orElseGet(() -> printMethod());
        System.out.println(value1);
        /*
            output:
                Geekific
        */
        String value2 = (String) Optional.empty().orElseGet(() -> printMethod());
        System.out.println(value2);
        /*
            output:
                Like
                Subscribe
        */

        // should use orElseGet for correct return

        /* average */
        OptionalDouble average = Arrays.stream(new int[] {10,20,50,80}).average();
        System.out.println(average);
        // output: OptionalDouble[40.0]
        Arrays.stream(new int[] {10,20,50,80})
                .average()
                .ifPresent(System.out::println);
        // output: 40.0

        /* reduce */
        Integer sumOfIntegers = Stream.of(20,10,-40,80,30,-90).reduce(0, Integer::sum);
        System.out.println(sumOfIntegers);
        // other implementation
        Integer sum = Stream.of(20,10,-40,80,30,-90)
                .mapToInt(i -> i.intValue())
                .sum();
        System.out.println(sum);
        // output: 10

        List<String> letters = Arrays.asList("a","b","c","d","e");
        String result = letters.stream()
                .reduce("", (partialString, element) -> partialString+element);
        System.out.println(result);
        //output : abcde

        Stream<Book> bookStream10 = Stream.of(book1,book2,book3,book4,book5);
        double total = bookStream10.reduce(0.0, (cumulativePriceResult, boo) ->
                cumulativePriceResult + boo.getPrice(), Double::sum);
        System.out.println(total);
        //output : 150.0
        // other implementation
        Stream<Book> bookStream11 = Stream.of(book1,book2,book3,book4,book5);
        double totally = bookStream11.reduce(0.0,
                (cumulativePrice, bo) -> cumulativePrice + bo.getPrice(),
                (subTotal1, subTotal2) -> subTotal1 + subTotal2);
        System.out.println(totally);
        //output : 150.0

        /* findFirst */
        Stream<Book> bookStream12 = Stream.of(book1,book2,book3,book4,book5);
        Optional<Book> firstBook = bookStream12.findFirst();
        System.out.println(firstBook);
        //output: Optional[Book{title='Book1', author='author1', price=10.0}]
        boolean isPresent = firstBook.isPresent();
        System.out.println(isPresent);
        //output: true

        /* findAny */
        Stream<Book> bookStream13 = Stream.of(book1,book2,book3,book4,book5);
        Optional<Book> anyBook = bookStream13.findAny();
        anyBook.ifPresent(System.out::println); // allowed to select any book
        //output: Book{title='Book1', author='author1', price=10.0}

        /* forEach */
        Stream<Book> bookStream14 = Stream.of(book1,book2,book3,book4,book5);
        bookStream14.forEach(System.out::println);
        /*
        output:
            Book{title='Book1', author='author1', price=10.0}
            Book{title='Book2', author='author2', price=20.0}
            Book{title='Book3', author='author3', price=30.0}
            Book{title='Book4', author='author4', price=40.0}
            Book{title='Book5', author='author5', price=50.0}
        */

        /* anyMatch  */
        Stream<Integer> stream2 = Stream.of(25,15,75,35,40,5,65);
        boolean oneIsEven = stream2.anyMatch(i -> i%2 == 0);
        System.out.println(oneIsEven);
        //output: true

        /* allMatch */
        Stream<Integer> stream3 = Stream.of(25,15,75,35,40,5,65);
        boolean allAreEven = stream3.allMatch(i -> i%2 == 0);
        System.out.println(allAreEven);
        //output: false

        /* noneMatch */
        Stream<Integer> stream4 = Stream.of(25,15,75,35,40,5,65);
        boolean allAreLessThan80 = stream4.noneMatch(i -> i > 80);
        System.out.println(allAreLessThan80);
        //output: true

        /* collect */
        Stream<Book> bookStream15 = Stream.of(book1,book2,book3,book4,book5);
        List<Book> bookList = bookStream15.collect(Collectors.toList());
        System.out.println(bookList);
        //output: [Book{title='Book1', author='author1', price=10.0}, Book{title='Book2', author='author2', price=20.0}, Book{title='Book3', author='author3', price=30.0}, Book{title='Book4', author='author4', price=40.0}, Book{title='Book5', author='author5', price=50.0}]

        Stream<Book> bookStream16 = Stream.of(book1,book2,book3,book4,book5);
        Set<Book> bookSet = bookStream16.collect(Collectors.toSet());
        System.out.println(bookSet);
        //output: [Book{title='Book3', author='author3', price=30.0}, Book{title='Book1', author='author1', price=10.0}, Book{title='Book2', author='author2', price=20.0}, Book{title='Book4', author='author4', price=40.0}, Book{title='Book5', author='author5', price=50.0}]

        Stream<Book> bookStream17 = Stream.of(book1,book2,book3,book4,book5);
        Vector<Book> bookVector = bookStream17.collect(Collectors.toCollection(Vector::new));
        System.out.println(bookVector);
        //output: [Book{title='Book1', author='author1', price=10.0}, Book{title='Book2', author='author2', price=20.0}, Book{title='Book3', author='author3', price=30.0}, Book{title='Book4', author='author4', price=40.0}, Book{title='Book5', author='author5', price=50.0}]

        Stream<Book> bookStream18 = Stream.of(book1,book2,book3,book4,book5);
        LinkedList<Book> bookLinkedList = bookStream18.collect(Collectors.toCollection(LinkedList::new));
        System.out.println(bookLinkedList);
        //output: [Book{title='Book1', author='author1', price=10.0}, Book{title='Book2', author='author2', price=20.0}, Book{title='Book3', author='author3', price=30.0}, Book{title='Book4', author='author4', price=40.0}, Book{title='Book5', author='author5', price=50.0}]


        /* non-terminal operations */

        /* skip */
        Stream<Book> bookStream19 = Stream.of(book1,book2,book3,book4,book5);
        long skipFirstTwoBooks = bookStream19.skip(2).count();
        System.out.println(skipFirstTwoBooks);
        //output: 3

        /* limit */
        Stream<Book> bookStream20 = Stream.of(book1,book2,book3,book4,book5);
        long firstTwoBooks = bookStream20.limit(2).count();
        System.out.println(firstTwoBooks);
        //output: 2

        /* create infinite stream */
        /* generate / iterate */
        List<Double> tenRandomNumbers = Stream.generate(Math::random)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(tenRandomNumbers);
        //output : [0.4355288243749754, 0.4716416971433798, 0.760616150412617, 0.9480167848343208, 0.11086139652693039, 0.738426648114602, 0.00856588763059063, 0.8277975845042745, 0.6031625243963609, 0.6125253832046853]
        List<Double> rand = Stream.generate(() -> Math.random())
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(rand);
        //output: [0.6605100938515512, 0.9694906422728098, 0.4476196758625821, 0.7451314845023767, 0.9141841354225874, 0.8189299357061571, 0.0302939682563097, 0.4966547484232, 0.7686929786463355, 0.19668431928025065]

        Stream.generate(() -> "Geekific")
                .limit(3)
                .forEach(System.out::println);
        /*
            output:
                Geekific
                Geekific
                Geekific
        */

        List<Integer> intsFrom0To9 = Stream.iterate(0, n -> n +1)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(intsFrom0To9);
        //output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        Stream.iterate(1, n -> n +2)
                .limit(3)
                .forEach(System.out::println);
        /*
            output:
                1
                3
                5
        */


        /* distinct */
        Stream.of(10,10,20,20,30)
                .distinct().forEach(System.out::println);
        /*
        output:
            10
            20
            30
        */

        /* sorted */
        Stream<Book> bookStream21 = Stream.of(book3,book1,book2,book5,book4);
        bookStream21.sorted(Comparator.comparing(Book::getPrice))
                .forEach(System.out::println);
        /*
        output:
            Book{title='Book1', author='author1', price=10.0}
            Book{title='Book2', author='author2', price=20.0}
            Book{title='Book3', author='author3', price=30.0}
            Book{title='Book4', author='author4', price=40.0}
            Book{title='Book5', author='author5', price=50.0}
        */

        Stream<Book> bookStream22 = Stream.of(book3,book1,book2,book5,book4);
        bookStream22.sorted(Comparator.comparing(Book::getPrice)
                        .thenComparing(Book::getPrice))
                .forEach(System.out::println);
        /*
        output:
            Book{title='Book1', author='author1', price=10.0}
            Book{title='Book2', author='author2', price=20.0}
            Book{title='Book3', author='author3', price=30.0}
            Book{title='Book4', author='author4', price=40.0}
            Book{title='Book5', author='author5', price=50.0}
        */

        /* filter */
        List<Integer> filteredInts = Stream.of(25,15,75,35,40,5)
                .filter(i -> i <30)
                .collect(Collectors.toList());
        System.out.println(filteredInts);
        //output: [25, 15, 5]

        /* map */
        Stream<Book> bookStream23 = Stream.of(book3,book1,book2,book5,book4);
        List<String> titles = bookStream23.map(Book::getTitle)
                .collect(Collectors.toList());
        System.out.println(titles);
        //output: [Book3, Book1, Book2, Book5, Book4]

        List<Integer> listTimes10 = Stream.of(25,15,75,35,40,5)
                .map(i -> i * 10)
                .collect(Collectors.toList());
        System.out.println(listTimes10);
        //output: [250, 150, 750, 350, 400, 50]

        /* peek */
        Stream<Book> bookStream24 = Stream.of(book3,book1,book2,book5,book4);
        List<Book> printAndCollect = bookStream24.peek(System.out::println)
                .collect(Collectors.toList());
        /*
        output:
            Book{title='Book3', author='author3', price=30.0}
            Book{title='Book1', author='author1', price=10.0}
            Book{title='Book2', author='author2', price=20.0}
            Book{title='Book5', author='author5', price=50.0}
            Book{title='Book4', author='author4', price=40.0}
        */

        Stream<Book> bookStream25 = Stream.of(book3,book1,book2,book5,book4);
        List<Book> alterAndCollect = bookStream25
                .peek(b -> b.setPrice(b.getPrice() + 10))
                .collect(Collectors.toList());
        System.out.println(alterAndCollect);
        // output: [Book{title='Book3', author='author3', price=40.0}, Book{title='Book1', author='author1', price=20.0}, Book{title='Book2', author='author2', price=30.0}, Book{title='Book5', author='author5', price=60.0}, Book{title='Book4', author='author4', price=50.0}]

        /* flatMap */
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(10,20,30),
                Arrays.asList(40,50,60)
        );
        List<Integer> integerList = nestedList
                .stream()
                .flatMap(Collection::stream)
                //other implementation
                //.flatMap(collection -> collection.stream())
                .collect(Collectors.toList());
        System.out.println(integerList);
        //output: [10, 20, 30, 40, 50, 60]


        /* terminal operations - used inside the collect operation */

        /* joining */
        Stream<Book> bookStream26 = Stream.of(book3,book1,book2,book5,book4);
        String commaSeparatedTitles = bookStream26
                .map(Book::getTitle)
                .collect(Collectors.joining(", "));
        System.out.println(commaSeparatedTitles);
        // output :Book3, Book1, Book2, Book5, Book4

        /* summary statistics */
        Stream<Book> bookStream27 = Stream.of(book3,book1,book2,book5,book4);
        DoubleSummaryStatistics summaryStatistics = bookStream27
                .collect(Collectors.summarizingDouble(Book::getPrice));
        System.out.println(summaryStatistics);
        // output: DoubleSummaryStatistics{count=5, sum=200.000000, min=20.000000, average=40.000000, max=60.000000}
        // does also have IntSummaryStatistics &  LongSummaryStatistics

        /* partitioningBy */
        Map<Boolean, List<Integer>> evenOddMap =
                Stream.of(25,15,75,35,40,5,10,55,60,80)
                        .collect(
                                Collectors.partitioningBy(i -> i % 2 == 0)
                        );
        System.out.println(evenOddMap);
        //output: {false=[25, 15, 75, 35, 5, 55], true=[40, 10, 60, 80]}

        /* groupingBy */
        Stream<Book> bookStream28 = Stream.of(book3,book1,book2,book5,book4);
        Map<String, List<Book>> booksByAuthor = bookStream28
                .collect(Collectors.groupingBy(Book::getAuthor));
        System.out.println(booksByAuthor);
        //output: {author2=[Book{title='Book2', author='author2', price=30.0}], author1=[Book{title='Book1', author='author1', price=20.0}], author5=[Book{title='Book5', author='author5', price=60.0}], author4=[Book{title='Book4', author='author4', price=50.0}], author3=[Book{title='Book3', author='author3', price=40.0}]}

        /* groupingBy - mapping */
        Stream<Book> bookStream29 = Stream.of(book3,book1,book2,book5,book4);
        Map<String, List<String>> titlesByAuthor = bookStream29
                .collect(
                        Collectors.groupingBy(
                                Book::getAuthor,
                                Collectors.mapping(Book::getTitle, Collectors.toList())
                        )
                );
        System.out.println(titlesByAuthor);
        //output: {author2=[Book2], author1=[Book1], author5=[Book5], author4=[Book4], author3=[Book3]}

        /* groupingBy - reduce */
        Stream<Book> bookStream30 = Stream.of(book3,book1,book2,book5,book4);
        Map<String, Optional<Book>> mostExpensiveByAuthor = bookStream30
                .collect(
                        Collectors.groupingBy(
                                Book::getAuthor,
                                Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Book::getPrice)))
                        )
                );
        System.out.println(mostExpensiveByAuthor);
        //output: {author2=Optional[Book{title='Book2', author='author2', price=30.0}], author1=Optional[Book{title='Book1', author='author1', price=20.0}], author5=Optional[Book{title='Book5', author='author5', price=60.0}], author4=Optional[Book{title='Book4', author='author4', price=50.0}], author3=Optional[Book{title='Book3', author='author3', price=40.0}]}


        /* iterate - post java 8 */
        Stream.iterate(1, n -> n <10, n -> n + 2)
                .forEach(System.out::println);
        /*
            output:
                1
                3
                5
                7
                9
        */

        /* takeWhile / dropWhile */
        Stream<Integer> streamer = Stream.of(25,15,75,35,40,5,10,55,60);
        List<Integer> takeWhile = streamer.takeWhile(num -> num < 30)
                .collect(Collectors.toList());
        System.out.println(takeWhile);
        //output: [25, 15]

        Stream<Integer> streamer1 = Stream.of(25,15,75,35,40,5,10,55,60);
        List<Integer> filtered = streamer1.filter(num -> num < 30)
                .collect(Collectors.toList());
        System.out.println(filtered);
        //output:[25, 15, 5, 10]

        Stream<Integer> streamer2 = Stream.of(25,15,75,35,40,5,10,55,60);
        List<Integer> dropWhile = streamer2.dropWhile(num -> num < 30)
                .collect(Collectors.toList());
        System.out.println(dropWhile);
        //output:[75, 35, 40, 5, 10, 55, 60]

        /* parallel */
        Stream<Integer> streamer5 = Stream.of(25,15,75,35,40,5,10,55,60);
        streamer5.parallel().forEach(System.out::println);
        /*
            output:
                5
                40
                55
                60
                10
                15
                25
                35
                75
        */
        //printing is not in order hence parallelism





































    }

    private static String printMethod() {
        System.out.println("Like");
        return "Subscribe";
    }

    private static Book getDefaultBookToReturn() {
        return null;
    }
}
