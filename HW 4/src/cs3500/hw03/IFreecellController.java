package cs3500.hw03;

import java.util.List;

import cs3500.hw02.FreecellOperations;

/**
 * Created by Gus on 5/26/2017.
 */
public interface IFreecellController<K> {


  void playGame(List<K> deck, FreecellOperations<K> model,
                int numCascades, int numOpens, boolean shuffle) throws IllegalArgumentException;
}
