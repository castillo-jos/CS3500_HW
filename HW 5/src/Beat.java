/**
 * Created by Gus on 6/5/2017.
 */
public class Beat {
  int duration = 0;

  public Beat(int duration) {
    if (duration < 1) {
      throw new IllegalArgumentException();
    }
    this.duration = duration;
  }

}
