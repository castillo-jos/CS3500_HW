/**
 * Created by Gus on 6/6/2017.
 */
public class MusicCreatorTest {
  public static void main(String[] args) {
    MusicCreatorOperations testCreator = new MusicCreatorModel();
    testCreator.addBeat("C", 2, 3, 5);
    testCreator.addBeat("C♯", 2, 6, 5);
    testCreator.addBeat("D", 2, 6, 5);
    testCreator.addBeat("F", 2, 3, 5);
    testCreator.addBeat("B", 2, 6, 5);
    testCreator.addBeat("G", 2, 6, 5);
    System.out.print(testCreator.showSheetMusic());
  }
}
