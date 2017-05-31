package cs3500.hw04;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;

/**
 * Created by Gus on 5/30/2017.
 */
public class FreecellModelCreator {
  public enum GameType {
    SINGLEMOVE, MULTIMOVE
  }

  private FreecellModelCreator() {

  }

  public static final FreecellOperations create(GameType type) {
    if (type == GameType.SINGLEMOVE) {
      return new FreecellModel();
    }
    else{
      return (FreecellOperations) new MultiFreecellModel();
    }
  }
}
