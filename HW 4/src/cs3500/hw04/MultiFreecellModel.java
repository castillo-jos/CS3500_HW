package cs3500.hw04;


import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;

/**
 * Created by Gus on 5/30/2017.
 */
public class MultiFreecellModel extends FreecellModel {
  @Override
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
        Card cardBase = cascadePiles.get(destPileNumber)
                .get(cascadePiles.get(destPileNumber).size() - 1);
        Card cardMoving = openPiles[pileNumber];
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
              if (valueArray[heartFoundation.size()] == movingCard.getSuit()) {
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
              if (valueArray[clubFoundation.size()] == movingCard.getValue()) {
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
              if (valueArray[heartFoundation.size()] == movingCard.getValue()) {
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
              if (valueArray[spadeFoundation.size()] == movingCard.getValue()) {
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
              if (valueArray[diamondFoundation.size()] == movingCard.getValue()) {
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
              if (valueArray[clubFoundation.size()] == movingCard.getValue()) {
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
}

