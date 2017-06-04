package cs3500.hw03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;

/**
 * Created by Gus on 5/26/2017.
 */

public class FreecellController implements IFreecellController {
  Readable rd;
  Appendable ap;
  String command;
  List<String> singleOrder = new ArrayList();
  PileType sourcePile;
  int pileNumber;
  int cardIndex;
  PileType destPile;
  int destPileNumber;

  /**
   * Constructor for the FrecellController class. This constructor
   * makes sure that no null parameters are being passed to it.
   *
   * @param rd Readable object.
   * @param ap Appendable object.
   */
  public FreecellController(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (ap == null || rd == null) {
      throw new IllegalStateException();
    }
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame(List deck, FreecellOperations model, int numCascades,
                       int numOpens, boolean shuffle) throws IllegalArgumentException {
    if (model == null || deck == null) {
      throw new IllegalArgumentException();
    }
    if (4 > numCascades || 1 > numOpens) {
      try {
        ap.append("Could not start game.");
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.exit(0);
    }
    model.startGame(deck, numCascades, numOpens, shuffle);
    Scanner askResponse = new Scanner(rd);

    while (askResponse.hasNext()) {
      while (singleOrder.size() < 3) {
        command = askResponse.next();
        for (char quit : command.toCharArray()) {
          if (quit == 'q' || quit == 'Q') {
            try {
              ap.append("Game quit prematurely.");
              throw new IllegalArgumentException();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
        singleOrder.add(command);
      }

      switch (singleOrder.get(0).toCharArray()[0]) {
        case 'C':
          sourcePile = PileType.CASCADE;
          break;
        case 'O':
          sourcePile = PileType.OPEN;
          break;
        default:
          throw new IllegalArgumentException();
      }

      StringBuffer tempNum = new StringBuffer();

      for (int i = 1; i < singleOrder.get(0).length(); i++) {
        tempNum.append(singleOrder.get(0).charAt(i));
      }

      pileNumber = Integer.parseInt(tempNum.toString());
      tempNum.delete(0, tempNum.length());

      for (char givenNum : singleOrder.get(1).toCharArray()) {
        if (!Character.isDigit(givenNum)) {
          throw new IllegalArgumentException();
        }
      }

      cardIndex = Integer.parseInt(singleOrder.get(1).toString());

      switch (singleOrder.get(2).toCharArray()[0]) {
        case 'C':
          destPile = PileType.CASCADE;
          break;
        case 'O':
          destPile = PileType.OPEN;
          break;
        default:
          throw new IllegalArgumentException();
      }

      for (int i = 1; i < singleOrder.get(2).length(); i++) {
        tempNum.append(singleOrder.get(2).charAt(i));
      }

      destPileNumber = Integer.parseInt(tempNum.toString());
      pileNumber = pileNumber - 1;
      destPileNumber = destPileNumber - 1;
      model.move(sourcePile, pileNumber, cardIndex, destPile, destPileNumber);
      singleOrder.clear();
      try {
        ap.append(model.getGameState());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}


