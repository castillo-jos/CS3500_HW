/**
 * Created by Gus on 6/6/2017.
 */
public class MusicCreatorTest {
  public static void main(String[] args){
    MusicCreatorOperations testCreator = new MusicCreatorModel();
    testCreator.addBeat("C", 4, 5, 4);
    testCreator.addBeat("G♯", 6, 3,3);
    System.out.print(testCreator.showSheetMusic());
  }
}
