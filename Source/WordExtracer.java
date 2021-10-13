package Source;

public class WordExtracer {
  private final Preprocessor pp;

  public WordExtracer() {
    pp = new Preprocessor("");
    
  }

  public String[] execute() {
    return new String[3];
  }

}
//(;|\(|\)|==|=|\+=|--|\+\+|!=|<=|>=|<|>|\+|-|!|[a-zA-Z]+\d*|\d+)\s*|\"(.*\s*)+\"
// (;|\(|\)|==|=|\+=|--|\+\+|!=|<=|>=|<|>|\+|-|!|[a-zA-Z]+\d*|\d+)\s*|\"(.*\s*)*?\"
// (\]|\[|}|{|;|\(|\)|==|=|\+=|--|\+\+|!=|<=|>=|<|>|\+|-|!|[a-zA-Z]+\d*|\d+) *|\"(.* *)*?\"