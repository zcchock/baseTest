package com.zc;

import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chock
 * @since 2019/11/16
 */
public class LambdaTest {

    @Test
    public void lambdaTest() {
        new Thread(() -> System.out.println("Test lambda")).start();
    }


    @Test
    public void demo() {
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("Test thread");
            }
        };
        new Thread(runnable).start();
    }

    @Test
    public void eachLambda() {
        List<String> strList = Arrays.asList("one", "two", "three");
        strList.forEach(str -> System.out.println(str));
        strList.forEach(System.out::println);
    }


    @Test
    public void functionLambda() {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.startsWith("J"));

        System.out.println("Languages which ends with a :");
        filter(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.length() > 4);
    }

    private static void filter(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }


    @Test
    public void predicateLambda() {

        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        Predicate<String> unStartJ = a -> !a.startsWith("J");
        Predicate<String> longerFourLetter = a -> a.length() > 4;

        languages.stream().filter(unStartJ.and(longerFourLetter)).forEach(str -> System.out.println(str));
    }


    @Test
    public void mapLambda() {
        List<Integer> beforeVals = Arrays.asList(100, 200, 300, 400, 500);
        beforeVals.stream().map(n -> n / 100).forEach(System.out::print);
    }

    @Test
    public void reduceLambda() {
        double bill = Stream.of(100, 200, 300, 400, 500).map((cost) -> .8 * cost)
                .reduce(10.0, (sum, cost) -> {
                    sum += cost;
                    return sum;
                });

        System.out.println(bill);
    }

    @Test
    public void lastOperationLambda() {
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany",
                "Italy", "U.K.", "Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase())
                .collect(Collectors.joining(", "));
        System.out.println(G7Countries);

        G7.stream().map(x -> x.toUpperCase())
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void intSummaryStatisticsLambda() {
        List<Integer> vals = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics summary = vals.stream().mapToInt(n -> n + 2).summaryStatistics();
        System.out.println("最大值：" + summary.getMax() + " 平均值：" + summary.getAverage());
    }

    @Test
    public void distinctAndSkipLambda() {
        List languages = Arrays.asList("Java", "Scala", "C++", "Java", "Haskell", "Lisp");
        languages.stream().distinct().skip(2).forEach(n -> System.out.println(n));
    }

    @Test
    public void listToMapAndGroupByLambda () {
        List<Person> list = new ArrayList<Person>() {{
            add(new Person(1, "name1"));
            add(new Person(2, "name2"));
            add(new Person(3, "name3"));
        }};

        // list转换为id为key，对象为val的MAP
        Map<Integer, Person> map = list.stream().collect(Collectors.toMap(Person::getId, n -> n));
        // list按照id进行分组，在id存在重复的情况下可以拆分
        Map<Integer, List<Person>> map2 = list.stream().collect(Collectors.groupingBy(Person::getId));
    }


    class Person {
        int id;
        String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
