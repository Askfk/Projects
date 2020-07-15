package Client.ClientGUI.Frames;

import Client.ClientGUI.GUICommends.ClientGUICommands;
import Client.ClientGUI.CellRenderers.CellRendererButton;
import Client.ClientGUI.JButtonListModel.JButtonListModel;
import Client.Utils.GetCurrentTime;
import Client.Buffers.MessageBuffer;
import Client.ClientThreads.ClientServer;
import Tools.ClassTools.Infors.Info;
import Tools.ClassTools.Users.User;
import Tools.SocketInstructions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class contains only Main Chat GUI, like the format of WeChat
 * @author Yiming Li
 * @version 2020-03
 */

public class ClientMainFrame {

    private JFrame mainFrame;

    private JTextArea messageArea;
    private Choice onlineStatus;
    private JButtonListModel friendsNameListModle;
    private JTextField message;
    private JList<String> friendsNameList;
    private JButton groupMemberDetails;

    private ClientServer clientServer;

    private User currentUser;

    private int width;
    private int height;

    ClientToolsFrame clientToolsFrame;

    /**
     * @param clientServer The main thread of client server.
     */

    public ClientMainFrame(ClientServer clientServer) {
        this.clientServer = clientServer;
        currentUser = clientServer.getCurrentUser();
        this.clientToolsFrame = new ClientToolsFrame(clientServer);
        messageArea = new JTextArea();
        clientServer.messageArea = messageArea;

        friendsNameListModle = new JButtonListModel();
        clientServer.friendsListModel = friendsNameListModle;
        friendsNameList = new JList<>();
        friendsNameList.setCellRenderer(new CellRendererButton(clientServer));
        clientServer.friendsList = friendsNameList;
//        friendsNameList.setModel(clientServe.friendsListModel);

        width = 800;
        height = 600;
    }

    void simpleMainFrame(){
        mainFrame = new JFrame(currentUser.getNickName() + "<" + currentUser.getEmail() + ">");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(width, height);


        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setFont(new Font(null, Font.ROMAN_BASELINE, 15));

        JScrollPane forMessageArea = new JScrollPane(messageArea);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Edit");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Change Profile");
        m11.setActionCommand(ClientGUICommands.COMMAND_EDIT_PROFILE);
        m11.addActionListener(actionListener);
        JMenuItem m22 = new JMenuItem("Logout");
        m22.setActionCommand(ClientGUICommands.COMMAND_LOGOUT);
        m22.addActionListener(actionListener);

        m1.add(m11);
        m1.add(m22);

        JButton createGroup = new JButton("New Group");
        createGroup.setActionCommand(ClientGUICommands.COMMAND_CREATE_GROUP_CHAT);
        createGroup.addActionListener(actionListener);

        onlineStatus = new Choice();
        onlineStatus.add("Online");
        onlineStatus.add("Off-line");
        onlineStatus.setSize(width/10, height/20);

        onlineStatus.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String status = e.getItem().toString();
                boolean changeStatus;
                if(status.equals("Off-line")){
                    changeStatus = clientServer.changeOnlineStatus(false);
                }
                else
                    changeStatus = clientServer.changeOnlineStatus(true);
                if(!changeStatus){
                    new NoticeFrame("Change Online Status Failed!");
                }
            }
        });

        groupMemberDetails = new JButton("Show Group Members");
        groupMemberDetails.setActionCommand(ClientGUICommands.COMMAND_SHOW_GROUP_MEMBERS);
        groupMemberDetails.addActionListener(actionListener);
        groupMemberDetails.setVisible(false);
        clientServer.groupMemberDetails = groupMemberDetails;

        JButton addFriendsButton = new JButton("Add new Friend");
        addFriendsButton.setActionCommand(ClientGUICommands.COMMAND_ADD_FRIENDS_FRAME);
        addFriendsButton.addActionListener(actionListener);

        JButton matchButton = new JButton("Match");
        matchButton.setActionCommand(ClientGUICommands.COMMAND_MATCH);
        matchButton.addActionListener(actionListener);

        JButton history = new JButton("History");
        history.setActionCommand(ClientGUICommands.COMMAND_GET_HISTORY);
        history.addActionListener(actionListener);

        mb.add(onlineStatus);
        mb.add(groupMemberDetails);
        mb.add(history);
        mb.add(createGroup);
        mb.add(addFriendsButton);
        mb.add(matchButton);


        message = new JTextField(50);

        JButton sendButton = new JButton("Send");
        sendButton.setActionCommand(ClientGUICommands.COMMAND_SEND_MESSAGE);
        sendButton.addActionListener(actionListener);

        // bond the enter/return in keyboard with message textfield.
        message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    sendButton.doClick();
                }
            }
        });

        JScrollPane friendsList = new JScrollPane(friendsNameList);

        friendsList.setBounds(0, 0, 200, 550 - mb.getHeight());
        forMessageArea.setBounds(200, 0, 600, 520 - mb.getHeight());
        message.setBounds(210, 520, 460, 20);
        sendButton.setBounds(680, 520, 100, 20);

        mainFrame.setJMenuBar(mb);
        mainFrame.getContentPane().add(friendsList);
        mainFrame.getContentPane().add(forMessageArea);
        mainFrame.getContentPane().add(message);
        mainFrame.getContentPane().add(sendButton);

        JLabel padding = new JLabel();
        padding.setVisible(false);
        mainFrame.getContentPane().add(padding);

        mainFrame.setBackground(Color.DARK_GRAY);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        this.currentUser = this.clientServer.getCurrentUser();
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commend = e.getActionCommand();

            switch (commend){
                case ClientGUICommands.COMMAND_LOGOUT: {
                    clientServer.finalize();
                    mainFrame.setVisible(false);
                    System.exit(0);
                    break;
                }
                case ClientGUICommands.COMMAND_EDIT_PROFILE: { // go to edit profile frame
                    clientToolsFrame.editProfileFrame();
                    break;
                }
                case ClientGUICommands.COMMAND_GET_HISTORY: { // show the chat history
                    String chatInfo = friendsNameList.getSelectedValue();
                    if(chatInfo != null){
                        String[] chatInfos = chatInfo.split("/");
                        new HistoryFrame(clientServer, currentUser, chatInfos);
                    }
                    break;
                }
                case ClientGUICommands.COMMAND_SEND_MESSAGE: {
                    String text = message.getText();
                    String date = GetCurrentTime.getCurrentTime();
                    if(friendsNameList.getSelectedValue() != null){
                        String[] chatInfo = friendsNameList.getSelectedValue().split("/");
                        String toId = chatInfo[1];
                        String mess = "[" + date + "  " + currentUser.getNickName() + "<" + currentUser.getEmail() + ">]" + "\n" + text + "\n";
                        messageArea.append(mess);
                        Info info;
                        if(chatInfo.length == 4){
                            info = new Info(clientServer.getCurrentUser().getUID(), toId, text, date, false);
                        }
                        else{
                            info = new Info(clientServer.getCurrentUser().getUID(), toId, text, date, true);
                        }
                        MessageBuffer.serverMessageBuffer.add(info);
                    }
                    message.setText("");
                    break;
                }
                case ClientGUICommands.COMMAND_SHOW_GROUP_MEMBERS: {
                    String[] group = friendsNameList.getSelectedValue().split("/");
                    String key = group[1] + "/" + group[0];
                    key = key.replace("(Group)", "");
                    ArrayList<User> groupMembers = clientServer.groupMembers.get(key);
                    new ShowGroupMembersFrame(groupMembers);
                    break;
                }
                case ClientGUICommands.COMMAND_CREATE_GROUP_CHAT: {
                    new CreateGroupChatFrame(clientServer);
                    break;
                }
                case ClientGUICommands.COMMAND_ADD_FRIENDS_FRAME: {
                    clientToolsFrame.addFriendFrame();
                    break;
                }
                case ClientGUICommands.COMMAND_MATCH: {
                    clientServer.match();
                    break;
                }
            }
        }
    };

}
