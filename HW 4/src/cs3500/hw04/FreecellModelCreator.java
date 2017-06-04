package cs3500.hw04;

import cs3500.hw02.FreecellModel;

/**
 * Created by Gus on 5/30/2017.
 */
public class FreecellModelCreator {
  public enum GameType {
    SINGLEMOVE, MULTIMOVE
  }

  private FreecellModelCreator() {

  }

  /**
   * A function that returns either the multimove or single move game
   * type, depending on parameter given.
   *
   * @param type Type of game given to the creator.
   * @return Freecellmodel
   */
  public static final FreecellModel create(GameType type) {
    if (type == GameType.SINGLEMOVE) {
      return new FreecellModel();
    } else {
      return new MultiFreecellModel();
    }
  }
}
