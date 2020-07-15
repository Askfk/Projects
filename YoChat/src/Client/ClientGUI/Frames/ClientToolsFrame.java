package Client.ClientGUI.Frames;

import Client.Buffers.FriendsRequestBuffer;
import Client.ClientGUI.Format;
import Client.ClientGUI.GUICommends.ClientGUICommands;
import Client.ClientThreads.ClientServer;
import Tools.ClassTools.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class contains editProfilesGUI, Add new Friends GUI.
 * @author Yiming Li
 * @version 2020-
 */

public class ClientToolsFrame {

    private JFrame currentFrame;

    private TextField nickName;
    private Choice gender;
    private Choice age;
    private Choice nation;
    private JTextArea profile;

    private JTextField userIdText;
    private ClientServer clientServer;

    /**
     * @param clientServer The main thread of client server.
     */

    public ClientToolsFrame(ClientServer clientServer){
        this.clientServer = clientServer;
    }

    // TODO: Make the comments of different contents clear
    public void editProfileFrame(){
        JFrame editProfileFrame = new JFrame("Editing Profile");
        editProfileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box vbox = Box.createVerticalBox();

        JPanel nickPanel = new JPanel(new BorderLayout());
        nickName = new TextField(10);
        nickName.setText(clientServer.getCurrentUser().getNickName());
        nickPanel.add(new JLabel("Nickname: "), BorderLayout.WEST);
        nickPanel.add(nickName);

        vbox.add(nickPanel);

        JPanel genderPanel = new JPanel(new BorderLayout());
        gender = new Choice();
        gender.add("Male");
        gender.add("Female");
        gender.select(clientServer.getCurrentUser().getGender());
        genderPanel.add(new JLabel("Gender:    "), BorderLayout.WEST);
        genderPanel.add(gender);

        vbox.add(genderPanel);

        JPanel agePanel = new JPanel(new BorderLayout());
        age = new Choice();

        for(String a : Format.ages){
            age.add(a);
        }
        age.select(clientServer.getCurrentUser().getAge());
        agePanel.add(new JLabel("Age:         "), BorderLayout.WEST);
        agePanel.add(age);

        vbox.add(agePanel);

        JPanel nationPanel = new JPanel(new BorderLayout());
        nation = new Choice();
        for(String n : Format.contries){
            nation.add(n);
        }
        nation.select(clientServer.getCurrentUser().getNation());
        nationPanel.add(new JLabel("Nation:    "), BorderLayout.WEST);
        nationPanel.add(nation);

        vbox.add(nationPanel);

        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.add(new JLabel("Profile:   "), BorderLayout.WEST);
        profile = new JTextArea();
        profile.setEditable(true);
        profile.setLineWrap(true);
        profile.setFont(new Font(null, Font.ROMAN_BASELINE, 15));
        profilePanel.add(profile);

        vbox.add(profilePanel);

        JPanel buttonPanel = new JPanel();
        JButton changeInfo = new JButton("Change");
        changeInfo.setActionCommand(ClientGUICommands.COMMAND_CHANGE_INFO);
        changeInfo.addActionListener(actionListener);

        JButton backToMain = new JButton("Back");
        backToMain.setActionCommand(ClientGUICommands.COMMAND_BACK);
        backToMain.addActionListener(actionListener);

        buttonPanel.add(changeInfo);
        buttonPanel.add(backToMain);
        vbox.add(buttonPanel);

        editProfileFrame.setContentPane(vbox);
        editProfileFrame.pack();
        editProfileFrame.setLocationRelativeTo(null);

        currentFrame = editProfileFrame;
        editProfileFrame.setVisible(true);
    }

    public void addFriendFrame(){
        JFrame addFriendFrame = new JFrame("Add Friend");
        Box vbox = Box.createVerticalBox();
        JPanel addfriend = new JPanel();
        addfriend.add(new JLabel("User Email: "));
        userIdText = new JTextField(10);
        addfriend.add(userIdText);

        JPanel buttonPanel = new JPanel();
        JButton addf = new JButton("Add");
        addf.setActionCommand(ClientGUICommands.COMMAND_ADD_FRIENDS);
        addf.addActionListener(actionListener);

        JButton back = new JButton("Back");
        back.setActionCommand(ClientGUICommands.COMMAND_BACK);
        back.addActionListener(actionListener);
        buttonPanel.add(addf);
        buttonPanel.add(back);

        vbox.add(addfriend);
        vbox.add(buttonPanel);

        addFriendFrame.setContentPane(vbox);
        addFriendFrame.pack();
        addFriendFrame.setLocationRelativeTo(null);

        currentFrame = addFriendFrame;
        addFriendFrame.setVisible(true);
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commend = e.getActionCommand();
            switch (commend){
                case ClientGUICommands.COMMAND_CHANGE_INFO: {
                    User user = clientServer.getCurrentUser();
                    user.setNickName(nickName.getText());
                    user.setAge(age.getSelectedItem());
                    user.setGender(gender.getSelectedItem());
                    user.setNation(nation.getSelectedItem());
                    clientServer.changeInfos(user);
                    currentFrame.setVisible(false);
                    break;
                }
                case ClientGUICommands.COMMAND_ADD_FRIENDS: {
                    String email = userIdText.getText();
                    if(email.matches(Format.emailMatch)){
                        for(User user : clientServer.friends.values()) {
                            if (user.getEmail().equals(email)) {
                                new NoticeFrame("You are already fiends");
                                break;
                            }
                        }
                        User user = clientServer.getUserInfoByEmail(email);
                        if(user == null){
                            new NoticeFrame("Email address does not exit!");
                            break;
                        }
                        else {
                            FriendsRequestBuffer.addFriendsRequestBuffer(user);
                            currentFrame.setVisible(false);
                            break;
                        }
                    }
                    else{
                        new NoticeFrame("Please enter the correct email format!");
                        break;
                    }
                }
                case ClientGUICommands.COMMAND_BACK: {
                    currentFrame.setVisible(false);
                    break;
                }
            }
        }
    };

}
