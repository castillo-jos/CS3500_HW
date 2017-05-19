package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;

/**
 * Created by Gus on 5/17/2017.
 */
public class Tester {
  public static void main(String arg[]){
    FreecellModel currentGame = new FreecellModel();
    List deck = currentGame.getDeck();

    currentGame.startGame(deck, 4, 0, true);
    System.out.print(currentGame.getGameState());
  }
}
