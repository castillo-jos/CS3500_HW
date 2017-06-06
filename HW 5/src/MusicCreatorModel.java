import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gus on 6/5/2017.
 */
public class MusicCreatorModel implements MusicCreatorOperations {
  List<Note> listOfNotes = new ArrayList<Note>();
  List<Integer> lengthOfMusic = new ArrayList<Integer>();

  @Override
  public void startCreator() {
    lengthOfMusic.add(0);
  }

  @Override
  public void addNote(String pitch, int octave) {
    listOfNotes.add(new Note(pitch, octave));
  }

  @Override
  public void addBeat(Note selectedNote, int startTime, int duration) {
    boolean hasNoteBeenCreated = false;
    for(Note createdNote: listOfNotes){
      if(createdNote.toString().equals(selectedNote.toString()))
    }
  }

  @Override
  public void deleteBeat(Note selectedNote, int timeOfBeat) {

  }

  @Override
  public void showSheetMusic() {

  }
}
