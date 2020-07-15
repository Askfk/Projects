package Client.ClientGUI.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This frame mainly shows the notice with given notices.
 * @author Yiming Li
 * @version 2020-03
 */

public class NoticeFrame extends JFrame {

    private String notice;

    /**
     * @param notice Given notice.
     */
    public NoticeFrame(String notice){
        this.notice = notice;
        Initialization();
    }

    void Initialization(){
        Box vbox = Box.createVerticalBox();
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(notice));
        vbox.add(jPanel);
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        JPanel jPanel1 = new JPanel();
        jPanel1.add(ok);
        vbox.add(jPanel1);

        setContentPane(vbox);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
