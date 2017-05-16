/**
 * Created by Gus on 5/16/2017.
 */
public class Dependent extends Person {

  private Employee dependentOf;

  public Dependent(Employee dependentOf, String firstName, String lastName) {
    super(firstName, lastName);
    this.dependentOf = dependentOf;
  }

  public Employee getDependentOf() {
    return dependentOf;
  }
}
