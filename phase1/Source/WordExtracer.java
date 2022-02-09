package Source;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Tokens {
  __func___token, __line___token, bool_token, break_token, btoi_token, class_token, continue_token, define_token,
  double_token, dtoi_token, else_token, for_token, _token, if_token, import_token, int_token, itob_token, itod_token,
  new_token, NewArray_token, null_token, Print_token, private_token, public_token, ReadInteger_token, ReadLine_token,
  return_token, string_token, this_token, void_token, while_token
}

public class WordExtracer {
  private final Preprocessor pp;
  public String text;

  public WordExtracer(String text) {
    pp = new Preprocessor(text);
    this.text = pp.excute();
  }

  public String[] execute() {
    getToken();
    return new String[3];
  }

  private Map<String, Tokens> getToken() {
    Map<String, Tokens> data = new HashMap<String, Tokens>();
    String pattern ="(\"[\\S ]*\")|('|\"|]|[|}|{|;|(|)|==|=|,|&&|&|+=|--|++|!=|<=|>=|<|>|+|-|!|[a-zA-Z]+\\d*|\\d+)";
                  //"(\"[\S ]*\")|(\'|\"|\]|\[|\}|\{|\;|\(|\)|\=\=|\=|\,|\&\&|\&|\|\||\||\+=|--|\+\+|!=|<=|>=|<|>|\+|\-|*|/|!|[a-zA-Z]+\\d*|\\d+)";
    Matcher m = Pattern.compile(pattern).matcher(text);
    String x;
    while (m.find()) {
      for (int i = 1; i <= m.groupCount(); i++) {
        if ((x = m.group(i)) != null)
          data.put(x, tokenRecognizer(x));
      }
    }
    return data;
  }

  private Tokens tokenRecognizer(String x) {
    return null;
  }
}
// (;|\(|\)|==|=|\+=|--|\+\+|!=|<=|>=|<|>|\+|-|!|[a-zA-Z]+\d*|\d+)\s*|\"(.*\s*)+\"
// (;|\(|\)|==|=|\+=|--|\+\+|!=|<=|>=|<|>|\+|-|!|[a-zA-Z]+\d*|\d+)\s*|\"(.*\s*)*?\"
// (\]|\[|}|{|;|\(|\)|==|=|\+=|--|\+\+|!=|<=|>=|<|>|\+|-|!|[a-zA-Z]+\d*|\d+)
// *|\"(.* *)*?\"
// (;|\(|\)|==|=|\+=|--|\+\+|!=|<=|>=|<|>|\+|-|!|[a-zA-Z]+\d*|\d+)\s*|(".*\s*?")