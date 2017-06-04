package cs3500.hw04;

import java.io.StringReader;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw03.FreecellController;

/**
 * Created by Gus on 6/3/2017.
 */
public class MainTester {
  static Appendable sBuilder = new StringBuilder();
  static Readable sReader = new StringReader("C1 1 O1");

  /**
   * A test built to actually see the functionality of the code.
   *
   * @param args A little something for the compiler.
   */
  public static void main(String[] args) {
    FreecellController currentGame = new FreecellController(sReader, sBuilder);
    FreecellModel model = new FreecellModel();
    List<Card> deck = model.getDeck();
    currentGame.playGame(deck, model, 8, 4, true);
  }
}
