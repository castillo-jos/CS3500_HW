import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gus on 6/4/2017.
 */

/**
 * A class that denotes one pitch at a certain octave.
 */
public class Note {

  //the pitch, restricted to one of the following characters: C C♯ D D♯ E F F♯ G G♯ A A♯ B
  private String pitch;

  //the octave at which the Note is at. Cannot be lower than zero.
  private int octave;

  //A list of string to denote each representation of a beat.
  List<String> listOfBeats = new ArrayList<>();

  //A storage field for the enum PitchNames.
  private PitchNames pitchName;

  /*An ID determined by the octave level and pitch of Note. Each note of same
   *octave and pitch will have the same noteID, but never the same ID of
   * any other Note object of either different octave or pitch.
   */
  private int noteID;

  /*A HashMap of two integers. These HashMaps are added to a list of beats
  * used to determined the beat string equivalent for the Note.
  */
  HashMap<Integer, Integer> beatsInNote = new HashMap<Integer, Integer>();

  /**
   * Contructor type one that takes in a String pitch and a octave integer.
   * Sets these parameters as the values for pitch and octave of the Note
   * object.
   *
   * @param pitch  The letter part of the Note.
   * @param octave The level of pitches the Note is at.
   * @throws IllegalArgumentException If pitch is less than 1 or more than 3, or if octave is less
   *                                  than zero.
   */
  public Note(String pitch, int octave) throws IllegalArgumentException {

    /*
     * If pitch is either greater than 3 or less than one,
     * it is not a proper argument. This if loop makes sure
     * than no pitch fields like that pass through the
     * constructor.
     */
    if (pitch.length() < 1 || pitch.length() > 3) {
      throw new IllegalArgumentException();
    }

    /*
     * The following if statements are in regards to the pitch passed into
     * constructor. The required pitch string would include a letter in the
     * first 8 letters of the alphabet, as well as possibly a '#'.
     */
    /*
     * This makes sure that the first char in the pitch string is in fact
     * alphabetic.
     */
    if (!(Character.isAlphabetic(pitch.charAt(0)))) {
      throw new IllegalArgumentException();
    }
    /*
     * This checks that the first character in the pitch string is valid,
     * being one of the first 8 letters of the alphabet.
     */
    String noteList = "ABCDEFG";
    boolean noteIsValid = false;
    for (char note : noteList.toCharArray()) {
      if (note == pitch.charAt(0)) {
        noteIsValid = true;
      }
    }

    if (!noteIsValid) {
      throw new IllegalArgumentException();
    }

    /*
     * This check only occurs if the pitch string is two characters long.
     * If it is, then the second char must be equal to #.
     */
    if (pitch.length() == 2) {
      if (pitch.charAt(1) != '#') {
        throw new IllegalArgumentException();
      }
    }

    /*
     * This checks to see if the octave passed is at least greater or equal to one.
     */
    if (octave < 1) {
      throw new IllegalArgumentException();
    }
    //sets object's pitch field.
    setPitch(pitch);
    //sets object's octave field.
    setOctave(octave);
    //set object's PitchName enum value.
    setPitchName(pitch);
    //noteID calculated from octave and PitchName ordinal.
    noteID = 12 * (octave - 1) + getPitchName().ordinal();
  }

  /**
   * A constructor that derives pitch, octive, and PitchName all
   * from a value given to it. Mostly used during automated creation
   * of new Note objects.
   *
   * @throws IllegalArgumentException If noteID is less than one.
   */
  public Note(int noteID) throws IllegalArgumentException {
    //checks that passed parameter is not less than zero.
    if (noteID < 0) {
      throw new IllegalArgumentException();
    }

    //derives octave and pitch from noteID.
    int octave = (noteID / 12) + 1;
    String pitch = PitchNames.values()[noteID % 12].toString();

    //Sets appropriate values.
    setPitch(pitch);
    setOctave(octave);
    setPitchName(pitch);
    this.noteID = noteID;
  }

  /**
   * Set object's pitch
   *
   * @param pitch Letter note of object.
   */
  private void setPitch(String pitch) {
    this.pitch = pitch;
  }

  /**
   * Set object's octave
   *
   * @param octave Levels of pitches Note object is at.
   */
  private void setOctave(int octave) {
    this.octave = octave;
  }

  /**
   * Adding a beat at startTime and for as long as durations asks for.
   *
   * @param startTime Time at which beat should be added.
   * @param duration  Time for which beat should be held.
   */
  public void addBeat(int startTime, int duration) {
    //adds to HashMap
    beatsInNote.put(startTime, duration);
    //reformats List of Beats to accommodate for new beat.
    formatListOfBeats();
  }

  /**
   * Removes Beat at designated start time.
   *
   * @param startTime Pinpoints time at which beat that needs to be removed starts.
   * @throws IllegalArgumentException If beat being removed does not exist.
   */
  public void removeBeat(int startTime) throws IllegalArgumentException {
    if (beatsInNote.containsKey(startTime)) {
      beatsInNote.remove(startTime);
    } else {
      throw new IllegalArgumentException();
    }

    formatListOfBeats();
  }

  /**
   * Returns a formatted string that contains the note's pitch and octave.
   * Padded to 5 characters.
   *
   * @return A string that contains pitch and octave.
   */
  public String toString() {
    String noteString = pitch + octave;
    if (noteString.length() <= 3) {
      noteString = String.format("%4s", noteString) + " ";
    } else if (noteString.length() > 3) {
      noteString = String.format("%5s", noteString);
    }
    return noteString;
  }

  /**
   * Sets enum value for PitchName of Note object.
   *
   * @param pitch The letter value of the Note.
   */
  public void setPitchName(String pitch) {
    for (PitchNames eachPitch : PitchNames.values()) {
      if (eachPitch.toString().equals(pitch)) {
        pitchName = eachPitch;
      }
    }
  }

  /**
   * Retrieves noteID for Note object;
   *
   * @return 12 * (octave - 1) + getPitchName().ordinal().
   */
  public int getNoteID() {
    return noteID;
  }

  /**
   * Returns enum value of PitchName of Note Object.
   *
   * @return PitchName
   */
  public PitchNames getPitchName() {
    return pitchName;
  }

  /**
   * Formats the list of beats for the Note Object, based on the beatsInNote HashMap.
   */
  private void formatListOfBeats() {
    //clears list
    listOfBeats.clear();

    //iterates through each Map Entry
    for (Map.Entry beat : beatsInNote.entrySet()) {
      if (listOfBeats.isEmpty()) {
        listOfBeats.add(String.format("%5s", ""));
      }

      //adds space till next beat
      if ((int) beat.getKey() + (int) beat.getValue() > listOfBeats.size()) {
        while (((int) beat.getKey() + (int) beat.getValue() - 1) >= listOfBeats.size()) {
          listOfBeats.add(String.format("%5s", ""));
        }
      }

      //adds beat
      listOfBeats.set((int) beat.getKey(), String.format("%3s", "X") + "  ");
      for (int i = (int) beat.getKey() + 1; i < (int) beat.getKey() + (int) beat.getValue(); i++) {
        listOfBeats.set(i, String.format("%3s", "|") + "  ");
      }
    }
  }
}
