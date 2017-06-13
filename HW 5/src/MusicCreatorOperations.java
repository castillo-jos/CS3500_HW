/**
 * Created by Gus on 6/5/2017.
 */

/**
 * An interface containing methods that deal with either adding, removing, or manipulating Note
 * objects.
 */
public interface MusicCreatorOperations {
  /**
   * Creates the initial set of note in between, and including, the two Note objects passed to it,
   * regardless of whichever note is of higher octave or pitch.
   *
   * @param firstNote  One of two endpoints in the new set of Note objects.
   * @param secondNote Two of two endpoints in the new set of Note objects. Can be either higher or
   *                   lower than firstNote.
   */
  void startCreator(Note firstNote, Note secondNote);

  /**
   * Adds Note object to a provided list of Notes. Also creates additional
   * Note objects in between passed object and closest created Note in terms
   * of pitch and octave, if needed.
   *
   * @param newNote Note to be added to list.
   */
  void addNote(Note newNote);

  /**
   * Removes first note from any collection of notes available.
   */
  void removeFirstNote();

  /**
   * Removes last note from any collection of notes available.
   */
  void removeLastNote();

  /**
   * Adds a beat to Note object passed to it at startTime for as long as duration designates.
   * Keep in mind that the startTime is included in duration.
   *
   * @param selectedNote Desired Note destination of beat.
   * @param startTime    Start time of beat.
   * @param duration     Duration of beat (startTime included).
   */
  void addBeat(Note selectedNote, int startTime, int duration);

  /**
   * Removes a beat at selected Note object. Only the startTime of the beat must be
   * passed; the complete beat in its entire duration will be removed.
   *
   * @param selectedNote Note of beat being removed.
   * @param startTime    startTime of being removed.
   */
  void removeBeat(Note selectedNote, int startTime);

  /**
   * Appends the beats of all Note objects within the class, as well as the Note values themselves,
   * and accompanying beat values.
   *
   * @return A string of all beats and notes added to the class, in crude, musical format.
   */
  String showSheetMusic();

}
