package java_io;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    // for transient demo
    int i = 10;
    final int fi = 20;
    transient int ti = 30;
    transient final int tfi = 40;
    transient static  int tsi = 50;


    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException {
        //FileReader and FileWriter
        File file = new File("java_io/file.txt");

        try(FileWriter fw = new FileWriter(file,true)){
            fw.write("Geekific");
        }
        // the true if for append option

        try(FileReader fr = new FileReader(file)){
            System.out.println((char) fr.read());
            //output: G
        }
        // reads one character at a time

        //to read all
        try(FileReader fr = new FileReader(file)){
            char[] chars = new char[(int) file.length()];
            fr.read(chars);
            System.out.println(new String(chars));
        }
        //output: Geekific

        /* BufferedReader and BufferedWriter */
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            bw.write("Geekific");
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        //output: GeekificGeekific

        /* Non-blocking Input / Output */

        // write using NIO
        String nioFile = "java_io/nioFile.txt";
        Files.write(Paths.get(nioFile),
                "Geekific".getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
        Files.write(Paths.get(nioFile),
                Arrays.asList("Line 1","Line 2","Line 3"),
                StandardCharsets.UTF_8
        );

        //read using nio
        byte[] bytes = Files.readAllBytes(Paths.get(nioFile));
        System.out.println(new String(bytes));
        /*
        output:
            Line 1
            Line 2
            Line 3
        */
        try(BufferedReader br = Files.newBufferedReader(Paths.get(nioFile))){
            System.out.println(br.lines().collect(Collectors.toList()));
        }
        //output: [Line 1, Line 2, Line 3]

        /* resource files */
        URI resUri = ClassLoader.getSystemResource("java_io/resource.txt").toURI();
        Stream<String> stream = Files.lines(Paths.get(resUri));
        stream.forEach(System.out::println);
        /*
        output:
            Reading from a resource file with Geekific!
            Don't forget to leave a like and Subscribe!
        */

        /* readString and writeString */
        String fileName = "java_io/java11.txt";
        Files.writeString(Paths.get(fileName), "Geekific",
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
        String content = Files.readString(Paths.get(fileName));
        System.out.println(content);
        //output: Geekific


        /* transient */
        String transientFile = "java_io/transient.txt";
        Main app = new Main();
        //Serialization
        FileOutputStream fos = new FileOutputStream(transientFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(app);
        System.out.println("Values written to file: \n"+app);
        //Deserialization
        FileInputStream fis = new FileInputStream(transientFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Main readApp = (Main) ois.readObject();
        System.out.println("Values retrieved from file: \n"+readApp);


    }
}
