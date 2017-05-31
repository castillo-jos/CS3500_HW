package cs3500.hw02;

import java.util.List;

import cs3500.hw02.FreecellModel;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class HW02TypeChecks {

  public static void main(String[] args) {
    helper(new cs3500.hw02.FreecellModel());
  }

  private static <T> void helper(FreecellModel model) {
    List<T> deck = model.getDeck();
    model.startGame(deck, 8, 4, false);
    System.out.println(model.getGameState());
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0 );
    System.out.println(model.getGameState());
  }
}