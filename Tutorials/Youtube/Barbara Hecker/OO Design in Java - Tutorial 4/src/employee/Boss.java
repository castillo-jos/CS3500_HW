package employee;

/**
 * Created by Gus on 5/14/2017.
 */
public class Boss {
  String name;
  Double salary;
  String level;
  int attitude;

  public Boss(){
    name = "Mr.Boss";
    salary = 20.75;
    level = "Entry";
    attitude = 0;
  }

  public Boss(String n, Double s, String l, int a){
    name = n;
    salary = s;
    level = l;
    attitude = a;
  }

  public Double getSalary(){
    return  salary;
  }

  public  int getAttitude(){
    return attitude;
  }

  public  void setAttitude(int a){
    attitude = a;
  }
}
