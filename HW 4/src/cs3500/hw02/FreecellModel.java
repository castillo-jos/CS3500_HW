package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gus on 5/17/2017.
 */
public class FreecellModel implements FreecellOperations {
  protected String[] valueArray = {"A", "2", "3", "4", "5", "6", "7"
          , "8", "9", "10", "J", "Q", "K"};

  protected final String club = "♠";
  protected final String heart = "♥";
  protected final String diamond = "♦";
  protected final String spade = "♣";

  protected String[] suitArray = {heart, club, diamond, spade};
  protected ArrayList<List<Card>> cascadePiles;
  protected Card[] openPiles;
  protected List<Card> heartFoundation = new ArrayList<>();
  protected List<Card> clubFoundation = new ArrayList<>();
  protected List<Card> diamondFoundation = new ArrayList<>();
  protected List<Card> spadeFoundation = new ArrayList<>();

  /**
   * getDeck has been changed to return an ArrayList only capable of holding Card objects.
   */
  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<Card>() {
    };

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
    /**
     * A class invariant that check whether the deck passed to this method is complete.
     */

    if (deck == null) {
      throw new IllegalArgumentException();
    }

    if (deck.size() != 52) {
      throw new IllegalArgumentException();
    }

    if (numCascadePiles < 4) {
      throw new IllegalArgumentException();
    }

    if (numOpenPiles < 1) {
      throw new IllegalArgumentException();
    }

    List<Card> heartCheck = new ArrayList();
    List<Card> clubCheck = new ArrayList();
    List<Card> diamondCheck = new ArrayList();
    List<Card> spadeCheck = new ArrayList();

    List<Card> tempDeck = new ArrayList();
    for (int i = 0; i < deck.size(); i++) {
      tempDeck.add((Card) deck.get(i));
    }

    for (int i = 0; i < deck.size(); i++) {
      Card tempCard = (Card) deck.get(i);
      String cardSuit = tempCard.getSuit();
      switch (cardSuit) {
        case heart:
          heartCheck.add(tempCard);
          break;

        case spade:
          spadeCheck.add(tempCard);
          break;

        case diamond:
          diamondCheck.add(tempCard);
          break;
        case club:
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
      long seed = System.nanoTime();
      Collections.shuffle(deck, new Random(seed));
    }

    openPiles = new Card[numOpenPiles];
    cascadePiles = createCascadePiles(deck, numCascadePiles);
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

    if (pileNumber < 0 || destPileNumber < 0) {
      throw new IllegalArgumentException();
    }

    if (source.equals(PileType.CASCADE)) {
      if (pileNumber >= cascadePiles.size()) {
        throw new IllegalArgumentException();
      }
      if (!(0 <= cardIndex && cardIndex < cascadePiles.get(pileNumber).size())) {
        throw new IllegalArgumentException();
      }
    } else if (source.equals(PileType.OPEN)) {
      if (pileNumber >= openPiles.length) {
        throw new IllegalArgumentException();
      }
      if (!(0 <= cardIndex && cardIndex < 1)) {
        throw new IllegalArgumentException();
      }
    }

    if (destination.equals(PileType.OPEN)) {
      if (destPileNumber >= openPiles.length) {
        throw new IllegalArgumentException();
      }
    } else if (destination.equals(PileType.CASCADE)) {
      if (destPileNumber >= cascadePiles.size()) {
        throw new IllegalArgumentException();
      }
    } else if (destination.equals(PileType.FOUNDATION)) {
      if (destPileNumber >= 4) {
        throw new IllegalArgumentException();
      }
    }

    if (source.equals(PileType.CASCADE)) {
      if (pileNumber >= cascadePiles.size()) {
        throw new IllegalArgumentException();
      }
      if (cardIndex == 1) {
        cardIndex = cascadePiles.get(pileNumber).size() - 1;
      } else {
        throw new IllegalArgumentException();
      }
    } else if (source.equals(PileType.OPEN)) {
      if (pileNumber >= openPiles.length) {
        throw new IllegalArgumentException();
      }
      if (openPiles[pileNumber] == null) {
        throw new IllegalArgumentException();
      }
    } else {
      throw new IllegalArgumentException();
    }

    if (destination == PileType.OPEN) {
      if (openPiles[destPileNumber] != null) {
        throw new IllegalArgumentException();
      } else {
        if (source == PileType.CASCADE) {
          openPiles[destPileNumber] = cascadePiles.get(pileNumber).get(cardIndex);
          cascadePiles.get(pileNumber).remove(cardIndex);
        } else {
          openPiles[destPileNumber] = openPiles[pileNumber];
          openPiles[pileNumber] = null;
        }
      }
    } else if (destination == PileType.CASCADE) {
      if (source == PileType.CASCADE) {
        Card cardBase = cascadePiles.get(destPileNumber)
                .get(cascadePiles.get(destPileNumber).size() - 1);
        Card cardMoving = cascadePiles.get(pileNumber).get(cardIndex);
        if (cascadeCheck(cardBase, cardMoving)) {
          cascadePiles.get(destPileNumber).add(cardMoving);
          cascadePiles.get(pileNumber).remove(cardIndex);
        }
      } else {
        Card cardBase = (Card) cascadePiles.get(destPileNumber)
                .get(cascadePiles.get(destPileNumber).size() - 1);
        Card cardMoving = (Card) openPiles[pileNumber];
        if (cascadeCheck(cardBase, cardMoving)) {
          cascadePiles.get(destPileNumber).add(cardMoving);
          openPiles[pileNumber] = null;
        }
      }
    } else {
      if (source == PileType.CASCADE) {
        Card movingCard = cascadePiles.get(pileNumber).get(cardIndex);
        switch (movingCard.getSuit()) {
          case heart:
            if (heartFoundation.isEmpty()) {
              if (movingCard.getValue().equals("A")) {
                heartFoundation.add(movingCard);
                cascadePiles.get(pileNumber).remove(movingCard);
              } else {
                throw new IllegalArgumentException();
              }
            } else {
              if (valueArray[heartFoundation.size()].equals(movingCard.getSuit())) {
                heartFoundation.add(movingCard);
                cascadePiles.get(pileNumber).remove(movingCard);
              } else {
                throw new IllegalArgumentException();
              }
            }
            break;
          case spade:
            if (spadeFoundation.isEmpty()) {
              if (movingCard.getValue().equals("A")) {
                spadeFoundation.add(movingCard);
                cascadePiles.get(pileNumber).remove(movingCard);
              } else {
                throw new IllegalArgumentException();
              }
            } else {
              if (valueArray[spadeFoundation.size()] == movingCard.getValue()) {
                spadeFoundation.add(movingCard);
                cascadePiles.get(pileNumber).remove(movingCard);
              } else {
                throw new IllegalArgumentException();
              }
            }
            break;
          case diamond:
            if (diamondFoundation.isEmpty()) {
              if (movingCard.getValue().equals("A")) {
                diamondFoundation.add(movingCard);
                cascadePiles.get(pileNumber).remove(movingCard);
              } else {
                throw new IllegalArgumentException();
              }
            } else {
              if (valueArray[diamondFoundation.size()] == movingCard.getValue()) {
                diamondFoundation.add(movingCard);
                cascadePiles.get(pileNumber).remove(movingCard);
              } else {
                throw new IllegalArgumentException();
              }
            }
            break;
          case club:
            if (clubFoundation.isEmpty()) {
              if (movingCard.getValue().equals("A")) {
                clubFoundation.add(movingCard);
                cascadePiles.get(pileNumber).remove(movingCard);
              } else {
                throw new IllegalArgumentException();
              }
            } else {
              if (valueArray[clubFoundation.size()].equals(movingCard.getValue())) {
                clubFoundation.add(movingCard);
                cascadePiles.get(pileNumber).remove(movingCard);
              } else {
                throw new IllegalArgumentException();
              }
            }
            break;
          default:
            throw new IllegalArgumentException();
        }
      } else {
        Card movingCard = openPiles[pileNumber];
        switch (movingCard.getSuit()) {
          case heart:
            if (heartFoundation.isEmpty()) {
              if (movingCard.getValue().equals("A")) {
                heartFoundation.add(movingCard);
                openPiles[pileNumber] = null;
              } else {
                throw new IllegalArgumentException();
              }
            } else {
              if (valueArray[heartFoundation.size()].equals(movingCard.getValue())) {
                heartFoundation.add(movingCard);
                openPiles[pileNumber] = null;
              } else {
                throw new IllegalArgumentException();
              }
            }
            break;
          case spade:
            if (spadeFoundation.isEmpty()) {
              if (movingCard.getValue().equals("A")) {
                spadeFoundation.add(movingCard);
                openPiles[pileNumber] = null;
              } else {
                throw new IllegalArgumentException();
              }
            } else {
              if (valueArray[spadeFoundation.size()].equals(movingCard.getValue())) {
                spadeFoundation.add(movingCard);
                openPiles[pileNumber] = null;
              } else {
                throw new IllegalArgumentException();
              }
            }
            break;
          case diamond:
            if (diamondFoundation.isEmpty()) {
              if (movingCard.getValue().equals("A")) {
                diamondFoundation.add(movingCard);
                openPiles[pileNumber] = null;
              } else {
                throw new IllegalArgumentException();
              }
            } else {
              if (valueArray[diamondFoundation.size()].equals(movingCard.getValue())) {
                diamondFoundation.add(movingCard);
                openPiles[pileNumber] = null;
              } else {
                throw new IllegalArgumentException();
              }
            }
            break;
          case club:
            if (clubFoundation.isEmpty()) {
              if (movingCard.getValue().equals("A")) {
                clubFoundation.add(movingCard);
                openPiles[pileNumber] = null;
              } else {
                throw new IllegalArgumentException();
              }
            } else {
              if (valueArray[clubFoundation.size()].equals(movingCard.getValue())) {
                clubFoundation.add(movingCard);
                openPiles[pileNumber] = null;
              } else {
                throw new IllegalArgumentException();
              }
            }
            break;
          default:
            throw new IllegalArgumentException();
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

    StringBuffer gameState = new StringBuffer();
    gameState.append("F1:");
    if (!spadeFoundation.isEmpty()) {
      gameState.append(" ");
      for (Card x : spadeFoundation) {
        gameState.append(x + ", ");
      }

    }
    gameState.append("\n");
    gameState.append("F2:");
    for (Card x : clubFoundation) {
      gameState.append(" " + x);
    }
    gameState.append("\n");
    gameState.append("F3:");
    for (Card x : heartFoundation) {
      gameState.append(" " + x);
    }
    gameState.append("\n");
    gameState.append("F4:");
    for (Card x : diamondFoundation) {
      gameState.append(" " + x);
    }
    gameState.append("\n");

    for (int k = 0; k < openPiles.length; k++) {
      if (openPiles[k] == null) {
        gameState.append("O" + (k + 1) + ":\n");
      } else {
        gameState.append("O" + (k + 1) + ": " + openPiles[k] + "\n");
      }
    }

    for (int j = 0; j + 1 < cascadePiles.size(); j++) {
      gameState.append("C" + (j + 1) + ": ");
      for (int k = 0; k < cascadePiles.get(j).size(); k++) {
        if (k + 1 != cascadePiles.get(j).size()) {
          gameState.append(cascadePiles.get(j).get(k));
          gameState.append(", ");
        } else {
          gameState.append(cascadePiles.get(j).get(k));
        }
      }
      gameState.append("\n");
    }
    gameState.append("C" + (cascadePiles.size()) + ": ");
    for (int k = 0; k < cascadePiles.get(cascadePiles.size() - 1).size(); k++) {
      if (k + 1 != cascadePiles.get(cascadePiles.size() - 1).size()) {
        gameState.append(cascadePiles.get(cascadePiles.size() - 1).get(k));
        gameState.append(", ");
      } else {
        gameState.append(cascadePiles.get(cascadePiles.size() - 1).get(k));
      }
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
          if (cardValue.equals(valueArray[j])) {
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
          if (cardValue.equals(valueArray[j])) {
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


  private ArrayList<LinkedList> createCascadePiles(List<Card> deck, int numOfPiles) {
    ArrayList<LinkedList> cascadePiles = new ArrayList<>(numOfPiles);
    for (int i = 0; i < numOfPiles; i++) {
      cascadePiles.add(new LinkedList<Card>());
    }

    for (int j = 0; j < deck.size(); j++) {
      cascadePiles.get(j % numOfPiles).add(deck.get(j));
    }

    return cascadePiles;
  }


  protected boolean cascadeCheck(Card cardBase, Card cardMove) {
    int cardValueIndex = 0;
    if (((cardBase.getSuit() == heart || cardBase.getSuit() == diamond)
            && (cardMove.getSuit() == spade || cardMove.getSuit() == club))) {
      while (cardBase.getValue() != valueArray[cardValueIndex]) {
        cardValueIndex++;
      }
      if (cardMove.getValue() != valueArray[cardValueIndex - 1]) {
        throw new IllegalArgumentException();
      }
    } else if (((cardBase.getSuit() == spade || cardBase.getSuit() == club)
            && (cardMove.getSuit() == heart || cardMove.getSuit() == diamond))) {
      while (cardBase.getValue() != valueArray[cardValueIndex]) {
        cardValueIndex++;
      }
      if (cardMove.getValue() != valueArray[cardValueIndex - 1]) {
        throw new IllegalArgumentException();

      }
    } else {
      throw new IllegalArgumentException();
    }
    return true;
  }
}
