package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gus on 5/20/2017.
 */
public class Tester {

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
