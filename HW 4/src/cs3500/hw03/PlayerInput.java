package cs3500.hw03;

import java.nio.CharBuffer;

/**
 * Created by Gus on 5/27/2017.
 */
public class PlayerInput implements Readable {

  public String input = new String();

  PlayerInput(String input) {
    this.input = input;
  }

  @Override
  public int read(CharBuffer cb) throws IllegalArgumentException {
    int i = 0;
    while (i != input.length()) {
      cb.append(input.charAt(i));
      i++;
    }
    return i;
  }
}
