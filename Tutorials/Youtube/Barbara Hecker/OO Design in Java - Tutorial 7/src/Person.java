/**
 * Created by Gus on 5/16/2017.
 */
public abstract class Person {
  public static int MaxID;
  public int ID;
  private String Name = "Unknow";

  public abstract  String toString();

  public Person()
  {
    MaxID++;
    setID(MaxID);
  }

  public Person(String Name){
    setName(Name);
    MaxID++;
    setID(MaxID);
  }

  void setName(String Name){
    this.Name = Name;
  }

  String getName(){
    return Name;
  }

  public int getID(){
    return ID;
  }

  public void setID(int ID){
    this.ID = ID;
  }

  public  static int getMaxID(){
    return MaxID;
  }
}
