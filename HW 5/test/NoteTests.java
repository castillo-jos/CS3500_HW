import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Gus on 6/4/2017.
 */
public class NoteTests {
  @Test
  public void ValidNoteTest() {
    Note testNote = new Note("C", 4);
    assertEquals("C4", testNote.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvaildPitchSizeTest() {
    Note testNote = new Note("C#T", 4);
  }
}
