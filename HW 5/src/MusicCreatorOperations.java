/**
 * Created by Gus on 6/5/2017.
 */
public interface MusicCreatorOperations {
  public void startCreator();
  public void addNote(String pitch, int octave);
  public void addBeat(Note selectedNote, int startTime, int duration);
  public void deleteBeat(Note selectedNote, int timeOfBeat );
  public void showSheetMusic();
}
