/**
 * Created by Gus on 5/16/2017.
 */
public class Employee extends Person {
  private static int nextId = 1;
  private int id = nextId++;
  private int dept;
  private double payRate;
  private Dependent[] dependents = new Dependent[5];
  private int numDependents = 0;

  public Employee() {
  }

  public Employee(String firstName, String lastName) {
    super(firstName, lastName);
  }

  public Employee(String firstName, String lastName, int dept) {
    super(firstName, lastName);
    setDept(dept);
  }

  public Employee(String firstName, String lastName, double payRate) {
    super(firstName, lastName);
    setPayRate(payRate);
  }

  public Employee(String firstName, String lastName, int dept, double payRate){
    super(firstName, lastName);
    setDept(dept);
    setPayRate(payRate);
  }

  public int getId() {
    return id;
  }

  public int getDept() {
    return dept;
  }

  public double getPayInfo(){
    return payRate;
  }

  public void setDept(int dept) {
    this.dept = dept;
  }

  public void setPayRate(double payRate) {
    this.payRate = payRate;
  }

  public void addDependent(String fName, String lName) {
    if (numDependents < dependents.length) {
      dependents[numDependents++] = new Dependent(this, fName, lName);
    }
  }

  public String listDependents() {
    if (dependents == null) return "";
    StringBuffer temp = new StringBuffer();
    String newline = System.getProperty("line.separator");
    if (newline == null) newline = "\n";

    for (int i = 0; i < numDependents; i++) {
      temp.append(dependents[i].getFirstName());
      temp.append(" ");
      temp.append(dependents[i].getLastName());
      temp.append(newline);
    }
    return temp.toString();
  }


}
