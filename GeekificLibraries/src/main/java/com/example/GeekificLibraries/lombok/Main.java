package com.example.GeekificLibraries.lombok;

import lombok.val;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {



        /* use of val */
        // without val
        final Map<Integer, String> mapWithoutVal = new HashMap<>();
        mapWithoutVal.put(0,"Lombok");
        mapWithoutVal.put(1, "Geekific");
        for (final Map.Entry<Integer, String> entry: mapWithoutVal.entrySet()){
            System.out.println(entry.getKey() + entry.getValue());
        }
        /*
            0Lombok
            1Geekific
        */

        // with val
        val mapWithVal = new HashMap<Integer,String>();
        mapWithVal.put(0,"Lombok");
        mapWithVal.put(1, "Geekific");
        for (val entry: mapWithVal.entrySet()){
            System.out.println(entry.getKey() + entry.getValue());
        }
        /*
            0Lombok
            1Geekific
        */


        /* use of var */
        // without var
        Map<Integer, String> mapWithoutVar = new HashMap<>();
        mapWithoutVar.put(0,"Lombok");
        mapWithoutVar.put(1, "Geekific");
        for (Map.Entry<Integer, String> entry: mapWithoutVar.entrySet()){
            System.out.println(entry.getKey() + entry.getValue());
        }
        /*
            0Lombok
            1Geekific
        */

        // with var
        var mapWithVar = new HashMap<Integer,String>();
        mapWithVar.put(0,"Lombok");
        mapWithVar.put(1, "Geekific");
        for (var entry: mapWithVar.entrySet()){
            System.out.println(entry.getKey() + entry.getValue());
        }
        /*
            0Lombok
            1Geekific
        */


        /* @Accessors */
        Actor actor = new Actor();
        actor.id(1).name("Maisie").topRole("Arya");

        /* @ToString */
        System.out.println(actor);
        /* Actor(id=1, name=Maisie, topRole=Arya, data=[]) */


        /* @ToString(includeFieldNames = false) */
        Actor1 actor1 = new Actor1();
        actor1.id(1).name("Maisie").topRole("Arya");
        System.out.println(actor1);
        /* Actor(1, Maisie, Arya, []) */

        /* @ToString.Exclude */
        Actor2 actor2 = new Actor2();
        actor2.id(1).name("Maisie").topRole("Arya");
        System.out.println(actor2);
        /* Actor2(Maisie, Arya, []) */

        /* @ToString.Include */
        Actor3 actor3 = new Actor3();
        actor3.id(1).name("Maisie").topRole("Arya");
        System.out.println(actor3);
        /* Actor3(name=Maisie) */

        /* callSuper = true */
        Actor4 actor4 = new Actor4();
        actor4.id(1).name("Maisie");
        actor4.topRole("arya");
        System.out.println(actor4);
        /* Actor4(super=Person(name=Maisie), arya) */

        /* NoArgs, AllArgs, RequiredArgs */
        /*
            @NoArgsConstructor
            public Actor(){};
            Actor actor = new Actor();

            @AllArgsConstructor
            public Actor(int id, String name, String topRole){
                this.id = id;
                this.name = name;
                this.topRole = topRole;
            };
            Actor actor = new Actor(123,"Geekific","Arya");


            @RequiredArgsConstructor
            @AllArgsConstructor
            public class Actor {
                private final int id;
                @NonNull
                private String name;
                private String topRole;
            }

            public Actor(int id, String name){
                this.id = id;
                if(name == null){
                    throw new NullPointerException("name")
                }
                this.name = name;
            };
            Actor actor = new Actor(123,"Maisie");
        */

        /* @Data = @Getter, @Setter, @ToString, @EqualsAndHashCode & @RequiredArgsContructors */

        /* @Builder */
        Actor5 actor5 = Actor5.builder()
                .topRole("Arya")
                .name("Maisie")
                .id(1)
                .build();
        System.out.println(actor5);
        /* Actor5(id=1, name=Maisie, topRole=Arya) */

        Actor6 actor6 = Actor6.anActor()
                .setId(1)
                .setName("Maisie")
                .setTopRole("Arya")
                .execute();
        System.out.println(actor6);
        /* Actor6(id=1, name=Maisie, topRole=Arya) */

       /* @Builder.Default */
        Actor7 actor7 = Actor7.builder()
                .name("Maisie")
                .id(1)
                .build();
        System.out.println(actor7);
        /* Actor7(id=1, name=Maisie, topRole=null) */

        /* @Singular */
        Actor8.Actor8Builder builder = new Actor8.Actor8Builder();
        builder.id(1).movie("Two Weeks To Live");
        System.out.println(builder);
        /* Actor8.Actor8Builder(id=1, name=null, topRole=null, movies=[Two Weeks To Live]) */
        builder.clearMovies();
        builder.movies(List.of("The Owners", "The New Mutants"));
        Actor8 actor8 = builder.build();
        System.out.println(actor8);
        /* Actor8(id=1, name=null, topRole=null, movies=[The Owners, The New Mutants]) */

        Actor9.Actor9Builder actor9Builder = new Actor9.Actor9Builder();
        actor9Builder.addId(1).addMovie("Two Weeks To Live");
        System.out.println(actor9Builder);
        /* Actor9.Actor9Builder(id=1, name=null, topRole=null, movies=[Two Weeks To Live]) */
        actor9Builder.clearMovies();
        actor9Builder.addMovies(List.of("The Owners"," The New Mutants"));
        Actor9 actor9 = actor9Builder.build();
        System.out.println(actor9);
        /* Actor9(id=1, name=null, topRole=null, movies=[The Owners,  The New Mutants]) */

        /* @SuperBuilder */
        Actor10 actor10 = Actor10.builder()
                .topRole("Arya")
                .name("Maisie")
                .id(1)
                .build();

        /* @Cleanup - will ensure a given resource is automatically cleaned up
                    or closed before the code execution path exist your current scope

            String in = "src/bankAccount.json";
            String out = "src/bankAccount1.json";

            @Cleanup
            BufferedReader reader = new BufferedReader(new FileReader(in));

            @Cleanup
            BufferedWriter writer = new BufferedWriter(new FileReader(out));

            writer.write(reader.readLine());
        */


        /* Log - multiple annotations */

    }
}
