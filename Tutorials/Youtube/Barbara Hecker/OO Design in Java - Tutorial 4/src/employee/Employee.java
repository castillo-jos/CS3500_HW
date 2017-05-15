package employee;

/**
 * Created by Gus on 5/14/2017.
 */
public class Employee {
  String name;
  Double salary;

  //Default Constructor

  Employee() {
    name = "Joe Smith";
    salary = 15.75;
  }

  //Accessor Method to get employee name
  String getName() {
    return name;
  }

  String getSalary() {
    return salary.toString();
  }

  //The main driver method to demo the class
  public static void main(String[] args) {
    Employee myEmployee = new Employee();

    String retrievedName = myEmployee.getName();
    System.out.println(retrievedName);

    String retrievedSalary = myEmployee.getSalary();
    System.out.println(retrievedSalary);
  }
}
