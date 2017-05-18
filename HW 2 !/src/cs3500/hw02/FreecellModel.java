package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gus on 5/17/2017.
 */
public class FreecellModel implements FreecellOperations {
  private String[] valueArray =

          {
                  "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
          };

  private String[] suitArray = {"\u2764", "\u2663", "\u2666", "\u2660"};

  public List<Card> getDeck() {
    List<Card> deck = new ArrayList();

    for (int i = 0; i < suitArray.length; i++) {
      for (int j = 0; j < valueArray.length; j++) {
        deck.add(new Card(suitArray[i], valueArray[j]));
      }
    }
    return deck;
  }

  public void move(PileType source, int pileNumber, int cardIndex, PileType destination, int destPileNumber) throws IllegalArgumentException {

  }

  public boolean isGameOver() {
    return false;
  }

  public String getGameState() {
    return null;
  }

}
