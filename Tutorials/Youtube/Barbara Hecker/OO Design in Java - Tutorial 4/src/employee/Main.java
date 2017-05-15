package employee;

/**
 * Created by Gus on 5/14/2017.
 */
public class Main {
  public static void main(String[] args){
    Employee myEmployee = new Employee();
    Boss myBoss = new Boss("Fred", 20.85, "entry", 1);
    Boss myBossTwo = new Boss();

    System.out.println(myEmployee.getName());
    System.out.println(myEmployee.getSalary());

    System.out.println("first attitude = " + myBoss.getAttitude());

    myBoss.setAttitude(5);

    System.out.println("new attitude = " + myBoss.getAttitude());
    System.out.println(myBoss.getSalary());

    System.out.println(myBossTwo.getSalary());
  }
}
