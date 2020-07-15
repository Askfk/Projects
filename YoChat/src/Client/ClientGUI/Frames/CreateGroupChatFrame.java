package Client.ClientGUI.Frames;

import Client.ClientGUI.Format;
import Client.ClientThreads.ClientServer;
import Tools.ClassTools.Users.User;

import javax.net.ssl.KeyManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * This frame mainly for create group chat.
 * @author Yiming Li
 * @version 2020-03
 */

public class CreateGroupChatFrame extends JFrame {

    private ClientServer clientServer;
    private Map<JCheckBox, String> checkChoosedFriends;

    /**
     * @param clientServer The main thread of client server.
     */

    public CreateGroupChatFrame(ClientServer clientServer){
        this.clientServer = clientServer;
        this.checkChoosedFriends = new HashMap<>();
        initialization();
    }

    void initialization(){
        setSize(200, 400);
        Box vbox = Box.createVerticalBox();
        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.add(new JLabel("Group Name:"), BorderLayout.WEST);
        JTextField groupName = new JTextField(10);
        upperPanel.add(groupName, BorderLayout.CENTER);
        vbox.add(upperPanel);

        for(User user : clientServer.friends.values()){
            JCheckBox checkBox = new JCheckBox();
            JLabel label = new JLabel(user.getNickName());
            JButton button = new JButton("details");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new PersonalInfoFrame(user);
                }
            });
            JPanel jPanel = new JPanel(new BorderLayout());
            jPanel.add(checkBox, BorderLayout.WEST);
            jPanel.add(label, BorderLayout.CENTER);
            jPanel.add(button, BorderLayout.EAST);
            vbox.add(jPanel);

            checkChoosedFriends.put(checkBox, user.getUID());
        }

        JPanel emailPanel = new JPanel(new BorderLayout());
        emailPanel.add(new JLabel("Emails (separate by ;): "), BorderLayout.WEST);
        JTextField Emails = new JTextField(15);
        emailPanel.add(Emails);

        vbox.add(emailPanel);

        JPanel basePanel = new JPanel();
        JButton Create = new JButton("Create");
        Create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!groupName.getText().equals("")){
                    String groupMembersId = groupName.getText() + ":";
                    String inviteEmails = Emails.getText();
                    if(!inviteEmails.equals("")){
                        String[] emails = inviteEmails.split(";");
                        for(String email : emails){
                            if(!email.matches(Format.emailMatch)){
                                new NoticeFrame("Please enter the correct email format");
                                return;
                            }
                        }
                    }

                    /**
                     * ===================================================================================================
                     * Here need to add the email operations, if need the function that invite people into group by emails.
                     * ===================================================================================================
                     */

                    for(JCheckBox jCheckBox : checkChoosedFriends.keySet()){
                        if(jCheckBox.isSelected()){
                            groupMembersId += checkChoosedFriends.get(jCheckBox) + "/";
                        }
                    }

                    if(groupMembersId.equals(groupName.getText() + ":")){
                        new NoticeFrame("You cannot create a group with no members selected.");
                    }
                    else {
                        groupMembersId += clientServer.getCurrentUser().getUID();
                        boolean createStatus = clientServer.createGroupChat(groupMembersId);
                        if(createStatus){
                            new NoticeFrame("Create Group Successfully");
                            setVisible(false);
                        }
                        else {
                            new NoticeFrame("Create Group Failed!");
                        }
                    }
                }
                else
                    new NoticeFrame("Group name can not be empty.");

            }
        });


        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        basePanel.add(Create);
        basePanel.add(cancel);
        vbox.add(basePanel);

        JScrollPane scrollPane = new JScrollPane(vbox);
        setContentPane(scrollPane);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
