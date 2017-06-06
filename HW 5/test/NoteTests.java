import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Gus on 6/4/2017.
 */
public class NoteTests {
  /**
   * A test to make sure the class constructs properly,
   * and the toString function also returns the object's
   * pitch and octave.
   */
  @Test
  public void ValidNoteTestOne() {
    Note testNote = new Note("C", 4);
    assertEquals("C4", testNote.toString());
  }

  /**
   * A test to make sure the class constructs properly,
   * and the toString function also returns the object's
   * pitch and octave. In this test, a '#' character is
   * passed in the pitch, as well as a letter note.
   */
  @Test
  public void ValidNoteTestTwo() {
    Note testNote = new Note("C#", 4);
    assertEquals("C#4", testNote.toString());
  }

  /**
   * A test to make sure that an IllegalArgumentException
   * is thrown when a pitch that is more than two characters
   * long is passed to it.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidPitchLengthTestOne() {
    Note testNote = new Note("Hello", 4);
  }

  /**
   * A test to make sure that an IllegalArgumentException
   * is thrown when a pitch that is less than one.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidPitchLengthTestTwo() {
    Note testnote = new Note("", 4);
  }

  /**
   * A test to make sure that an illegalArgumentException is thrown
   * when a pitch is passed to the Note class that isn't an
   * Alphabetic letter.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidPitchCharacterTestOne() {
    Note testNote = new Note("4", 4);
  }

  /**
   * A test that makes sure that if the String passed has a
   * length of two, that the second character is nothing other
   * than '#'.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidPitchCharacterTestTwo() {
    Note testNote = new Note("C%", 4);
  }

  /**
   * A test to make sure that the String character being passed
   * is a valid note letter.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidPitchCharacterTestThree() {
    Note testNote = new Note("T", 4);
  }

  /**
   * A Test to make sure that the octave given to the Note class is greater
   * than zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidOctaveTest() {
    Note testNote = new Note("C", 0);
  }
}

