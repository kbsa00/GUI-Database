import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Author: Khalid B Said
 * Description of the project:
 *
 * Just a little project that I personally been working on in the Summer. I created a Database and
 * a Graphical User Interface that are connected to each other . Which means that you are sending
 * SQL Query through the GUI to the Database and later on displaying that information on your
 * Application. You can do everything from adding, deleting and search for spesific information
 * to the database.
 *
 */

public class GUI extends JFrame {
    private JButton search, exit, delete, add;
    private JLabel identifikator, firstname, lastname, mail, salry, dept;
    private JDialog dialog;
    private JTextArea txtarea;
    private JTextField idbox, firstbox, lastbox, email, salary, department;
    private String fname, lname;
    private int id;

    /**
     * In the Constructor is where the Main GUI is written and it's
     * respective Button functions.
     */
    public GUI(){
        setTitle("Search Database");
        JPanel panelnorth = new JPanel(new GridLayout(2,3));
        JPanel panelsouth = new JPanel(new GridLayout(1,3));

         identifikator = new JLabel("ID");
        firstname = new JLabel("Firstname");
        lastname = new JLabel("Lastname");
        idbox = new JTextField();
        firstbox = new JTextField();
        lastbox = new JTextField();
        panelnorth.add(identifikator);
        panelnorth.add(firstname);
        panelnorth.add(lastname);
        panelnorth.add(idbox);
        panelnorth.add(firstbox);
        panelnorth.add(lastbox);
        add(panelnorth, BorderLayout.NORTH);


        txtarea = new JTextArea();
        txtarea.setEditable(false);
        add(txtarea, BorderLayout.CENTER);


        search = new JButton("Search");
        exit = new JButton("Exit");
        delete = new JButton("Delete");
        add = new JButton("Add");

        panelsouth.add(search);
        panelsouth.add(add);
        panelsouth.add(delete);
        panelsouth.add(exit);
        add(panelsouth, BorderLayout.SOUTH);

        search.addActionListener(e -> {
            if (idbox.getText().equals("") || firstbox.getText().equals("") || lastbox.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please fill out everything.");
            }
            else {

                int id = Integer.parseInt(idbox.getText());
                fname = "'";
                fname += firstbox.getText();
                fname += "'";
                lname = "'";
                lname += lastbox.getText();
                lname += "'";

                Database d1 = new Database();
                d1.searchQuery(id, fname, lname);

            }
        });

        exit.addActionListener(e ->{
            System.exit(0);
        });

        delete.addActionListener(e -> {
            int youranswer = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this person from the Database?");

            if (idbox.getText().equals("") || firstbox.getText().equals("") || lastbox.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please fill out everything");
            }
            else if (youranswer == JOptionPane.YES_OPTION) {
                id = Integer.parseInt(idbox.getText());
                fname = "'";
                fname += firstbox.getText();
                fname += "'";
                lname = "'";
                lname += lastbox.getText();
                lname += "'";

                Database b1 = new Database();
                b1.deleteQuery(id, fname, lname);

            }
        });

        add.addActionListener(e -> {
            addingToDBGUI();
        });


        setResizable(false);
        setSize(700,300);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Retrieving the information from the Database and then printing it out
     * to the TextArea on the Application.
     */

    public void PrintDatabase(ArrayList<String> list){
        if (list.size() == 0){
            JOptionPane.showMessageDialog(this, "Something went wrong. Can't the information you are looking for in the Database.");
        }
        else {
            txtarea.setText("ID \t Firstname \t Lastname \t email \t\t Department \t Salary \n");
            for (String print : list) {
                txtarea.append(print + " \t");
            }
        }
    }

    /**
     * This method starts up another GUI where the User will have the possibility of
     * using a nice Interface while they are adding more people to the Database.
     */

    public void addingToDBGUI(){
        dialog = new JDialog();
        dialog.setTitle("Add to the Database");
        dialog.setSize(650,120);
        JPanel panel = new JPanel(new GridLayout(2,6));
        JPanel panelcenter = new JPanel(new GridLayout(1,0));


        identifikator = new JLabel("ID");
        firstname = new JLabel("Firstname");
        lastname = new JLabel("Lastname");
        mail = new JLabel("E-Mail");
        salry = new JLabel("salary");
        dept = new JLabel("Department");

        panel.add(identifikator);
        panel.add(firstname);
        panel.add(lastname);
        panel.add(mail);
        panel.add(salry);
        panel.add(dept);

        idbox = new JTextField();
        firstbox = new JTextField();
        lastbox = new JTextField();
        email = new JTextField();
        salary = new JTextField();
        department = new JTextField();

        panel.add(idbox);
        panel.add(firstbox);
        panel.add(lastbox);
        panel.add(email);
        panel.add(salary);
        panel.add(department);
        dialog.add(panel, BorderLayout.NORTH);

        JButton add2 = new JButton("Add to Database");
        panelcenter.add(add2);
        dialog.add(panelcenter, BorderLayout.CENTER);

        add2.addActionListener(e -> {
            if (idbox.getText().equals("") || firstbox.getText().equals("") || lastbox.getText().equals("")|| email.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please fill out everything.");
            }else {
                int id = Integer.parseInt(idbox.getText());
                String fname = "'";
                fname += firstbox.getText();
                fname += "'";
                String lname = "'";
                lname += lastbox.getText();
                lname += "'";
                String eaddr = "'";
                eaddr += email.getText();
                eaddr += "'";
                int payment = Integer.parseInt(salary.getText());
                String dep = "'";
                dep += department.getText();
                dep += "'";

                Database d1 = new Database();
                d1.addQuery(id, fname, lname, eaddr, payment, dep);
            }
        });

        dialog.setResizable(false);
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
