import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Gus on 6/7/2017.
 */
public class ShitTesting {
  public static void main(String[] args){
    System.out.println(PitchNames.CSHARP);
    List randoList = new ArrayList();
    randoList.add(PitchNames.E);
    randoList.add(PitchNames.A);
    randoList.add(PitchNames.B);
    randoList.add(PitchNames.C);

    System.out.print(randoList);


    System.out.print(randoList);
    randoList.sort(Collections.reverseOrder());
    System.out.println(randoList);

    PitchNames pitchName = PitchNames.values()[2+1];
    System.out.println(pitchName);
    System.out.println(PitchNames.B.ordinal());
  }
}
