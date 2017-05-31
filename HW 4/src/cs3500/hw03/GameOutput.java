package cs3500.hw03;


/**
 * Created by Gus on 5/27/2017.
 */
public class GameOutput implements Appendable {
  StringBuffer gOutput = new StringBuffer();

  public String toString() {
    return gOutput.toString();
  }

  @Override
  public Appendable append(CharSequence csq) throws IllegalArgumentException {
    gOutput.append(csq);
    gOutput.append("\n");
    return null;
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Appendable append(char c) throws IllegalArgumentException {
    gOutput.append(c);
    return null;
  }
}
