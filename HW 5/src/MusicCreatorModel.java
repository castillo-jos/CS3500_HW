import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gus on 6/5/2017.
 */

/**
 * A class than implements MusicCreatorOperations in order to store, create, remove, and
 * manipulate Note Objects.
 */
public class MusicCreatorModel implements MusicCreatorOperations {
  //A list for storing list of Note objects
  private List<Note> listOfNotes = new ArrayList<Note>();


  @Override
  public void startCreator(Note firstNote, Note secondNote) {
    addNote(firstNote);
    addNote(secondNote);
  }

  @Override
  public void addNote(Note newNote) throws IllegalArgumentException {
    //adds new Note if list is empty
    if (listOfNotes.isEmpty()) {
      listOfNotes.add(newNote);
      //creates a group of Notes to fill in space if new Note is higher than highest note stored
    } else if (newNote.getNoteID()
            > listOfNotes.get(listOfNotes.size() - 1).getNoteID()) {
      Note lastNote = listOfNotes.get(listOfNotes.size() - 1);
      int notesToAdd = newNote.getNoteID() - lastNote.getNoteID();
      for (int i = 1; i <= notesToAdd; i++) {
        listOfNotes.add(new Note(lastNote.getNoteID() + i));
      }
      //creates a group of Notes to fill in space if new Note is lower than lowest note stored
    } else if (newNote.getNoteID()
            < listOfNotes.get(0).getNoteID()) {
      Note firstNote = listOfNotes.get(0);
      int notesToAdd = firstNote.getNoteID() - newNote.getNoteID();
      for (int i = 0; i < notesToAdd; i++) {
        listOfNotes.add(i, new Note(newNote.getNoteID() + i));
      }
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void removeFirstNote() throws IllegalArgumentException {
    if (listOfNotes.isEmpty()) {
      throw new IllegalArgumentException();
    }
    listOfNotes.remove(0);
  }

  @Override
  public void removeLastNote() throws IllegalArgumentException {
    if (listOfNotes.isEmpty()) {
      throw new IllegalArgumentException();
    }
    listOfNotes.remove(listOfNotes.size() - 1);
  }

  @Override
  public void addBeat(Note selectedNote, int startTime, int duration)
          throws IllegalArgumentException {
    boolean beatAdded = false;
    for (Note eachNote : listOfNotes) {
      if (selectedNote.toString().equals(eachNote.toString())) {
        eachNote.addBeat(startTime, duration);
        beatAdded = true;
      }
    }

    if (!beatAdded) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void removeBeat(Note selectedNote, int startTime)
          throws IllegalArgumentException {
    boolean beatRemoved = false;
    for (Note eachNote : listOfNotes) {
      if (selectedNote.toString().equals(eachNote.toString())) {
        eachNote.removeBeat(startTime);
        beatRemoved = true;
      }
    }

    if (!beatRemoved) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String showSheetMusic() {
    StringBuilder sheetMusic = new StringBuilder();
    sheetMusic.append("  ");
    for (Note eachNote : listOfNotes) {
      sheetMusic.append(eachNote.toString());
    }
    sheetMusic.append("\n");
    int longestNote = 0;
    for (Note eachNote : listOfNotes) {
      if (eachNote.listOfBeats.size() > longestNote) {
        longestNote = eachNote.listOfBeats.size();
      }
    }

    for (Note eachNote : listOfNotes) {
      if (eachNote.listOfBeats.size() != longestNote) {
        for (int i = eachNote.listOfBeats.size(); i < longestNote; i++) {
          eachNote.listOfBeats.add(String.format("%5s", ""));
        }
      }
    }
    for (int i = 0; i < longestNote; i++) {
      sheetMusic.append(String.format("%2s", i));
      for (Note eachNote : listOfNotes) {
        sheetMusic.append(eachNote.listOfBeats.get(i));
      }
      sheetMusic.append("\n");
    }
    sheetMusic.delete(sheetMusic.length() - 1, sheetMusic.length());
    return sheetMusic.toString();
  }
}
