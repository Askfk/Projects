package Client.ClientGUI.Frames;

import Client.ClientThreads.ClientServer;
import Client.Utils.RecordWriter;
import Tools.ClassTools.Infors.Info;
import Tools.ClassTools.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This frame mainly shows the current chat's history.
 * @author Yiming Li
 * @version 2020-03
 */

public class HistoryFrame extends JFrame {

    private ClientServer clientServer;
    private User currentUser;
    private String[] chatInfo;
    private String toId;

    /**
     *
     * @param clientServer The main thread of client server
     * @param currentUser The current user of client server
     * @param chatInfo Chat info contains [name, id, isOnline, isGroup]
     */
    public HistoryFrame(ClientServer clientServer, User currentUser, String[] chatInfo){
        this.clientServer = clientServer;
        this.currentUser = currentUser;
        this.chatInfo = chatInfo;
        this.toId = chatInfo[1];
        initialization();
    }

    void initialization(){
        setSize(400, 400);
        JPanel upperPanel = new JPanel(new BorderLayout());
        JTextField keywords = new JTextField(10);
        JLabel jLabel = new JLabel("  Keywords: ");
        JButton search = new JButton("Search");
        JTextArea jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        jTextArea.setFont(new Font(null, Font.ROMAN_BASELINE, 15));
        upperPanel.add(jLabel, BorderLayout.WEST);
        upperPanel.add(keywords, BorderLayout.CENTER);
        upperPanel.add(search, BorderLayout.EAST);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        if(chatInfo.length == 4){ // if current chat is a group chat
            search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jTextArea.setText("");
                    String gid = chatInfo[1] + "/" + chatInfo[0];
                    gid = gid.replace("(Group)", "");
                    ArrayList<User> users = clientServer.groupMembers.get(gid);
                    ArrayList<Info> histories;
                    String keyw = keywords.getText();
                    keywords.setText("");
                    if(keyw.equals("")){ // find all the chat history
                        histories = RecordWriter.readAllGroupMessages(toId);
                    }
                    else { // find the chat history by keywords
                        histories = RecordWriter.readGroupMessagesByKey(toId, keyw);
                    }
                    for(Info info : histories){
                        String userName = null;
                        String email = null;
                        if(info.fromID.equals(currentUser.getUID())){
                            userName = currentUser.getNickName();
                            email = currentUser.getEmail();
                        }
                        else {
                            for(User user : users){
                                if(user.getUID().equals(info.fromID)){
                                    userName = user.getNickName();
                                    email = user.getEmail();
                                }
                            }
                        }
                        String message = "[" + info.time + "  " + userName + "<" + email + ">]" + "\n" + info.message + "\n";
                        jTextArea.append(message);
                    }
                }
            });
        }
        else { // if current chat is a personal chat
            search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jTextArea.setText("");
                    String toId = chatInfo[1];
                    String keyw = keywords.getText();
                    ArrayList<Info> histories;
                    User opu = clientServer.friends.get(toId);
                    keywords.setText("");
                    if(keyw.equals("")){ // find all the chat history
                        histories = RecordWriter.readAllMessages(currentUser.getUID(), toId);
                    }
                    else // find the chat history by keywords
                        histories = RecordWriter.readMessagesByKey(currentUser.getUID(), toId, keyw);
                    for(Info info : histories){
                        String userName;
                        String email;
                        if(info.fromID.equals(currentUser.getUID())){
                            userName = currentUser.getNickName();
                            email = currentUser.getEmail();
                        }
                        else {
                            userName = opu.getNickName();
                            email = opu.getEmail();
                        }
                        String message = "[" + info.time + "  " + userName + "<" + email + ">]" + "\n" + info.message + "\n";
                        jTextArea.append(message);
                    }
                }
            });
        }

        upperPanel.setSize(400, 50);
        jScrollPane.setSize(400, 350);

        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(upperPanel, BorderLayout.NORTH);
        jPanel.add(jScrollPane, BorderLayout.CENTER);
        jPanel.setSize(400, 400);
        setContentPane(jPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
