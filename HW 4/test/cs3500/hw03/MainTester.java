package cs3500.hw03;

import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;

/**
 * Created by Gus on 5/27/2017.
 */
public class MainTester {
  /**
   * main to test and see whether code works.
   * @param args                  Expected.
   * @throws IllegalArgumentException In case of illegal argument.
   */
  public static void main(String[] args) throws IllegalArgumentException {
    PlayerInput rd = new PlayerInput(" ");
    GameOutput ap = new GameOutput();
    FreecellModel model = new FreecellModel();
    List<Card> deck = model.getDeck();
    FreecellController testOne = new FreecellController(rd, ap);
    testOne.playGame(deck, model, 8, 4, false);
  }
}

