package generics_coding_with_john.generic_class;

public class PrinterTwo <T,V>{
    T thingToPrint;
    V otherThingToShout;

    public PrinterTwo(T thingToPrint, V otherThingToShout){
        this.thingToPrint = thingToPrint;
        this.otherThingToShout = otherThingToShout;
    }

    public void print(){
        System.out.println(thingToPrint);
        System.out.println(otherThingToShout);
    }
}
