import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {
    public static int add(String string) {
        if(!string.isEmpty()){
            List<Integer> numbers = strArrToNUmList(getSplit(string));
            //negatives(numbers);
            return listSum(numbers);
        }
        return 0;
    }

    private static void negatives(List<Integer> numbers) {
        StringBuilder stringBuilder = new StringBuilder();
        numbers.stream()
                .filter(num->num<0)
                .forEach(num->stringBuilder.append(num).append(" "));
        if(!stringBuilder.toString().isEmpty()){
            throw new RuntimeException("negatives not allowed: "+stringBuilder.toString());
        }
    }

    private static List<Integer> strArrToNUmList(String[] stringArr) {
        return Arrays.stream(stringArr)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static String[] getSplit(String string) {
        if(string.startsWith("//")){
            Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(string);
            if(matcher.matches()){
                String delimiter = matcher.group(1);
                String toSplit = matcher.group(2);
                return toSplit.split(delimiter);
            }
            throw new RuntimeException("Wrong Custom delimiter format");
        }
        return string.split(",");
    }

    private static Integer listSum(List<Integer> numbers) {
        return numbers.stream()
                .reduce(Integer::sum)
                .orElseThrow();
    }
}
