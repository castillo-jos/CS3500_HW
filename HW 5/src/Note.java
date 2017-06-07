import com.sun.javafx.iio.png.PNGImageLoader2;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gus on 6/4/2017.
 */
public class Note {
  private String pitch;
  private int octave;
  public List<String> listOfBeats = new ArrayList<String>();
  private PitchNames pitchName;

  public Note(String pitch, int octave) throws IllegalArgumentException {
    if (pitch.length() < 1 || pitch.length() > 3) {
      throw new IllegalArgumentException();
    }

    /**
     * The following if statements are in regards to the pitch passed into
     * constructor. The required pitch string would include a letter in the
     * first 8 letters of the alphabet, as well as possibly a '#'.
     */
    /**
     * This makes sure that the first char in the pitch string is in fact
     * alphabetic.
     */
    if (!(Character.isAlphabetic(pitch.charAt(0)))) {
      throw new IllegalArgumentException();
    }
    /**
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

    /**
     * This check only occurs if the pitch string is two characters long.
     * If it is, then the second char must be equal to #.
     */
    if (pitch.length() == 2) {
      if (pitch.charAt(1) != '♯') {
        throw new IllegalArgumentException();
      }
    }

    /**
     * This checks to see if the octave passed is at least greater or equal to one.
     **/
    if (octave < 1) {
      throw new IllegalArgumentException();
    }

    setPitch(pitch);
    setOctave(octave);
    setPitchName(pitch);
  }

  private void setPitch(String pitch) {
    this.pitch = pitch;
  }

  private void setOctave(int octave) {
    this.octave = octave;
  }

  public String getPitch() {
    return pitch;
  }

  public int getOctave() {
    return octave;
  }

  public void addBeat(int startTime, int duration) {
    System.out.println("how are you");
    if(listOfBeats.isEmpty()){
      listOfBeats.add("     ");
      System.out.println("hehe");
    }
    while (startTime >= listOfBeats.size()) {
      listOfBeats.add("     ");
      System.out.println(listOfBeats.size());
    }

    listOfBeats.add(startTime, "  X  ");
    for (int i = startTime + 1; i < startTime + duration; i++) {
      listOfBeats.add(i, "  |  ");
    }
  }

  public String toString() {
    return "  " + pitch + octave + "  ";
  }

  public void setPitchName(String pitch) {
    for (PitchNames eachPitch : PitchNames.values()) {
      if (eachPitch.toString().equals(pitch)) {
        pitchName = eachPitch;
      }
    }
  }

  public PitchNames getPitchName() {
    return pitchName;
  }
}
