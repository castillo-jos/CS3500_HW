package cs3500.hw02;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Gus on 5/17/2017.
 */
public class FreecellModel implements FreecellOperations {
  private String[] valueArray =

          {
                  "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
          };

  private String[] suitArray = {"\u2764", "\u2663", "\u2666", "\u2660"};
  private Card frontOfPile = null;
  private Card[] cascadePiles;
  private Card[] openPiles;
  private Card[] foundationPiles = new Card[4];

  public List<Card> getDeck() {
    List<Card> deck = new ArrayList();

    for (int i = 0; i < suitArray.length; i++) {
      for (int j = 0; j < valueArray.length; j++) {
        deck.add(new Card(suitArray[i], valueArray[j]));
      }
    }
    return deck;
  }

  public void startGame(List deck, int numCascadePiles, int numOpenPiles, boolean shuffle) throws IllegalArgumentException {
    List<Card> heartCheck = new ArrayList();
    List<Card> clubCheck = new ArrayList();
    List<Card> diamondCheck = new ArrayList();
    List<Card> spadeCheck = new ArrayList();

    for (int i = 0; i < deck.size(); i++) {
      Card tempCard = (Card) deck.get(i);
      String cardSuit = tempCard.getSuit();
      switch (cardSuit) {
        case "\u2764":
          heartCheck.add(tempCard);
          break;

        case "\u2663":
          spadeCheck.add(tempCard);
          break;

        case "\u2666":
          diamondCheck.add(tempCard);
          break;
        case "\u2660":
          clubCheck.add(tempCard);
          break;

        default:
          throw new IllegalArgumentException();
      }
    }
    if (!(isSuitComplete(heartCheck) && isSuitComplete(diamondCheck) && isSuitComplete(spadeCheck) && isSuitComplete(clubCheck))) {
      throw new IllegalArgumentException();
    }

    int[] linkOfNumbers = {2, 3, 4, 5, 6};

    if (shuffle == true) {
      shuffleDeck(deck);
    }
    cascadePiles = createCascadePiles(deck, numCascadePiles);
    openPiles = new Card[numOpenPiles];


  }

  public void move(PileType source, int pileNumber, int cardIndex, PileType destination, int destPileNumber) throws IllegalArgumentException {

  }

  public boolean isGameOver() {
    return false;
  }

  public String getGameState() {
    StringBuffer gameState = new StringBuffer();
    int x = 0;
    for(int j = 0; j < cascadePiles.length; j++){
      gameState.append("C" + (j+1) + ": ");
      Card temp = cascadePiles[j];
      Card nullSearcher = cascadePiles[j].cardInFront;
      while(nullSearcher != null){
        gameState.append(temp);
        temp = nullSearcher;
        nullSearcher = temp.cardInFront;
        x++;
      }
      gameState.append("\n");
    }
    return gameState.toString();
  }

  private boolean isSuitComplete(List<Card> singleSuit) {
    if (singleSuit.size() != 13) {
      throw new IllegalArgumentException();
    } else {
      int valueCount = 0;

      for (int j = 0; j < valueArray.length; j++) {

        for (int i = 0; i < singleSuit.size(); i++) {
          String cardValue = singleSuit.get(i).getValue();
          if (cardValue == valueArray[j]) {
            valueCount++;
          }
        }
        if (valueCount != 1) {
          throw new IllegalArgumentException();
        }
        valueCount = 0;
      }
    }
    return true;
  }

  private void shuffleDeck(List<Card> deck) {
    long seed = System.nanoTime();
    Collections.shuffle(deck, new Random(seed));
  }

  private void addToPile(Card frontOfPile, Card newCard) {
    frontOfPile.cardInFront = newCard;
  }

  private Card removeFromPile(Card frontOfPile) {
    if (frontOfPile == null) {
      throw new IllegalArgumentException();
    }
    return frontOfPile.cardBehind;
  }

  private Card[] createCascadePiles(List<Card> deck, int numOfPiles) {
    Card[] cascadePiles = new Card[numOfPiles];
    for (int i = 0; i < numOfPiles; i++) {
      cascadePiles[i] = deck.get(i);
      deck.remove(i);
    }
    int i = 0;

    while(!deck.isEmpty()){
      if(i < numOfPiles){
        Card temp = cascadePiles[i];
        Card nullSearcher = cascadePiles[i].cardInFront;
        while(nullSearcher != null){
          temp = nullSearcher;
          nullSearcher = temp.cardInFront;
        }
        addToPile(temp, deck.get(0));
        temp.cardIsInPile = PileType.CASCADE;
        deck.remove(0);
        i++;
      }
      else{
        i = 0;
      }
    }
    return cascadePiles;
  }
}
