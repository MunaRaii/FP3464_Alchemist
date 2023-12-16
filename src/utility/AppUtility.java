package utility;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class AppUtility {

    public static final int DEFAULT_FORMAT_LENGTH = 25;
    static Random random = new Random();

    public static String randomNumberStringGenerator() {
        return String.format("%04d", random.nextInt(10000));
    }

    public static String formateString(String... strs) {

        String str1= Arrays.stream(strs).map(str -> {
            int availableSpace = 0;
            if (str.length() < DEFAULT_FORMAT_LENGTH) {
                availableSpace = DEFAULT_FORMAT_LENGTH - str.length();
            }
            return str + " ".repeat(availableSpace);
        }).collect(Collectors.joining());
        return str1;
    }
}
