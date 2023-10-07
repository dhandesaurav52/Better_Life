package better.life;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class Signup extends JFrame implements ActionListener{

    long random;
    JTextField nameTextField,fathernameTextField,ageTextField,emailTextField,addressTextField,cityTextField,stateTextField,pincodeTextField;
    JPasswordField passwordTextField;
    JButton next,clear;
    JRadioButton male,female,others,married,unmarried;
    JDateChooser dateChooser;

    Signup(){

        setLayout(null);

        Random ran = new Random();
        long random = Math.abs((ran.nextLong()%9000L) + 1000L);

        JLabel formno = new JLabel("Application Form NO." + random );
        formno.setFont(new Font("Raleway", Font.BOLD, 38));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        //Personal detail label
        JLabel personalDetails = new JLabel("Page 1: Personal Details" );
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personalDetails.setBounds(290, 80, 400, 30);
        add(personalDetails);

        //name label
        JLabel name = new JLabel("Name:" );
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100, 140, 100, 30);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        nameTextField.setBounds(300, 140, 400, 30);
        add(nameTextField);

        //fathername label
        JLabel fathername = new JLabel("Father's Name:" );
        fathername.setFont(new Font("Raleway", Font.BOLD, 20));
        fathername.setBounds(100, 180, 200, 30);
        add(fathername);

        fathernameTextField = new JTextField();
        fathernameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        fathernameTextField.setBounds(300, 180, 400, 30);
        add(fathernameTextField);

        //DOB label
        JLabel dob = new JLabel("D.O.B:" );
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 220, 200, 30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 220, 400, 30);
        dateChooser.setForeground(new Color(105, 105, 105));
        add(dateChooser);

        //Gender label
        JLabel gender = new JLabel("Gender:" );
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100, 260, 200, 30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300, 260, 60, 30);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(450, 260, 120, 30);
        female.setBackground(Color.WHITE);
        add(female);

        ButtonGroup gendergroup= new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);

        //Email label
        JLabel email = new JLabel("Email:" );
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100, 300, 200, 30);
        add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        emailTextField.setBounds(300, 300, 400, 30);
        add(emailTextField);

        //Marital label
        JLabel marital = new JLabel("Marital Status:" );
        marital.setFont(new Font("Raleway", Font.BOLD, 20));
        marital.setBounds(100, 340, 200, 30);
        add(marital);

        married = new JRadioButton("Married");
        married.setBounds(300, 340, 120, 30);
        married.setBackground(Color.WHITE);
        add(married);

        unmarried = new JRadioButton("Un-married");
        unmarried.setBounds(450, 340, 120, 30);
        unmarried.setBackground(Color.WHITE);
        add(unmarried);

        others = new JRadioButton("Others");
        others.setBounds(600, 340, 120, 30);
        others.setBackground(Color.WHITE);
        add(others);

        ButtonGroup marriedgroup= new ButtonGroup();
        marriedgroup.add(married);
        marriedgroup.add(unmarried);
        marriedgroup.add(others);

        //Address label
        JLabel address = new JLabel("Address:" );
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 380, 200, 30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        addressTextField.setBounds(300, 380, 400, 30);
        add(addressTextField);

        //City label
        JLabel city = new JLabel("City:" );
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        city.setBounds(100, 420, 200, 30);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        cityTextField.setBounds(300, 420, 400, 30);
        add(cityTextField);

        //State label
        JLabel State = new JLabel("State:" );
        State.setFont(new Font("Raleway", Font.BOLD, 20));
        State.setBounds(100, 460, 200, 30);
        add(State);

        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        stateTextField.setBounds(300, 460, 400, 30);
        add(stateTextField);

        //Pincode label
        JLabel pincode = new JLabel("Pin Code:" );
        pincode.setFont(new Font("Raleway", Font.BOLD, 20));
        pincode.setBounds(100, 500, 200, 30);
        add(pincode);

        pincodeTextField = new JTextField();
        pincodeTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        pincodeTextField.setBounds(300, 500, 400, 30);
        add(pincodeTextField);

        //Password
        JLabel password = new JLabel("Password:" );
        password.setFont(new Font("Raleway", Font.BOLD, 20));
        password.setBounds(100, 580, 200, 30);
        add(password);

        passwordTextField = new JPasswordField();
        passwordTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        passwordTextField.setBounds(300, 580, 400, 30);
        add(passwordTextField);
        //age lable
        JLabel age = new JLabel("Age:" );
        age.setFont(new Font("Raleway", Font.BOLD, 20));
        age.setBounds(100, 540, 200, 30);
        add(age);

        ageTextField = new JTextField();
        ageTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        ageTextField.setBounds(300, 540, 400, 30);
        add(ageTextField);

        //Button
        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 650, 80, 30);
        next.addActionListener(this);
        add(next);

        //JButton clear
        clear = new JButton("CLEAR");
        clear.setBounds(530, 650, 80, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(450, 80);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==next){
            String formno = "" + random;//long
            String name = nameTextField.getText();
            String fathername = fathernameTextField.getText();
            String dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
            String gender = null;
            if(male.isSelected()){
                gender = "Male";
            }else if(female.isSelected()){
                gender = "Female";
            }
            String email = emailTextField.getText();
            String marital = null;
            if(married.isSelected()){
                marital="Married";
            }else if(unmarried.isSelected()){
                marital = "Unmarried";
            }else if(others.isSelected()){
                marital = "Other's";
            }
            String address = addressTextField.getText();
            String city = cityTextField.getText();
            String state = stateTextField.getText();
            String pincode = pincodeTextField.getText();
            String age = ageTextField.getText();
            String password = passwordTextField.getText();
            //

            //
            try{
                if(name.equals("") || password.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter correct NAME and PASSWORD");
                }else{
                    DBHelper dbHelper =new DBHelper();
//                    String query = ""+formno+"','"+name+"','"+fathername+"','"+dob+"','"+gender+"','"+email+"','"+marital+"','"+address+"','"+city+"','"+state+"','"+pincode+"','"+age+"','"+password+"')";
                        StringBuilder queryBuilder = new StringBuilder("insert into signup(formno, name, fathername,dob, gender, email, marital, address, city, state, pincode, age, password) values('");
                    queryBuilder.append(formno)
                            .append("','")
                            .append(name)
                            .append("','")
                            .append(fathername)
                            .append("','")
                            .append(dob)
                            .append("','")
                            .append(gender)
                            .append("','")
                            .append(email)
                            .append("','")
                            .append(marital)
                            .append("','")
                            .append(address)
                            .append("','")
                            .append(city)
                            .append("','")
                            .append(state)
                            .append("','")
                            .append(pincode)
                            .append("','")
                            .append(age)
                            .append("','")
                            .append(password)
                            .append("')");
                            
                    System.out.println(queryBuilder.toString());
                    dbHelper.statement.executeUpdate(queryBuilder.toString());
                    setVisible(false);
                    new Login().setVisible(true);
                }
            }

            catch(Exception e){
                System.out.println(e);
            }

        }else  if(ae.getSource()==clear){
            nameTextField.setText("");
            fathernameTextField.setText("");
            ageTextField.setText("");
            emailTextField.setText("");
            addressTextField.setText("");
            cityTextField.setText("");
            stateTextField.setText("");
            pincodeTextField.setText("");
            passwordTextField.setText("");
        }
    }


    public static void main(String args[]){
        new Signup();
    }
}
   