import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Gus on 6/8/2017.
 */
public class MusicCreatorModelTest {

  @Test
  public void addingAndRemovingNotesTest() {
    MusicCreatorOperations model = new MusicCreatorModel();
    MusicCreatorOperations model1 = new MusicCreatorModel();

    Note c4 = new Note("C", 4);
    Note g6 = new Note("G", 6);
    model.startCreator(c4, g6);

    model1.addNote(c4);
    model1.addNote(g6);

    assertEquals(model.showSheetMusic(), model1.showSheetMusic());

    model1.removeLastNote();
    model1.removeFirstNote();

    assertNotEquals(model.showSheetMusic(), model1.showSheetMusic());

    Note cSharp4 = new Note("C#", 4);
    Note fSharp6 = new Note("F#", 6);
    MusicCreatorOperations model2 = new MusicCreatorModel();
    model2.startCreator(cSharp4, fSharp6);

    assertEquals(model1.showSheetMusic(), model2.showSheetMusic());
  }

  @Test
  public void addingAndRemovingBeatsTest() {
    MusicCreatorModel model = new MusicCreatorModel();
    MusicCreatorModel model1 = new MusicCreatorModel();

    Note c4 = new Note("C", 4);

    model.addNote(c4);
    model1.addNote(c4);
    model.addBeat(c4, 4, 4);
    model1.addBeat(c4, 4, 4);
    assertEquals(model.showSheetMusic(), model1.showSheetMusic());

    model.removeBeat(c4, 4);

    assertEquals(model.showSheetMusic(), "    C4 ");
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidRemovingFirstNote() {
    MusicCreatorOperations model = new MusicCreatorModel();
    model.removeFirstNote();
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidRemovingLastNote() {
    MusicCreatorOperations model = new MusicCreatorModel();
    model.removeLastNote();
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidRemovingBeat() {
    Note c4 = new Note("C", 4);
    MusicCreatorOperations model = new MusicCreatorModel();
    model.removeBeat(c4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidDuplicateAddingNote() {
    Note c4 = new Note("C", 4);
    MusicCreatorOperations model = new MusicCreatorModel();
    model.addNote(c4);
    model.addNote(c4);
  }
}
