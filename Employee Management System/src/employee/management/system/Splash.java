package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame implements ActionListener {
    
    Splash() {
        // Set up the splash screen frame
        getContentPane().setBackground(Color.WHITE); // Set the background color to white
        setLayout(null); // Disable the layout manager
        
        // Create and configure the heading label
        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(80, 30, 1200, 60); // Set the position and size of the label
        heading.setFont(new Font("seriff", Font.PLAIN, 60)); // Set the font and size of the text
        heading.setForeground(Color.RED); // Set the text color to red
        add(heading); // Add the heading label to the frame
        
        // Create and configure the image label
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg")); // Load the image
        Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT); // Scale the image
        ImageIcon i3 = new ImageIcon(i2); // Create an ImageIcon from the scaled image
        JLabel image = new JLabel(i3);
        image.setBounds(50, 100, 1050, 500); // Set the position and size of the label
        add(image); // Add the image label to the frame
        
        // Create and configure the "Click Here to Continue" button
        JButton clickhere = new JButton("Click Here to Continue");
        clickhere.setBounds(400, 400, 300, 70); // Set the position and size of the button
        clickhere.setBackground(Color.BLACK); // Set the background color to black
        clickhere.setForeground(Color.WHITE); // Set the text color to white
        clickhere.addActionListener(this); // Register the ActionListener for button clicks
        image.add(clickhere); // Add the button to the image label
        
        setSize(1170, 650); // Set the size of the frame
        setLocation(200, 100); // Set the position of the frame
        setVisible(true); // Make the frame visible
        
        // Create a blinking effect for the heading label
        while (true) {
            heading.setVisible(false); // Hide the label
            try {
                Thread.sleep(500); // Wait for 500 milliseconds
            } catch (Exception e) {
                // Ignore any exceptions
            }
            heading.setVisible(true); // Show the label
            try {
                Thread.sleep(500); // Wait for 500 milliseconds
            } catch (Exception e) {
                // Ignore any exceptions
            }
        }
    }
    
    public void actionPerformed(ActionEvent ee) {
        setVisible(false); // Hide the splash screen
        new Login(); // Create a new instance of the Login class
    }
    
    public static void main(String args[]) {
        // Create an anonymous object of the Splash class
        new Splash();
    }
}
