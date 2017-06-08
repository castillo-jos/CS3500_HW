import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gus on 6/5/2017.
 */
public class MusicCreatorModel implements MusicCreatorOperations {
  List<Note> listOfNotes = new ArrayList<Note>();
  List<Integer> lengthOfMusic = new ArrayList<Integer>();
  String[] pitchNames = {"C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "A♯", "B"};

  @Override
  public void startCreator() {
    lengthOfMusic.add(0);
  }

  private void addNote(String pitch, int octave) {
    listOfNotes.add(new Note(pitch, octave));
  }

  @Override
  public void addBeat(String pitch, int octave, int startTime, int duration) {
    if (octave < 1) {
      throw new IllegalArgumentException();
    }

    boolean isNoteCreated = false;
    Note tempNote = new Note(pitch, octave);


    for (Note eachNote : listOfNotes) {
      if (eachNote.toString().equals("  " + pitch + octave + "  ")) {
        isNoteCreated = true;
        eachNote.addBeat(startTime, duration);
      }
    }

    if (!isNoteCreated) {
      if (listOfNotes.isEmpty()) {
        addNote(pitch, octave);
        listOfNotes.get(0).addBeat(startTime, duration);
      } else if (octave >= listOfNotes.get(listOfNotes.size() - 1).getOctave()) {
        Note lastNote = listOfNotes.get(listOfNotes.size() - 1);
        int baseOctave = lastNote.getOctave();
        int octavesToAdd = octave - lastNote.getOctave();
        int notesToAdd = octavesToAdd * 12 + (tempNote.getPitchName().ordinal() - lastNote.getPitchName().ordinal() - 1);
        int addedOctave = 0;
        for (int i = notesToAdd; i >= 0; i--) {
          Note addedNote = listOfNotes.get(listOfNotes.size() - 1);
          PitchNames tempPitchName = PitchNames.values()[(addedNote.getPitchName().ordinal() + 1) % 12];
          if ((tempPitchName.ordinal() + 1) % 12 == 1) {
            addedOctave++;
          }
          addNote(tempPitchName.toString(), addedOctave + baseOctave);
        }
        listOfNotes.get(listOfNotes.size() - 1).addBeat(startTime, duration);
      } else if (octave < listOfNotes.get(listOfNotes.size() - 1).getOctave()) {
        Note firstNote = listOfNotes.get(0);
        int baseOctave = firstNote.getOctave();
        int octavesToAdd = baseOctave - octave;
        int notesToAdd = octavesToAdd * 12 + (tempNote.getPitchName().ordinal() - firstNote.getPitchName().ordinal() + 1);
        int addedOctave = 0;
        for (int i = notesToAdd; i >= 0; i--) {
          Note addedNote = listOfNotes.get(0);
          PitchNames tempPitchName = PitchNames.values()[(12 + addedNote.getPitchName().ordinal() - 1) % 12];
          if ((tempPitchName.ordinal()) % 12 == 11) {
            addedOctave++;
          }
          listOfNotes.add(0, new Note(tempPitchName.toString(), baseOctave - (addedOctave)));
        }
        listOfNotes.get(0).addBeat(startTime, duration);
      }
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
      if (!(eachNote.listOfBeats.size() == longestNote)) {
        for (int i = eachNote.listOfBeats.size(); i < longestNote; i++) {
          eachNote.listOfBeats.add(String.format("%5s",""));
        }
      }
    }
    for (int i = 0; i < longestNote; i++) {
      sheetMusic.append(String.format("%1$2s",i));
      for (Note eachNote : listOfNotes) {
        sheetMusic.append(eachNote.listOfBeats.get(i).toString());
      }
      sheetMusic.append("\n");
    }
    return sheetMusic.toString();
  }
}
