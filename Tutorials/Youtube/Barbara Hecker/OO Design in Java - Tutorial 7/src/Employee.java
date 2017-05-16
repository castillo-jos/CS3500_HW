/**
 * Created by Gus on 5/16/2017.
 */
public class Employee extends Person {
  private  double salary;

  public Employee(){
    salary = 0.0;
  }

  public  Employee(String employeeName){
    super(employeeName);
    salary = 0.0;
    System.out.println("Employee " + employeeName);
  }

  public Employee(String employeeName, double salary)
  {
    super(employeeName);
    setSalary(salary);
    System.out.println("Employee " + employeeName + "was created with ID " + ID + " and salary " + salary);
  }

  public void setSalary(double salary){
    this.salary = salary;
  }

  public double getSalary(){
    return salary;
  }

  public String toString()
  {
    return (getName() + " is an employee with salary of " + getSalary());
  }
}
