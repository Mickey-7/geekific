package generics_coding_with_john;

import generics_coding_with_john.generic_class.Printer;
import generics_coding_with_john.generic_class_with_boundary.Animal;
import generics_coding_with_john.generic_class_with_boundary.Cat;
import generics_coding_with_john.generic_class_with_boundary.Dog;
import generics_coding_with_john.generic_class_with_boundary.PrinterForAnimal;
import generics_coding_with_john.multiple_class.DoublePrinter;
import generics_coding_with_john.multiple_class.IntegerPrinter;
import generics_coding_with_john.multiple_class.StringPrinter;

import java.util.ArrayList;
import java.util.List;

//reference : https://www.youtube.com/watch?v=K1iu1kXkVoA
public class Main {
    public static void main(String[] args) {
        //old implementation
        //print integer
        IntegerPrinter integerPrinter = new IntegerPrinter(7);
        integerPrinter.print(); // 7
        //print double
        DoublePrinter doublePrinter = new DoublePrinter(7.0);
        doublePrinter.print(); // 7.0
        //print string
        StringPrinter stringPrinter = new StringPrinter("this is a string");
        stringPrinter.print(); // this is a string

        /*using generic class printer*/
        Printer<Integer> integerPrinter1 = new Printer<>(7);
        integerPrinter1.print(); // 7
        Printer<Double> doublePrinter1 = new Printer<>(7.0);
        doublePrinter1.print(); // 7.0
        Printer<String> stringPrinter1 = new Printer<>("this is a string");
        stringPrinter1.print(); // this is a string

        /*generic with bounded class*/
        PrinterForAnimal<Dog> dogPrinterForAnimal = new PrinterForAnimal<>(new Dog());
        dogPrinterForAnimal.print(); //dog is eating
        PrinterForAnimal<Cat> catPrinterForAnimal = new PrinterForAnimal<>(new Cat());
        catPrinterForAnimal.print(); //cat is eating

        /* generic methods*/
        shout("John"); //John!!!!!
        shout(57); //57!!!!!
        shout(new Cat());

        shoutTwo("John",45);
        //John!!!!!
        //45!!!!!
        shoutTwo(57,1.0);
        //57!!!!!
        //1.0!!!!!
        shoutTwo(new Cat(), new Dog());


        /* wildcard */
        List<Integer> integerList = new ArrayList<>();
        integerList.add(7);
        printList(integerList);

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());
        printList(catList);


        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
        printListBoundToAnimal(dogs);

    }


    /* generic methods */
    private static <T> void shout(T thingToShout){
        System.out.println(thingToShout+"!!!!!");
    }

    private static <T,V> void shoutTwo(T thingToShout, V otherThingToShout){
        System.out.println(thingToShout+"!!!!!");
        System.out.println(otherThingToShout+"!!!!!");
    }

    private static <T,V> T shoutReturn(T thingToShout, V otherThingToShout){
        System.out.println(thingToShout+"!!!!!");
        System.out.println(otherThingToShout+"!!!!!");
        return thingToShout;
    }

    /* wildcard */
    private static  void printList(List<?> myList){
        System.out.println(myList);
    }

    /* bounded wildcard */
    private static void printListBoundToAnimal(List<? extends Animal> animals){
        System.out.println(animals);
    }
}
