/**
 * Created by Gus on 5/17/2017.
 */
public class Main_Program {
  public static void main(String[] args){
    final int MAX_HEADCOUNT = 20;
    Person[] person_array = new Person[MAX_HEADCOUNT];

    //A student named Peter
    person_array[0] = new Student("Peter");

    //An instructor name Peter
    person_array[1] = new Instructor("Sandy", 25000);

    //An instructor named Sandy and her salary
    person_array[2] = new Instructor("Peter");

    //A janitor named Bob
    person_array[3] = new Employee("Janitor Bob");
  }
}
