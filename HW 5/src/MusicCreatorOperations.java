/**
 * Created by Gus on 6/5/2017.
 */
public interface MusicCreatorOperations {

  public void startCreator();

  public void addBeat(String pitch, int octave, int startTime, int duration);

  //public void deleteBeat(Note selectedNote, int timeOfBeat );
  public String showSheetMusic();

}
