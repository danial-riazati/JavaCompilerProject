package compiler;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java_cup.runtime.*;

class Token {
    @Override
    public String toString() {
        return key + " " + value;
    }

    public Token(String value) {
        this.value = value;
    }

    public Token(String key, String value) {
        this.key = key;
        this.value = value;
    }

    String key = "";
    String value = "";
}

public class Scanner {
    private SymbolFactory sf = new DefaultSymbolFactory();
    Set<String> tokens = new HashSet<String>() {
        {
            add("__func__");
            add("__line__");
            add("bool");
            add("break");
            add("btoi");
            add("class");
            add("continue");
            add("define");
            add("double");
            add("dtoi");
            add("else");
            add("for");
            add("if");
            add("import");
            add("int");
            add("itob");
            add("itod");
            add("new");
            add("NewArray");
            add("null");
            add("Print");
            add("private");
            add("public");
            add("ReadInteger");
            add("ReadLine");
            add("return");
            add("string");
            add("this");
            add("void");
            add("while");
        }
    };
    public static List<Token> data;
    int index = 0;
    private final Preprocessor pp;
    public String text;

    public Scanner(File file) throws Exception {
        data = new ArrayList<>();
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pp = new Preprocessor(contentBuilder.toString());
        this.text = pp.excute();
        //this.data.addAll(getDefineTokens());
        data.addAll(getToken());
        //  checkAllTokens();
    }


    private void checkAllTokens() throws Exception {
        String x = text.replaceAll("\n", "");
        //x = x.replaceAll(" ", "");
        StringBuilder y = new StringBuilder();
        for (Token k : data) {
            y.append(k.value);
        }
        System.out.println(x);
        System.out.println(y);
      /*  if(!x.equals(y))
            throw new Exception("Syntax Error");*/
    }

    public Symbol next_token() {
        if (index == data.size())
            return sf.newSymbol("EOF", sym.EOF);
        Token tmp = data.get(index++);
        switch (tmp.key.equals("") ? tmp.value : tmp.key) {
            case ("import"):
                return sf.newSymbol("IMPORT", sym.IMPORT);
            case ("define"):
                return sf.newSymbol("DEFINE", sym.DEFINE);
            case ("DEFINECHARS"):
                return sf.newSymbol("DEFINECHARS", sym.DEFINECHARS);
            case (";"):
                return sf.newSymbol("SEMI", sym.SEMI);
            case ("int"):
                return sf.newSymbol("INT", sym.INT);
            case ("double"):
                return sf.newSymbol("DOUBLE", sym.DOUBLE);
            case ("bool"):
                return sf.newSymbol("BOOL", sym.BOOL);
            case ("string"):
                return sf.newSymbol("STRING", sym.STRING);
            case ("["):
                return sf.newSymbol("OSB", sym.OSB);
            case ("]"):
                return sf.newSymbol("CSB", sym.CSB);
            case ("[]"):
                return sf.newSymbol("OCSB", sym.OCSB);
            case ("("):
                return sf.newSymbol("ORB", sym.ORB);
            case (")"):
                return sf.newSymbol("CRB", sym.CRB);
            case ("void"):
                return sf.newSymbol("VOID", sym.VOID);
            case (","):
                return sf.newSymbol("COMMA", sym.COMMA);
            case ("class"):
                return sf.newSymbol("CLASS", sym.CLASS);
            case ("{"):
                return sf.newSymbol("OCB", sym.OCB);
            case ("}"):
                return sf.newSymbol("CCB", sym.CCB);
            case ("private"):
                return sf.newSymbol("PRIVATE", sym.PRIVATE);
            case ("public"):
                return sf.newSymbol("PUBLIC", sym.PUBLIC);
            case ("if"):
                return sf.newSymbol("IF", sym.IF);
            case ("else"):
                return sf.newSymbol("ELSE", sym.ELSE);
            case ("while"):
                return sf.newSymbol("WHILE", sym.WHILE);
            case ("for"):
                return sf.newSymbol("FOR", sym.FOR);
            case ("break"):
                return sf.newSymbol("BREAK", sym.BREAK);
            case ("continue"):
                return sf.newSymbol("CONTINUE", sym.CONTINUE);
            case ("="):
                return sf.newSymbol("ASSIGN", sym.ASSIGN);
            case ("+="):
                return sf.newSymbol("PLUSA", sym.PLUSA);
            case ("-="):
                return sf.newSymbol("MINUSA", sym.MINUSA);
            case ("*="):
                return sf.newSymbol("MULTA", sym.MULTA);
            case ("/="):
                return sf.newSymbol("DIVA", sym.DIVA);
            case ("this"):
                return sf.newSymbol("THIS", sym.THIS);
            case ("+"):
                return sf.newSymbol("PLUS", sym.PLUS);
            case ("-"):
                return sf.newSymbol("MINUS", sym.MINUS);
            case ("*"):
                return sf.newSymbol("MULT", sym.MULT);
            case ("/"):
                return sf.newSymbol("DIV", sym.DIV);
            case ("%"):
                return sf.newSymbol("PERCENT", sym.PERCENT);
            case ("<"):
                return sf.newSymbol("LESS", sym.LESS);
            case ("<="):
                return sf.newSymbol("LESSEQ", sym.LESSEQ);
            case (">"):
                return sf.newSymbol("MORE", sym.MORE);
            case (">="):
                return sf.newSymbol("MOREEQ", sym.MOREEQ);
            case ("=="):
                return sf.newSymbol("EQEQ", sym.EQEQ);
            case ("!="):
                return sf.newSymbol("NOTEQ", sym.NOTEQ);
            case ("&&"):
                return sf.newSymbol("AND", sym.AND);
            case ("||"):
                return sf.newSymbol("OR", sym.OR);
            case ("!"):
                return sf.newSymbol("NOT", sym.NOT);
            case ("ReadLine"):
                return sf.newSymbol("READLINE", sym.READLINE);
            case ("new"):
                return sf.newSymbol("NEW", sym.NEW);
            case ("itod"):
                return sf.newSymbol("ITOD", sym.ITOD);
            case ("dtoi"):
                return sf.newSymbol("DTIO", sym.DTIO);
            case ("itob"):
                return sf.newSymbol("ITOB", sym.ITOB);
            case ("btoi"):
                return sf.newSymbol("BTIO", sym.BTIO);
            case ("__line__"):
                return sf.newSymbol("LINE", sym.LINE);
            case ("__func__"):
                return sf.newSymbol("FUNC", sym.FUNC);
            case ("."):
                return sf.newSymbol("DOT", sym.DOT);
            case ("T_INTLITERAL"):
                return sf.newSymbol("T_INTLITERAL", sym.T_INTLITERAL, Integer.valueOf(tmp.value));
            case ("T_DOUBLELITERAL"):
                return sf.newSymbol("T_DOUBLELITERAL", sym.T_DOUBLELITERAL, Double.valueOf(tmp.value));
            case ("T_BOOLEANLITERAL"):
                return sf.newSymbol("T_BOOLEANLITERAL", sym.T_BOOLEANLITERAL, Boolean.valueOf(tmp.value));
            case ("T_STRINGLITERAL"):
                return sf.newSymbol("T_STRINGLITERAL", sym.T_STRINGLITERAL, tmp.value);
            case ("T_ID"):
                return sf.newSymbol("ID", sym.ID, tmp.value);
            case ("null"):
                return sf.newSymbol("NULL", sym.NULL);
            case ("return"):
                return sf.newSymbol("RETURN", sym.RETURN);
            case ("Print"):
                return sf.newSymbol("PRINT", sym.PRINT);
            case ("ReadInteger"):
                return sf.newSymbol("READINTEGER", sym.READINTEGER);
            case ("NewArray"):
                return sf.newSymbol("NEWARRAY", sym.NEWARRAY);
            default:
                return sf.newSymbol("EOF", sym.EOF);
        }
    }


    private List<Token> getToken() {
        List<Token> data = new ArrayList<>();
        // String pattern = "(__func__)|(__line__)|(\"(?:(?:\\S| )*?(?:\\\")*)*\")|(0[xX][0-9a-fA-F]+)|(\\d+\\.\\d*[Ee][+-]?\\d*)|(\\d+\\.\\d*)|(\\|\\||&&|]|\\[|}|\\{|;|\\.|,|\\(|\\)|==|=|\\+=|\\*=|-=|/=|!=|<=|>=|<|>|\\+|-|\\*|/|!|%)|([a-zA-Z][a-zA-Z0-9_]*)|(\\d+)";
        String pattern = "(__func__)|(__line__)|(\"(?:(?:\\S| )*?(?:\\\\\")*)*\")|(0[xX][0-9a-fA-F]+)|(\\d+\\.\\d*[Ee][+-]?\\d+)|(\\d+\\.\\d*)|(\\|\\||&&|\\[\\s*]|]|\\[|}|\\{|;|\\.|,|\\(|\\)|==|=|\\+=|\\*=|-=|/=|!=|<=|>=|<|>|\\+|-|\\*|/|!|%)|([a-zA-Z_][a-zA-Z0-9_]*)|(\\d+)";

        // "(\"[\S
        // ]*\")|(\'|\"|\]|\[|\}|\{|\;|\(|\)|\=\=|\=|\,|\&\&|\&|\|\||\||\+=|--|\+\+|!=|<=|>=|<|>|\+|\-|*|/|!|[a-zA-Z]+\\d*|\\d+)";
        Matcher m = Pattern.compile(pattern).matcher(text);
        String x;
        String tmp;
        while (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                if ((x = m.group(i)) != null) {
                    if (x.startsWith("[") && x.endsWith("]"))
                        data.add(new Token("[]"));
                    else if (!(tmp = tokenRecognizer(x)).equals("")) {
                        if (tmp.equals("_"))
                            continue;
                        data.add(new Token(tmp, x));
                    }
                    else
                        data.add(new Token(x));
                }
            }
        }
        return data;
    }

    private String tokenRecognizer(String x) {
        if (tokens.contains(x))
            return "";
        else {
            if (x.startsWith("_"))
                return "_";
            if (x.startsWith("\"") && x.endsWith("\"") && x.length() >= 2)
                return "T_STRINGLITERAL";
            else if (isInt(x))
                return "T_INTLITERAL";
            else if (isDouble(x))
                return "T_DOUBLELITERAL";
            else if (x.equals("true") || x.equals("false"))
                return "T_BOOLEANLITERAL";
            else if (isOperand(x))
                return "";
            else
                return "T_ID";
        }

    }

    private boolean isOperand(String x) {
        return !Character.isLetterOrDigit(x.charAt(0));
    }

    public static boolean isDouble(String strNum) {
        if (strNum == null) {
            return false;
        }
        if (Character.isDigit(strNum.charAt(0)) && (strNum.contains("e") || strNum.contains("E")))
            return true;
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isInt(String strNum) {
        String x = strNum;
        if (strNum == null) {
            return false;
        }
        if (strNum.startsWith("0x") | strNum.startsWith("0X"))
            return true;

        return x.matches("[0-9]+");


    }
}

class Preprocessor {
    String definePattern = "define +(.*?) +(.*)";
    // public String text = "";
    Map<String, String> defineMatches;
    String ref;

    public Preprocessor(String text) {
        //this.text = text;
        ref = text;
        defineMatches = GetAllMatches();
    }

    public String excute() {
        RemoveProcess();
        ReplaceProcess();
        return ref;
    }

    // replace defined value in text
    private void ReplaceProcess() {
        defineMatches.forEach((t, l) -> {
            String pattern = "\\b" + t + "\\b";
            Matcher m = Pattern.compile(pattern).matcher(ref);
            while (m.find()) {
                ref = m.replaceAll(l);
            }
        });
    }

    // remove define statements
    private void RemoveProcess() {
        RemoveComments();
    }

    private void RemoveComments() {
        String pattern = "/\\*(.|\\s)*?\\*/|(//.*)";
        Matcher m = Pattern.compile(pattern).matcher(ref);
        while (m.find()) {
            ref = m.replaceAll("");
        }
    }

    private Map<String, String> GetAllMatches() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        List<String> lines = ref.lines().collect(Collectors.toList());
        for (String line : lines) {
            if (!line.equals("") && !line.startsWith("define") && !line.startsWith("import")) {
                break;
            } else {
                Matcher m = Pattern.compile(definePattern).matcher(line);
                while (m.find()) {
                    map.put(m.group(1), m.group(2));
                    Scanner.data.add(new Token("define"));
                    Scanner.data.add(new Token("T_ID", m.group(1)));
                    if (!m.group(2).equals(""))
                        Scanner.data.add(new Token("DEFINECHARS", m.group(2)));
                    //System.out.println(m.group(0));
                    ref = ref.replace(m.group(0), "");
                }

            }
        }

        return map;
    }

}
