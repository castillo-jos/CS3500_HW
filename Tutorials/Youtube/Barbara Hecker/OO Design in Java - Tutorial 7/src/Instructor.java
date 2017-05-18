/**
 * Created by Gus on 5/17/2017.
 */
public class Instructor extends Employee {
  private int[] studentArray = new int[10];

  public Instructor(String instructorName) {
    super(instructorName);
    setID(ID);

    for (int i = 0; i < 10; i++) {
      studentArray[i] = 0;
    }
    System.out.println("Instructor "+ instructorName + "created with ID" + ID);
  }

  public Instructor(String instructorName, double salary){
    super(instructorName, salary);
    setID(ID);

    for(int i = 0; i < studentArray.length; i++){
      studentArray[i] = 0;
    }
    System.out.println("Instructor " + instructorName + " created " + "with salary " + salary);
  }
}
