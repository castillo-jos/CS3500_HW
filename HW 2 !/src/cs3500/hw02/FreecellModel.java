package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Gus on 5/17/2017.
 */
public class FreecellModel implements FreecellOperations {
  private String[] valueArray = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

  private String club = "\u2663";
  private String heart = "\u2663";
  private String diamond = "\u2666";
  private String spade = "\u2660";

  private String[] suitArray = {"\u2764", "\u2663", "\u2666", "\u2660"};
  private Card frontOfPile = null;
  private Card[] cascadePiles;
  private Card[] openPiles;
  private List<Card> heartFoundation = new ArrayList<>();
  private List<Card> clubFoundation = new ArrayList<>();
  private List<Card> diamondFoundation = new ArrayList<>();
  private List<Card> spadeFoundation = new ArrayList<>();


  @Override
  public List getDeck() {
    List<Card> deck = new ArrayList();

    for (int i = 0; i < suitArray.length; i++) {
      for (int j = 0; j < valueArray.length; j++) {
        deck.add(new Card(suitArray[i], valueArray[j]));
      }
    }
    return deck;
  }

  /**
   * Deal a new game of freecell with the given deck, with or without shuffling
   * it first. This method first verifies that the deck is valid.
   * <p>
   * It deals the deck among the cascade piles in roundrobin fashion. Thus if
   * there are 4 cascade piles, the 1st pile will get cards 0, 4, 8, ..., the
   * 2nd pile will get cards 1, 5, 9, ..., the 3rd pile will get cards 2, 6, 10,
   * ... and the 4th pile will get cards 3, 7, 11, ....
   * </p>
   * Depending on the number of cascade piles, they may have a different number
   * of cards
   *
   * @param deck            the deck to be dealt
   * @param numCascadePiles number of cascade piles
   * @param numOpenPiles    number of open piles
   * @param shuffle         if true, shuffle the deck else deal the deck as-is
   */
  public void startGame(List deck, int numCascadePiles,
                        int numOpenPiles, boolean shuffle) throws IllegalArgumentException {
    

    List<Card> heartCheck = new ArrayList();
    List<Card> clubCheck = new ArrayList();
    List<Card> diamondCheck = new ArrayList();
    List<Card> spadeCheck = new ArrayList();

    List<Card> tempDeck = new ArrayList();
    for (int i = 0; i < deck.size(); i++) {
      tempDeck.add((Card) deck.get(i));
    }

    openPiles = new Card[numCascadePiles];

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
    if (!(isSuitComplete(heartCheck) && isSuitComplete(diamondCheck)
            && isSuitComplete(spadeCheck) && isSuitComplete(clubCheck))) {
      throw new IllegalArgumentException();
    }


    if (shuffle) {
      shuffleDeck(tempDeck);
    }

    cascadePiles = createCascadePiles(tempDeck, numCascadePiles);
    openPiles = new Card[numOpenPiles];

  }

  /**
   * Move a card from the given source pile to the given destination pile, if
   * the move is valid.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible {@link PileType})
   */
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber) throws IllegalArgumentException {

    int currentEmptySpaces = 0;
    for (Card spaces : openPiles) {
      if (spaces == null) {
        currentEmptySpaces++;
      }
    }

    if (destination == PileType.OPEN) {
      if (currentEmptySpaces == 0) {
        throw new IllegalArgumentException();
      } else {
        if (openPiles[destPileNumber] != null) {
          throw new IllegalArgumentException();
        }
        if (source == PileType.CASCADE) {
          Card cardBehind = cascadePiles[pileNumber];
          Card temp = cascadePiles[pileNumber];
          Card nullSearcher = cascadePiles[pileNumber].cardInFront;
          while (nullSearcher != null) {
            cardBehind = temp;
            temp = nullSearcher;
            nullSearcher = nullSearcher.cardInFront;
          }
          temp.cardIsInPile = PileType.OPEN;
          openPiles[destPileNumber] = temp;
          cardBehind.cardInFront = null;
        }
      }

      Card temp = cascadePiles[pileNumber];
      Card nullSearcher = cascadePiles[pileNumber].cardInFront;
      while (nullSearcher != null) {
        temp = nullSearcher;
        nullSearcher = temp.cardInFront;
      }

    }
    Card temp = cascadePiles[pileNumber];
    Card nullSearcher = cascadePiles[pileNumber].cardInFront;
    if (destination == PileType.FOUNDATION) {
      if (source == PileType.CASCADE) {
        while (nullSearcher != null) {
          temp = nullSearcher;
          nullSearcher = temp.cardInFront;
        }
        sortFoundationPiles(temp);
      } else {
        temp = openPiles[pileNumber];
        sortFoundationPiles(temp);
      }
    }

    if (destination == PileType.CASCADE) {
      if (source == PileType.CASCADE) {
        temp = cascadePiles[pileNumber];
        nullSearcher = temp.cardInFront;
        while (nullSearcher != null) {
          temp = nullSearcher;
          nullSearcher = nullSearcher.cardInFront;
        }
      } else {
        temp = openPiles[pileNumber];
        removeFromPile(temp);
      }
      Card desTemp = cascadePiles[destPileNumber];
      Card desNullSeacher = desTemp.cardInFront;
      while (desNullSeacher != null) {
        desTemp = desNullSeacher;
        desNullSeacher = desNullSeacher.cardInFront;
      }
      if (desTemp == null) {
        cascadePiles[destPileNumber] = temp;
      } else {
        if ((temp.getSuit() == spade || temp.getSuit() == club)
                && (desTemp.getSuit() == heart || desTemp.getSuit() == diamond)
                || (temp.getSuit() == heart || temp.getSuit() == diamond)
                && (desTemp.getSuit() == spade || desTemp.getSuit() == club)) {
          int valueIndex = 0;
          boolean valueMatch = false;
          while (temp.getValue() != valueArray[valueIndex]) {
            valueIndex++;
          }
          if (valueArray[valueIndex + 1] == desTemp.getValue()) {
            addToPile(desTemp, temp, desTemp.cardInBack);
          }
        }
      }
    }
  }

  /**
   * Signal if the game is over or not.
   *
   * @return true if game is over, false otherwise
   */

  public boolean isGameOver() {
    return isFoundationComplete(heartFoundation) && isFoundationComplete(clubFoundation)
            && isFoundationComplete(diamondFoundation) && isFoundationComplete(spadeFoundation);
  }

  /**
   * Return the present state of the game as a string. The string is formatted
   * as follows:
   * <pre>
   * F1:[b]f11,[b]f12,[b],...,[b]f1n1[n] (Cards in foundation pile 1 in order)
   * F2:[b]f21,[b]f22,[b],...,[b]f2n2[n] (Cards in foundation pile 2 in order)
   * ...
   * Fm:[b]fm1,[b]fm2,[b],...,[b]fmnm[n] (Cards in foundation pile m in
   * order)
   * O1:[b]o11[n] (Cards in open pile 1)
   * O2:[b]o21[n] (Cards in open pile 2)
   * ...
   * Ok:[b]ok1[n] (Cards in open pile k)
   * C1:[b]c11,[b]c12,[b]...,[b]c1p1[n] (Cards in cascade pile 1 in order)
   * C2:[b]c21,[b]c22,[b]...,[b]c2p2[n] (Cards in cascade pile 2 in order)
   * ...
   * Cs:[b]cs1,[b]cs2,[b]...,[b]csps (Cards in cascade pile s in order)
   *
   * where [b] is a single blankspace, [n] is newline. Note that there is no
   * newline on the last line
   * </pre>
   *
   * @return the formatted string as above
   */
  public String getGameState() {
    for (Card check : openPiles) {
      if (check != null) {
        if (check.cardInFront != null) {
          throw new IllegalArgumentException();
        }
      }
    }
    StringBuffer gameState = new StringBuffer();
    gameState.append("F1: ");
    for (Card x : heartFoundation) {
      gameState.append(x + " ");
    }
    gameState.append("\n");
    gameState.append("F2: ");
    for (Card x : spadeFoundation) {
      gameState.append(x + " ");
    }
    gameState.append("\n");
    gameState.append("F3: ");
    for (Card x : diamondFoundation) {
      gameState.append(x + " ");
    }
    gameState.append("\n");
    gameState.append("F4: ");
    for (Card x : clubFoundation) {
      gameState.append(x + " ");
    }
    gameState.append("\n");

    for (int k = 0; k < openPiles.length; k++) {
      if (openPiles[k] == null) {
        gameState.append("O" + (k + 1) + ": \n");
      } else {
        gameState.append("O" + (k + 1) + ": " + openPiles[k] + "\n");
      }
    }
    for (int j = 0; j < cascadePiles.length; j++) {
      gameState.append("C" + (j + 1) + ": ");
      Card temp = cascadePiles[j];
      Card nullSearcher = cascadePiles[j].cardInFront;
      gameState.append(temp);
      while (nullSearcher != null) {
        gameState.append(", ");
        temp = nullSearcher;
        gameState.append(temp);
        nullSearcher = temp.cardInFront;
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

  private boolean isFoundationComplete(List<Card> singleSuit) {
    if (singleSuit.size() != 13) {
      return false;
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
          return false;
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

  private void addToPile(Card frontOfPile, Card newCard, Card cardBehind) {
    frontOfPile.cardInFront = newCard;
    frontOfPile.cardInBack = cardBehind;
  }

  private void removeFromPile(Card frontOfPile) {
    if (frontOfPile == null) {
      throw new IllegalArgumentException();
    }
    frontOfPile.cardInBack.cardInFront = null;
  }

  private Card[] createCascadePiles(List<Card> deck, int numOfPiles) {
    Card[] cascadePiles = new Card[numOfPiles];
    for (int i = 0; i < numOfPiles; i++) {
      cascadePiles[i] = deck.get(i);
    }

    int x = 0;
    while (x != numOfPiles) {
      deck.remove(0);
      x++;
    }
    int i = 0;

    while (!deck.isEmpty()) {
      if (i < numOfPiles) {
        Card cardBehind = cascadePiles[i];
        Card temp = cascadePiles[i];
        Card nullSearcher = cascadePiles[i].cardInFront;
        while (nullSearcher != null) {
          cardBehind = temp;
          temp = nullSearcher;
          nullSearcher = temp.cardInFront;
        }
        addToPile(temp, deck.get(0), cardBehind);
        temp.cardIsInPile = PileType.CASCADE;
        deck.remove(0);
        i++;
      } else {
        i = 0;
      }
    }

    return cascadePiles;
  }

  private void sortFoundationPiles(Card temp) {
    switch (temp.getSuit()) {
      case "\u2764":
        if (temp.getValue() == valueArray[heartFoundation.size()]) {
          heartFoundation.add(temp);
          temp.cardIsInPile = PileType.FOUNDATION;
        } else {
          throw new IllegalArgumentException();
        }
        removeFromPile(temp);
        break;

      case "\u2663":
        if (temp.getValue() == valueArray[clubFoundation.size()]) {
          clubFoundation.add(temp);
          temp.cardIsInPile = PileType.FOUNDATION;
        } else {
          throw new IllegalArgumentException();
        }
        removeFromPile(temp);
        break;

      case "\u2666":
        if (temp.getValue() == valueArray[diamondFoundation.size()]) {
          diamondFoundation.add(temp);
          temp.cardIsInPile = PileType.FOUNDATION;
        } else {
          throw new IllegalArgumentException();
        }
        removeFromPile(temp);
        break;

      case "\u2660":
        if (temp.getValue() == valueArray[spadeFoundation.size()]) {
          spadeFoundation.add(temp);
          temp.cardIsInPile = PileType.FOUNDATION;
        } else {
          throw new IllegalArgumentException();
        }
        removeFromPile(temp);
        break;
      default:
        throw new IllegalArgumentException();
    }
  }
}
