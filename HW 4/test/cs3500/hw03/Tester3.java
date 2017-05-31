package cs3500.hw03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;

/**
 * Created by Gus on 5/26/2017.
 */
public class Tester3 {
  @Test(expected = IllegalArgumentException.class)
  public void startGameCascadeCheck() {
    FreecellOperations model = new FreecellModel();
    List deck = new ArrayList();
    deck = model.getDeck();
    model.startGame(deck, 0, 2, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameOpenCheck() {
    FreecellOperations model = new FreecellModel();
    List deck = new ArrayList();
    deck = model.getDeck();
    model.startGame(deck, 5, 0, false);
  }
}
