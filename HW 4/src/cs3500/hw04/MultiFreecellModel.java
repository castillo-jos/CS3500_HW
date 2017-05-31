package cs3500.hw04;

import java.util.List;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;

/**
 * Created by Gus on 5/30/2017.
 */
public class MultiFreecellModel extends FreecellModel {
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination, int destPileNumber) throws IllegalArgumentException {
    if(source.equals(PileType.FOUNDATION)){
      throw new IllegalArgumentException();
    }
    else if(source.equals(PileType.CASCADE)){
      if(cardIndex  == 0){
        super.move(source, pileNumber, cardIndex, destination, destPileNumber);
      }
      else{
        do{
          super.move(source, pileNumber, 0, destination, destPileNumber);
          cardIndex--;
        }while((cardIndex != 0));
      }
    }
  }
}

