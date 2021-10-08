package Source;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Preprocessor {
    public final String text;
    String result = "";
    Map<String, String> defineMatches;

    public Preprocessor(String text) {
        this.text = text;
        defineMatches = GetAllMatches();
    }

    public String[] excute() {
        result = RemoveProcess();

        return ReplaceProcess();
    }

    // replace defined value in text
    private String[] ReplaceProcess() {
        defineMatches.forEach((x, y) -> result = text.replaceAll(x, y));
        return text.split("[\\n\\s\\t\\r\\b]");

    }

    // remove define statements
    private String RemoveProcess() {
        RemoveComments();
        RemoveDefines();
        return result;
    }

    private void RemoveComments() {
        String pattern = "(//.*)";
        List<String> comments = Pattern.compile(pattern).matcher(text).results().map(x -> x.group(1))
                .collect(Collectors.toList());
        String result = text;
        for (String x : comments) {
            result = result.replace(x, "");
        }
    }

    private void RemoveDefines() {
        defineMatches.forEach((x, y) -> result = result.replace("define " + x + " " + y, ""));
    }

    private Map<String, String> GetAllMatches() {
        String definePattern = ".*(?i)define\s*(.*?)\s+(.*)";

        return Pattern.compile(definePattern).matcher(text).results()
                .collect(Collectors.toMap(e -> e.group(1), e -> e.group(2)));

    }

}