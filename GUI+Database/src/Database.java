import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Author: Khalid B Said
 * Description of the project:
 *
 * Just a little project that I personally been working on in the Summer. I created a Database and
 * a Graphical User Interface that are connected to each other . Which means that you are sending
 * SQL Query through the GUI to the Database and later on displaying that information on your
 * Application.
 *
 */

public class Database extends GUI {
    private ArrayList<String> infolist = new ArrayList<>();
    private Connection mycon;
    private Statement mystmt;
    private ResultSet myrs;

    public void ConnectToDB(){
        try{
             mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo",
                    "root", "1234");
             mystmt = mycon.createStatement();


        }catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }


    }

    public void EndConnectionDB(){

        try{
            mycon.close();
            mystmt.close();

        }catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    /**
     * This method runs a SQL-Query where the methods intent is to search up
     * specific information to the Database. It will also give a confirmation
     * whether the search was successfully
     */

    public void searchQuery(int id, String fname, String lname) {


        try {
             ConnectToDB();
             myrs = mystmt.executeQuery("SELECT * FROM employees where first_name = "
                  + fname + " and " + "last_name = " + lname + " and " + " id = " + id);

            while (myrs.next()) {
                String empID = myrs.getString("ID");
                String name = myrs.getString("first_name");
                String surname = myrs.getString("last_name");
                String email = myrs.getString("email");
                String department = myrs.getString("department");
                String salary = myrs.getString("salary");

                infolist.add(empID);
                infolist.add(name);
                infolist.add(surname);
                infolist.add(email);
                infolist.add(department);
                infolist.add(salary);
            }

            PrintDatabase(infolist);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Something went wrong. Can't the information you are looking for in the Database.");
            System.out.println("ERROR: " + ex.getMessage());
        }
        finally {
            EndConnectionDB();
        }
    }

    /**
     * This method runs a Query that is being sent from the Delete Button on the GUI.
     * It's intent is to try and locate the person you want to delete from the database,
     * and then deleting that person. You will also be getting a confirmation whether
     * the process was successful.
     */

    public void deleteQuery(int id, String fname, String lname){

        try {

            ConnectToDB();
             myrs = mystmt.executeQuery("SELECT * FROM employees where first_name = " +

            Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo",
                    "", "");

            Statement mystmt = mycon.createStatement();


            ResultSet myrs = mystmt.executeQuery("SELECT * FROM employees where first_name = " +
                    fname + " and " + "last_name = " + lname + " and " + " id = " + id);

            int count = 0;

            while (myrs.next()){
                String personInfo = myrs.getString("ID");
                count++;
            }

            if(count == 1){
                mystmt.executeUpdate("DELETE  FROM EMPLOYEES WHERE first_name = " + fname
                        + " and " + "last_name = " + lname + " and " + " id = " + id);
                JOptionPane.showMessageDialog(this, "Name: " + fname + " " + lname + " ID: " + id + " is now Deleted from the Database!");

            }
            else{
                JOptionPane.showMessageDialog(this, "Name or ID was wrong. Please write correct information ");

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Something went wrong. Can't the information you are looking for in the Database.");
            System.out.println("ERROR" + e.getMessage());
        }
        finally {
            EndConnectionDB();
        }

    }

    /**
     * In this method we are running a SQL-Query where the user of the GUI has the posibility of
     * adding more people to the Database. You will also get a confirmation whether adding to the
     * Database went successfully.
     */
    public void addQuery(int id, String fname, String lname, String email, int salary, String department){

        try{

            ConnectToDB();

            mystmt.executeUpdate("INSERT INTO employees (id, first_name, last_name, email, salary, department)" +
                    "values(" + id + ", " + fname + ", " + lname + ", " + email + ", " + salary + ", " + department + ")");

             myrs = mystmt.executeQuery("SELECT * FROM employees where first_name = " +
                    fname + " and " + "last_name = " + lname + " and " + " id = " + id);

            int count = 0;

            while (myrs.next()){
                String personInfo = myrs.getString("ID");
                count++;
            }

            if (count == 1){
                JOptionPane.showMessageDialog(this, "Name: " + fname + " " + lname +
                "ID: " + id + " Department: " + department);
            }

        }catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
        finally {
            EndConnectionDB();
        }

    }

}
