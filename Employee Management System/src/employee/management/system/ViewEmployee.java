package employee.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployee extends JFrame implements ActionListener {

    // Declaration of GUI components
    JTable table;
    Choice cemployeeId;
    JButton search, print, update, back;

    ViewEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Adding a label and choice component for employee ID search
        JLabel searchlbl = new JLabel("Search by Employee ID");
        searchlbl.setBounds(20, 20, 150, 20);
        add(searchlbl);
        
        cemployeeId = new Choice();
        cemployeeId.setBounds(180, 20, 150, 20);
        add(cemployeeId);
        
        // Fetching employee IDs from the database and populating the choice component
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            
            while (rs.next()) {
                cemployeeId.add(rs.getString("empID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        table = new JTable();
        
        // Fetching employee data from the database and setting it to the table using DbUtils
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Adding a scroll pane for the table
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 60);
        add(jsp);
        
        // Adding buttons for search, print, update, and back actions
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);
        
        update = new JButton("Update");
        update.setBounds(220, 70, 80, 20);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBounds(320, 70, 80, 20);
        back.addActionListener(this);
        add(back);
        
        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }
    
    // ActionListener implementation for button actions
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            // Handling the search button action by querying the database with the selected employee ID
            String query = "select * from employee where empId  = '"+cemployeeId.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            // Handling the print button action by printing the table
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            // Handling the update button action by hiding the current frame and opening the UpdateEmployee frame
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
        } else {
            // Handling the back button action by hiding the current frame and opening the Home frame
            setVisible(false);
            new Home();
        }
    }
    
    public static void main(String[] args) {
        new ViewEmployee();
    }
}
