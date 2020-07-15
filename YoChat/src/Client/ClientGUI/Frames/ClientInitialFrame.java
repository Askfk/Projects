package Client.ClientGUI.Frames;

import Client.ClientGUI.Format;
import Client.ClientGUI.GUICommends.ClientGUICommands;
import Client.ClientThreads.ClientServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class contains loginGUI, signUpGUI.
 * @author Yiming Li
 * @version 2020-03
 */

public class ClientInitialFrame {

    private ClientServer clientServe;

    private JFrame loginFrame;
    private JFrame signUpFrame;

    private ClientMainFrame clientMainFrame;

    private JTextField loginUserName;
    private JPasswordField loginPassword;
    private TextField signUserName;
    private TextField signPassword_1;
    private TextField signPassword_2;
    private TextField signEmail;

    /**
     * @param clientServer The main thread of client server.
     */
    public ClientInitialFrame(ClientServer clientServer){
        clientServe = clientServer;
        loginFrame();
    }

    void loginFrame(){

        loginFrame = new JFrame();

        loginFrame.setTitle("Welcome to login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel forUserName = new JPanel(new FlowLayout(FlowLayout.CENTER));
        forUserName.add(new JLabel("User name:"));
        loginUserName = new JTextField(10);
        forUserName.add(loginUserName);

        JPanel forPassword = new JPanel(new FlowLayout(FlowLayout.CENTER));
        forPassword.add(new JLabel("Password: "));
        loginPassword = new JPasswordField(10);
        forPassword.add(loginPassword);

        JPanel forButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        JButton toSignUpButton = new JButton("Sign Up");
        loginButton.setActionCommand(ClientGUICommands.COMMAND_LOGIN);
        toSignUpButton.setActionCommand(ClientGUICommands.COMMAND_TO_SIGNUP);

        loginPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginButton.doClick();
                }
            }
        });

        loginButton.addActionListener(actionListener);
        toSignUpButton.addActionListener(actionListener);
        forButton.add(loginButton);
        forButton.add(toSignUpButton);

        Box vbox = Box.createVerticalBox();
        JPanel labelpanel = new JPanel(new BorderLayout());

        vbox.add(labelpanel);
        vbox.add(forUserName);
        vbox.add(forPassword);
        vbox.add(forButton);

        loginFrame.setContentPane(vbox);

        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    void signUpFrame(){

        signUpFrame = new JFrame();

        signUpFrame.setTitle("Welcome to Sign Up");
        signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box vbox = Box.createVerticalBox();

        JPanel forUserName = new JPanel(new FlowLayout(FlowLayout.CENTER));
        forUserName.add(new JLabel("User name:      "));
        signUserName = new TextField(10);
        forUserName.add(signUserName);

        JPanel forFirstPassword = new JPanel(new FlowLayout(FlowLayout.CENTER));
        forFirstPassword.add(new JLabel("Password:       "));
        signPassword_1 = new TextField(10);
        forFirstPassword.add(signPassword_1);

        JPanel forSecondPassword = new JPanel(new FlowLayout(FlowLayout.CENTER));
        forSecondPassword.add(new JLabel("Repeat password:"));
        signPassword_2 = new TextField(10);
        forSecondPassword.add(signPassword_2);

        JPanel forEmail = new JPanel(new FlowLayout(FlowLayout.CENTER));
        forEmail.add(new JLabel("Email Address:  "));
        signEmail = new TextField(10);
        forEmail.add(signEmail);

        JPanel forButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setActionCommand(ClientGUICommands.COMMAND_SIGNUP);
        signUpButton.addActionListener(actionListener);
        JButton toLoginButton = new JButton("Login");
        toLoginButton.setActionCommand(ClientGUICommands.COMMAND_TO_LOGIN);
        toLoginButton.addActionListener(actionListener);
        forButtons.add(signUpButton);
        forButtons.add(toLoginButton);

        vbox.add(forUserName);
        vbox.add(forFirstPassword);
        vbox.add(forSecondPassword);
        vbox.add(forEmail);
        vbox.add(forButtons);

        signUpFrame.setContentPane(vbox);

        signUpFrame.pack();
        signUpFrame.setLocationRelativeTo(null);
        signUpFrame.setVisible(true);

    }

    // the action listener of all the button in the initial GUI
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command){
                case ClientGUICommands.COMMAND_LOGIN: {
                    // pair the username and password
                    String pair = loginUserName.getText() + "/" + loginPassword.getText();
                    // login
                    boolean loginStatus = clientServe.login(pair);
                    if(loginStatus){ // if login successfully
                        loginFrame.setVisible(false);
                        clientMainFrame = new ClientMainFrame(clientServe);
                        clientMainFrame.simpleMainFrame();
                    }
                    else{ // if login failed
                        new NoticeFrame("Invalid user name or password!");

                    }
                    break;
                }
                case ClientGUICommands.COMMAND_TO_SIGNUP: { // to sign up frame
                    loginFrame.setVisible(false);
                    signUpFrame();
                    break;
                }
                case ClientGUICommands.COMMAND_SIGNUP: {
                    if(signPassword_1.getText().equals(signPassword_2.getText())){ // If the two passwords are the same
                        String infos;
                        String email = signEmail.getText();
                        if(email.matches(Format.emailMatch)){ // if email match the model.
                            infos = signUserName.getText() + "/" + signPassword_1.getText() + "/" + signEmail.getText() + "/" + "normal";
                            boolean signStatus = clientServe.signUp(infos);
                            if(signStatus){
                                signUpFrame.setVisible(false);
                                loginFrame();
                            }
                            else{
                                new NoticeFrame("Connection Error, please check the internet");
                            }
                        }
                        else
                            new NoticeFrame("Please use the correct email format!");
                    }
                    else{
                        new NoticeFrame("The two passwords are not pair.");
                    }
                    break;
                }
                case ClientGUICommands.COMMAND_TO_LOGIN: { // to login frame
                    signUpFrame.setVisible(false);
                    loginFrame();
                    break;
                }
                default: {
                    error("Inner command Error, Error code -15");
                    break;
                }
            }
        }
    };

    void error(String error){
        System.err.println(error);
    }
}
