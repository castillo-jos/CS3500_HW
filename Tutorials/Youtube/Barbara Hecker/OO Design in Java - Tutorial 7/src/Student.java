/**
 * Created by Gus on 5/17/2017.
 */
public class Student extends Person {
  private String teacherName;
  private int teacherID;

  public Student() {
  }

  public Student(String studentName) {
    super(studentName);
    this.teacherName = "Unknown";
    this.teacherID = 0;
    System.out.println("Student " + studentName + " created with teacher " + teacherName);
  }

  public Student(String studentName, String teacherName, Person[] person_array) {
    super(studentName);
    this.teacherName = teacherName;
    this.teacherID = 0;

    for (int i = 0; i < Person.getMaxID(); i++) {
      if (person_array[i] instanceof Instructor) {
        if (person_array[i].getName() == this.teacherName) {
          this.teacherID = person_array[i].getID();
        }
      }
    }
  }

  public String toString(){
    return getName() + " created with teacher " + teacherName + " and ID " + teacherID;
  }

  public String getTeacherName() {
    return this.teacherName;
  }
}
