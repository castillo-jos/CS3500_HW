package cs3500.hw03;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Scanner;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;

/**
 * Created by Gus on 5/26/2017.
 */

public class FreecellController implements IFreecellController {
  PlayerInput rd;
  GameOutput ap;
  FreecellModel model;
  List<Card> deck;
  PileType sourcePile;
  int pileNumber;
  int cardIndex;
  PileType destination;
  int destPileNumber;

  public FreecellController(Readable rd, Appendable ap) throws IllegalArgumentException {
    this.rd = (PlayerInput) rd;
    this.ap = (GameOutput) ap;
  }

  @Override
  public void playGame(List deck, FreecellOperations model, int numCascades,
                       int numOpens, boolean shuffle) throws IllegalArgumentException {
    model.startGame(deck, numCascades, numOpens, shuffle);
    while (!model.isGameOver()) {
      Scanner pInput = new Scanner(System.in);
      String nextMove = new String(pInput.nextLine());
      String[] splited = nextMove.split("\\s+");

      System.out.println(splited[1]);
      CharBuffer cb = CharBuffer.allocate(splited[0].length());
      PlayerInput reader = new PlayerInput(splited[0]);
      reader.read(cb);
      cb.flip();

      if (cb.get(0) == 'q' || cb.get(0) == 'Q') {
        throw new IllegalArgumentException();
      }

      CharBuffer cb2 = CharBuffer.allocate(splited[1].length());
      PlayerInput reader2 = new PlayerInput(splited[1]);
      reader2.read(cb2);
      cb2.flip();

      if (cb.get(0) == 'C') {
        sourcePile = PileType.CASCADE;
      } else if (cb.get(0) == 'O') {
        sourcePile = PileType.OPEN;
      } else {
        throw new IllegalArgumentException();
      }

      pileNumber = (int) (Character.getNumericValue(cb.get(1)) - 1);
      System.out.println(pileNumber);
      cardIndex = 0;

      if (cb2.get(0) == 'C') {
        destination = PileType.CASCADE;
      } else if (cb2.get(0) == 'O') {
        destination = PileType.OPEN;
      } else if (cb2.get(0) == 'F') {
        destination = PileType.FOUNDATION;
      } else {
        throw new IllegalAccessError();
      }

      destPileNumber = (Character.getNumericValue(cb2.get(1)) - 1);
      System.out.println(destPileNumber);
      model.move(sourcePile, pileNumber, cardIndex, destination, destPileNumber);
      ap.append(model.getGameState());
      System.out.print(ap.toString());
    }
  }
}
