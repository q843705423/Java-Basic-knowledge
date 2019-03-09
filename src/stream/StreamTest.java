package stream;

import java.util.Arrays;

public class StreamTest {
    static String []string = new String[]{
            "hello","world","god"
    };
    public static void main(String []args){
        Arrays.asList(string).stream().map(x->x.toUpperCase()).forEach(System.out::println);
    }
}
