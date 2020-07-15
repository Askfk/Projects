package Client.ClientGUI.Frames;

import Tools.ClassTools.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This frame mainly show the group member details.
 * @author Yiming Li
 * @version 2020-03
 */
public class ShowGroupMembersFrame extends JFrame {

    private ArrayList<User> groupMembers;

    /**
     *
     * @param groupMembers all the group members in current group.
     */
    public ShowGroupMembersFrame(ArrayList<User> groupMembers){
        this.groupMembers = groupMembers;
        initialization();
    }

    void initialization(){
        Box vbox = Box.createVerticalBox();
        for(User user : groupMembers){
            JLabel jLabel = new JLabel("  Nickname: ");
            JLabel label = new JLabel(user.getNickName());
            JButton button = new JButton("details");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new PersonalInfoFrame(user);
                }
            });
            JPanel jPanel = new JPanel(new BorderLayout());
            jPanel.add(jLabel, BorderLayout.WEST);
            jPanel.add(label, BorderLayout.CENTER);
            jPanel.add(button, BorderLayout.EAST);
            vbox.add(jPanel);
        }

        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JPanel jPanel = new JPanel();
        jPanel.add(ok);
        vbox.add(jPanel);

        setContentPane(vbox);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
