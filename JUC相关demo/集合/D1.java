package 集合;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class D1 {
    public static void main(String[] args) {
//        List<String>  list = Arrays.asList("a","b","c","d");
//        list.forEach(System.out::println);
        List<Integer> list = new CopyOnWriteArrayList <>();         //       ArrayList <>();

        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                list.add((int)(Math.random()*99));
                System.out.println(list);
                },String.valueOf(i)).start();
        }

    }
}
