package user_form;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.GridLayout;

import java.awt.event.*;

//Java Swing User Registration Form:

public class User_Form implements ActionListener {
    static JFrame frame;

    static JPanel mainPanel1;
    static JLabel signUpLabel;

    static JPanel subPanel1;
    static JTextField nameField;
    static JTextField emailField;
    static JTextField phoneNumField;
    
    static JPanel subPanel2;
    static JLabel contactLabel;
    static JRadioButton emailJRadioButton;
    static JRadioButton phoneJRadioButton;
    static JRadioButton SMSJRadioButton;
    static JRadioButton appNotificationJRadioButton;

    static JPanel mainPanel2;
    static JButton submit;

    public static void main(String[] args) {
        User_Form formInstance = new User_Form();

        frame = new JFrame("User Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 300);

        //Text to show Sign-In message
        signUpLabel = new JLabel("Sign Up:");
        signUpLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        signUpLabel.setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 20)); // Margin padding

        // Panel containing radio buttons and textfields side-by-side
        mainPanel1 = new JPanel();
        mainPanel1.setLayout(new BorderLayout(20, 0)); // 20px horizontal gap between sub-panels

        // --- SUB PANEL 1: Text Fields (Stacked vertically) ---
        subPanel1 = new JPanel(new GridLayout(3, 1, 0, 10)); // 10px vertical gap
        subPanel1.setPreferredSize(new Dimension(160, 100)); 

        nameField = new JTextField("Full Name");
        emailField = new JTextField("Email Address");
        phoneNumField = new JTextField("Phone Number");

        subPanel1.add(nameField);
        subPanel1.add(emailField);
        subPanel1.add(phoneNumField);

        // --- SUB PANEL 2: Radio Buttons (Stacked vertically) ---
        subPanel2 = new JPanel(new GridLayout(5, 1, 0, 2)); // 2px vertical gap
        
        contactLabel = new JLabel("Preferred Contact:");

        emailJRadioButton = new JRadioButton("Email", true);
        emailJRadioButton.setFocusable(false);
        
        phoneJRadioButton = new JRadioButton("Phone");
        phoneJRadioButton.setFocusable(false);
        
        SMSJRadioButton = new JRadioButton("SMS");
        SMSJRadioButton.setFocusable(false);
        
        appNotificationJRadioButton = new JRadioButton("App Notifications");
        appNotificationJRadioButton.setFocusable(false);

        // Group logical selection
        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(emailJRadioButton);
        bGroup.add(phoneJRadioButton);
        bGroup.add(SMSJRadioButton);
        bGroup.add(appNotificationJRadioButton);
        
        // Add components to subPanel2 grid
        subPanel2.add(contactLabel);
        subPanel2.add(emailJRadioButton);
        subPanel2.add(phoneJRadioButton);
        subPanel2.add(SMSJRadioButton);
        subPanel2.add(appNotificationJRadioButton);

        // Place sub-panels side-by-side inside mainPanel1
        mainPanel1.add(subPanel1, BorderLayout.WEST);
        mainPanel1.add(subPanel2, BorderLayout.CENTER); 
        
        // Give mainPanel1 padding from window borders
        mainPanel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // --- BOTTOM PANEL: Submit Button ---
        mainPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.setPreferredSize(new Dimension(100, 30));
        
        // Register action listener
        submit.addActionListener(formInstance);

        mainPanel2.add(submit);

        // Add to Frame
        frame.add(signUpLabel, BorderLayout.NORTH);
        frame.add(mainPanel1, BorderLayout.CENTER);
        frame.add(mainPanel2, BorderLayout.SOUTH);
        
        frame.setLocationRelativeTo(null); // Centers window on screen
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            // 1. Reset border styling to default before checking
            nameField.setBorder(UIManager.getBorder("TextField.border"));
            emailField.setBorder(UIManager.getBorder("TextField.border"));

            // 2. Fetch and trim text box values
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            //String phone = phoneNumField.getText().trim();

            // 3. Validate Full Name
            if (name.isEmpty() || name.equals("Full Name")) {
                nameField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                JOptionPane.showMessageDialog(frame, "Please enter your Full Name!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                nameField.requestFocus();
                return; // Cancel submit flow
            }

            // 4. Validate Email Address
            if (email.isEmpty() || email.equals("Email Address")) {
                emailField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                JOptionPane.showMessageDialog(frame, "Please enter your Email Address!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                emailField.requestFocus();
                return; // Cancel submit flow
            }

            // 5. Basic Email Format Check (@ and .)
            if (!email.contains("@") || !email.contains(".")) {
                emailField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                JOptionPane.showMessageDialog(frame, "Please enter a valid email format (e.g. user@email.com)!", "Invalid Format", JOptionPane.ERROR_MESSAGE);
                emailField.requestFocus();
                return; // Cancel submit flow
            }

            // 6. Read Radio Button Selection
            String selectedContact = "";
            if (emailJRadioButton.isSelected()) selectedContact = "Email";
            else if (phoneJRadioButton.isSelected()) selectedContact = "Phone";
            else if (SMSJRadioButton.isSelected()) selectedContact = "SMS";
            else if (appNotificationJRadioButton.isSelected()) selectedContact = "App Notifications";

            // 7. Show Success Dialog
            JOptionPane.showMessageDialog(
                frame, 
                "Registration Successful!\n\nName: " + name + "\nEmail: " + email + "\nPreferred Contact: " + selectedContact, 
                "Form Submitted", 
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}