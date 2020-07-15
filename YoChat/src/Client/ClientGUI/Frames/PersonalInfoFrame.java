package Client.ClientGUI.Frames;

import Tools.ClassTools.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This frame mainly show the personal info of given user.
 * @author Yiming Li
 * @version 2020-03
 */
public class PersonalInfoFrame extends JFrame {

    private User user;

    /**
     *
     * @param user
     */
    public PersonalInfoFrame(User user){
        this.user = user;
        initialization();
    }

    void initialization(){
        Box vbox = Box.createVerticalBox();
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Nickname: " + user.getNickName()));
        vbox.add(jPanel);
        JPanel jPanel1 = new JPanel();
        jPanel1.add(new JLabel("Age: " + user.getAge()));
        vbox.add(jPanel1);
        JPanel jPanel2 = new JPanel();
        jPanel2.add(new JLabel("Gender: " + user.getGender()));
        vbox.add(jPanel2);
        JPanel jPanel3 = new JPanel();
        jPanel3.add(new JLabel("Nation: " + user.getNation()));
        vbox.add(jPanel3);
        JPanel jPanel4 = new JPanel();
        jPanel4.add(new JLabel("Email: " + user.getEmail()));
        vbox.add(jPanel4);
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        JPanel jPanel5 = new JPanel();
        jPanel5.add(ok);
        vbox.add(jPanel5);
        setContentPane(vbox);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
