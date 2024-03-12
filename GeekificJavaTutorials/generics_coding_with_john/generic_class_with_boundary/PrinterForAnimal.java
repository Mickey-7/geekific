package generics_coding_with_john.generic_class_with_boundary;

public class PrinterForAnimal <T extends Animal>{
    T thingToPrint;

    public PrinterForAnimal(T thingToPrint){
        this.thingToPrint = thingToPrint;
    }

    public void print(){
        thingToPrint.eat();
    }
}
