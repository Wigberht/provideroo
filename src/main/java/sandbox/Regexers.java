package sandbox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexers {
    public static void main(String[] args) {
        String text = "command=refl";
        String regex = ".*?command=(.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        matcher.find();
        System.out.println("found group 1: " + matcher.group(1));
    }
}
