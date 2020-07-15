package Client.ClientGUI.CellRenderers;

import Client.ClientThreads.ClientServer;
import Client.Utils.RecordWriter;
import Tools.ClassTools.Infors.Info;
import Tools.ClassTools.Users.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class extends JButton and implements ListCellRenderer, in order to customize the friends list with jbuttons.
 * @author Yiming Li
 * @version 2020-03
 */

public class CellRendererButton extends JButton implements ListCellRenderer {

    private ClientServer clientServer;

    /*类CellRenderer继承JButton并实作ListCellRenderer.
     */
    /*
       Class CellRenderer extends JButton and implements ListCellRenderer.
     */

    /**
     *
     * @param clientServer The main thread of client server.
     */
    public CellRendererButton(ClientServer clientServer) {
        this.clientServer = clientServer;
        setOpaque(true);
    }
    /*Deal with the operations of all jbuttons in the jlist*/
    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {

        /*
        Check whether value is type String and it actually should be "nickname/UID/isOnline/isGroup" if is a friend and
         "groupname(Group)/gid/true/isGroup" if is a group
         */
        if (value instanceof String) {
            // Set the name of the jbutton.
            setText(((String) value).split("/")[0]);
        }
        // if the button is selected (clicked)
        if (isSelected) {
            // Set the colour of the button cyan
            setForeground(Color.CYAN);
            // initial the message area in GUI
            clientServer.messageArea.setText("");
            User currentUser = clientServer.getCurrentUser();
            String[] id = ((String) value).split("/");
            if(id.length == 4){ // if is a group
                // get all the infos that have been stored.
                ArrayList<Info> infos = RecordWriter.readAllGroupMessages(id[1]);
                String key = id[1] + "/" + id[0];
                key = key.replace("(Group)", "");
                // Get all the group members in this group
                ArrayList<User> users = clientServer.groupMembers.get(key);
                // make check group members jbutton visible in the GUI.
                clientServer.groupMemberDetails.setVisible(true);
                // Append all the infos to the message area
                for(Info info : infos){
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
                    clientServer.messageArea.append(message);
                }
            }
            else { // if is a friend
                // make check group members jbutton invisible in the GUI.
                clientServer.groupMemberDetails.setVisible(false);
                // get all the messages with this user.
                ArrayList<Info> infos = RecordWriter.readAllMessages(currentUser.getUID(), id[1]);
                // append all the messages to the message area.
                for(Info info : infos){
                    String userName;
                    String email;
                    if(info.fromID.equals(currentUser.getUID())){
                        userName = currentUser.getNickName();
                        email = currentUser.getEmail();
                    }
                    else {
                        userName = clientServer.friends.get(info.fromID).getNickName();
                        email = clientServer.friends.get(info.fromID).getEmail();
                    }

                    String message = "[" + info.time + "  " + userName + "<" + email + ">]" + "\n" + info.message + "\n";
                    clientServer.messageArea.append(message);
                }
            }
        }
        else { // if not selected (clicked)
            //set the foreground and background when online or offline
            String[] id = ((String) value).split("/");
            setBackground(list.getBackground());
            if(id[2].equals("true"))
                // when online
                setForeground(Color.black);
            else
                // when offline
                setForeground(Color.gray);
        }
        return this;
    }
}
