package svm.sibmirsoft.helpers;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {
    private static final Random random = new Random();

    public DataGenerator() {
    }

    public static String generate10DigitNumber() {
        return (String)IntStream.range(0, 10).map((i) -> random.nextInt(10)).mapToObj(String::valueOf).collect(Collectors.joining());
    }

    public static List<Integer> splitPostCode(String postCode) {
        return (List)IntStream.range(0, 5).mapToObj((i) -> Integer.parseInt(postCode.substring(i * 2, i * 2 + 2))).collect(Collectors.toList());
    }

    public static char numberToLetter(int number) {
        return (char)(97 + number % 26);
    }

    public static String numbersToWord(List<Integer> numbers) {
        return (String)numbers.stream().map(DataGenerator::numberToLetter).map(String::valueOf).collect(Collectors.joining());
    }

    public static String shuffleWord(String word) {
        List<Character> letters = (List)word.chars().mapToObj((c) -> (char)c).collect(Collectors.toList());
        Collections.shuffle(letters);
        return (String)letters.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
