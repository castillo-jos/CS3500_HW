import java.io.*;

/**
 * Created by Gus on 5/16/2017.
 */
public class Payroll {
  public static void main(String[] args) throws Exception {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);

    String fName = null;
    String lName = null;
    int dept = 0;
    int payRate = 0;
    double hours = 0.0;
    int numDeps = 0;
    String dfName = null;
    String dlName = null;

    Employee e = null;

    System.out.println("Enter first name: ");
    fName = br.readLine();

    System.out.println("Enter last name: ");
    lName = br.readLine();

    System.out.println("Enter department: ");
    dept = Integer.parseInt(br.readLine());

    do {
      System.out.println("Enter pay rate: ");
      payRate = Integer.parseInt(br.readLine());
      if (payRate < 0.0) System.out.println("Pay rate must be greater than zero!");
    } while (payRate < 0.0);

    e = new Employee(fName, lName, dept, payRate);

    System.out.println("How many dependents?: ");
    numDeps = Integer.parseInt(br.readLine());

    for (int d = 0; d < numDeps; d++) {

      System.out.println("Enter dependent first name: ");
      dfName = br.readLine();

      System.out.println("Enter dependent last name: ");
      dlName = br.readLine();

      e.addDependent(dfName, dlName);
    }
    System.out.println(e.getPayInfo());
    System.out.println(e.listDependents());
  }
}
