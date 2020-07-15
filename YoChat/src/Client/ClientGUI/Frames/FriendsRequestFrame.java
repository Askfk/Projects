package Client.ClientGUI.Frames;

import Client.Buffers.FriendsReplyBuffer;
import Tools.ClassTools.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This frame is mainly show the friends requests when login
 * @author Yiming Li
 * @version 2020-03
 */

public class FriendsRequestFrame extends JFrame {

    private ArrayList<User> users;

    /**
     *
     * @param users All the users that request to be friends.
     */
    public FriendsRequestFrame(ArrayList<User> users){
        this.users = users;
        initialization();
    }

    void initialization(){
        Box vbox = Box.createVerticalBox();

        for(User user : users){
            JPanel jPanel = new JPanel(new BorderLayout());
            JLabel jLabel = new JLabel("  " + user.getNickName() + "<" + user.getEmail() + "> wants to be your friend");
            JButton accept = new JButton("Accept");
            accept.setActionCommand("accept/" + user.getUID());
            accept.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String commend = e.getActionCommand();
                    FriendsReplyBuffer.addFriendsReplyBuffer(commend);
                    jPanel.setVisible(false);
                }
            });
            JButton refuse = new JButton("Refuse");
            refuse.setActionCommand("refuse/" + user.getUID());
            refuse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String commend = e.getActionCommand();
                    FriendsReplyBuffer.addFriendsReplyBuffer(commend);
                    jPanel.setVisible(false);
                }
            });
            jPanel.add(jLabel, BorderLayout.WEST);
            jPanel.add(accept, BorderLayout.CENTER);
            jPanel.add(refuse, BorderLayout.EAST);
            vbox.add(jPanel);
        }

        JButton close  = new JButton("Close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        JPanel jPanel = new JPanel();
        jPanel.add(close);
        vbox.add(jPanel);
        setContentPane(vbox);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
