package better.life;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
public class Login extends JFrame implements ActionListener{

    JButton login, signup, clear;
    JTextField cusidTextField;
    JPasswordField passwordTextField;

    Login(){
        setTitle("Login for a better Life");

        setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image i2=i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        JLabel text = new JLabel("Welcome to Better Life");
        text.setFont(new Font("Osward",Font.BOLD,38));
        text.setBounds(200, 40, 500, 40);
        add(text);

        //CustId Lable
        JLabel CustomerID = new JLabel("Customer ID:");
        CustomerID.setFont(new Font("Osward",Font.BOLD,28));
        CustomerID.setBounds(120, 150, 250, 30);
        add(CustomerID);
        //CusId Text Field
        cusidTextField = new JTextField();
        cusidTextField.setBounds(300, 150, 230, 30);
        cusidTextField.setFont(new Font("Arial",Font.BOLD, 14));
        add(cusidTextField);
        //Passowd lable
        JLabel Password = new JLabel("Password:");
        Password.setFont(new Font("Osward",Font.BOLD,28));
        Password.setBounds(120, 220, 250, 30);
        add(Password);
        //Password Text Field
        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(300, 220, 230, 30);
        passwordTextField.setFont(new Font("Arial",Font.BOLD, 14));
        add(passwordTextField);

        //JButton login
        login = new JButton("SING IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        //JButton clear
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        //JButton sign up
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 480);
        setVisible(true);
        setLocation(450, 200);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==clear){
            cusidTextField.setText("");
            passwordTextField.setText("");

        }else if(ae.getSource()==login){


            String cusid =cusidTextField.getText();

            String password =passwordTextField.getText();
            try{

                DBHelper dbHelper =new DBHelper();
                String query = "select name,password from signup where name='"+cusid+"' and password='"+password+"'";
                ResultSet rs = dbHelper.statement.executeQuery(query);
                if (rs.next()){
                    System.out.println(rs.getString("name"));
                    System.out.println(rs.getString("password"));
                    System.out.println(query);
                    setVisible(false);
                    new AlarmClock().setVisible(true);
                }else{
                    System.out.println("Please enter correct Password");
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }else if(ae.getSource()==signup){
            setVisible(false);
            new Signup().setVisible(true);
        }
    }
    public static void main(String args[]){
        new Login();
    }

}
