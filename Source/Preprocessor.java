package Source;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Preprocessor {
    String definePattern = "[^a-zA-Z0-9_]define +(.*?) +(.*)";
    public String text;
    Map<String, String> defineMatches;
    AtomicReference<String> ref = new AtomicReference<>(text);

    public Preprocessor(String text) {
        this.text = text;
        defineMatches = GetAllMatches();
    }

    public String excute() {
        RemoveProcess();
        ReplaceProcess();
        return ref.get();
    }

    // replace defined value in text
    private void ReplaceProcess() {
        defineMatches.forEach((t, l) -> {
            String pattern = "\\b(" + t + ")\\b";
            Matcher m = Pattern.compile(pattern).matcher(ref.get());
            while (m.find()) {
                ref.set(m.replaceAll(l));
            }
        });
    }

    // remove define statements
    private void RemoveProcess() {
        RemoveComments();
    }

    private void RemoveComments() {
        String pattern = "(//.*)";
        Matcher m = Pattern.compile(pattern).matcher(ref.get());
        while (m.find()) {
            ref.set(m.replaceAll(""));
        }
    }

    private Map<String, String> GetAllMatches() {
        HashMap<String, String> map = new HashMap<String, String>();
        Matcher m = Pattern.compile(definePattern).matcher(ref.get());
        while (m.find()) {
            ref.set(m.replaceAll(""));
            map.put(m.group(1), m.group(2));
        }
        return map;
    }

}
// "[\+\=\-\[\]\{\}\r\t ](salam)[\+\=\-\[\]\{\}\r\t ]"g