package Client.ClientThreads;

import Client.Buffers.FriendsReplyBuffer;
import Client.Buffers.FriendsRequestBuffer;
import Client.Buffers.MessageBuffer;
import Client.ClientGUI.CellRenderers.CellRendererButton;
import Client.ServerInfos;
import Client.Utils.GetCurrentTime;
import Client.Utils.RecordWriter;
import Tools.ClassTools.Buffers.BufferPack;
import Tools.ClassTools.Infors.Info;
import Tools.ClassTools.Users.User;
import Tools.SocketInstructions;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.TreeSet;

/**
 * This thread is mainly working for chat.
 * The chat protocol is ask for data buffer exchange every certain time (determined by clientserver thread)
 * The sending data buffer contains unsettled chat messages, unsettled friend requests, unsettled friend replies.
 *
 * This is a very important part of the client server, which contains all the chat-related operations.
 *
 * The running flow is : create new connection with server, initial all the data steams, receive buffer, check whether
 * server is ready to receive buffer, then send buffer, close all the connections and data steams.
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class ClientChatTimerTask extends TimerTask {

    protected User currentUser;

    protected boolean receiveStatus = false;

    protected DataInputStream DataFromServer;
    protected DataOutputStream DataToServer;
    protected ObjectInputStream ObjectFromServer;
    protected ObjectOutputStream ObjectToServer;

    private SSLSocket server;
    private ClientServer clientServer;

    private boolean changed = false;

    // chat messages that need to be stored in the history file.
    private ArrayList<Info> messages;

    /**
     *
     * @param clientServer The main thread of client server
     */
    public ClientChatTimerTask(ClientServer clientServer){
        this.clientServer = clientServer;
        this.currentUser = clientServer.getCurrentUser();
        this.messages = new ArrayList<Info>();
    }

    /**
     * Initial the SSLSocket connection with server and all the data streams.
     */
    void setUpConnection(){
        try{
            // To get the SSLContext Obj
            SSLContext ctx = SSLContext.getInstance("SSL");
            // JSSE keyManagerFactory Obj
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            // TrustManagerFactory Obj
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            // Storage of keys and certificates
            KeyStore ks = KeyStore.getInstance("JKS");
            KeyStore tks = KeyStore.getInstance("JKS");
            // Load Keystore
            ks.load(new FileInputStream("src/Client/res/kclient.keystore"), ServerInfos.CLIENT_KEY_STORE_PASSWORD.toCharArray());
            tks.load(new FileInputStream("src/Client/res/tclient.keystore"), ServerInfos.CLIENT_TRUST_KEY_STORE_PASSWORD.toCharArray());
            // Initialize KeyManagerFactory Obj
            kmf.init(ks, ServerInfos.CLIENT_KEY_STORE_PASSWORD.toCharArray());
            // Initialize TrustManagerFactory Object
            tmf.init(tks);
            // Initialize SSLContext Obj
            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            server = (SSLSocket) ctx.getSocketFactory().createSocket(ServerInfos.serverName, ServerInfos.chatPort);
            tell("Successfully connect to ChatServer port: " + ServerInfos.chatPort);

            DataFromServer = new DataInputStream(server.getInputStream());
            DataToServer = new DataOutputStream(server.getOutputStream());
            ObjectToServer = new ObjectOutputStream(server.getOutputStream());
            ObjectFromServer = new ObjectInputStream(server.getInputStream());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close all the connections and data streams
     */
    void tearDownConnection(){
        try {
            DataToServer.close();
            DataFromServer.close();
            ObjectFromServer.close();
            ObjectToServer.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Main flow of the thread
     */
    @Override
    public void run() {
        // TODO: Make this thread only initialize the connection with server once during its whole life
        try {
            setUpConnection();
            receiveBuffer();
            if(DataFromServer.readUTF().equals(SocketInstructions.READY)){
                sendBuffer();
            }
            else
                error("Socket Error after receiving buffer.");

            RecordWriter.writeMessages(messages);
            messages = new ArrayList<Info>();
            tearDownConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send data buffer to server.
     */
    private void sendBuffer(){
        try {
            tell("Now begin sending");
            // send want to send buffer operation request to server
            DataToServer.writeUTF(SocketInstructions.REQUEST_SEND_BUFFER);
            DataToServer.flush();
            //System.out.println("Here1");
            receiveStatus = DataFromServer.readBoolean();
            //System.out.println("Here2");

            //if server receives the request successfully
            if(receiveStatus){
                BufferPack bufferPack = new BufferPack(MessageBuffer.serverMessageBuffer, FriendsRequestBuffer.friendsRequestBuffer,
                        FriendsReplyBuffer.friendsReplyBuffer, null, null);

                ObjectToServer.writeObject(bufferPack);
                ObjectToServer.flush();

                if(MessageBuffer.serverMessageBuffer != null)
                    messages.addAll(MessageBuffer.serverMessageBuffer);

                FriendsRequestBuffer.friendsRequestBuffer = new ArrayList<User>();
                FriendsReplyBuffer.friendsReplyBuffer = new ArrayList<String>();
                MessageBuffer.serverMessageBuffer = new ArrayList<Info>();
                tell("Send Buffer to Server Successfully");
            }
            else
                error("Socket Error when Send buffer");

        } catch (IOException e) {
            error("IOException when send buffer to server");
            e.printStackTrace();
        }
    }

    /**
     * Receive data buffer from server
     * @throws IOException
     */
    private void receiveBuffer() throws IOException {
        try{
            tell("Now start receiving");
            // Send 'want to get buffer operations request' to server
            DataToServer.writeUTF(SocketInstructions.REQUEST_GET_BUFFER);
            DataToServer.flush();

            receiveStatus = DataFromServer.readBoolean();
//            System.out.println(receiveStatus);

            // if server receives the request successfully
            if(receiveStatus){
                BufferPack bufferPack = (BufferPack) ObjectFromServer.readObject();
//                System.out.println("1");

                tell("Receive Buffer from Server Successfully");
                // Below should be the process of Buffer from server.

                // Deal with friends status
                if(bufferPack.friendsStatus != null){
                    for (User user : bufferPack.friendsStatus){
//                        System.out.println(user);
                        if(!clientServer.friends.containsKey(user.getUID())){ // if new friend or just logged in

                            changed = true; // Means the friends list in the GUI need to be refreshed
                            clientServer.friends.put(user.getUID(), user);
                            if(user.getMatched()){
                                clientServer.friendsListModel.addElement(user.getNickName() + "[match]/" + user.getUID() + "/" + user.isLogin());
                            }
                            else
                                clientServer.friendsListModel.addElement(user.getNickName() + "/" + user.getUID() + "/" + user.isLogin());

                        }
                        else { // Check whether if the nickname or login status is changed.
                            boolean oldStatue = clientServer.friends.get(user.getUID()).isLogin();
                            boolean newStatue = user.isLogin();
                            if(oldStatue != newStatue){
                                changed = true;
                                clientServer.friends.put(user.getUID(), user);
                                if(user.getMatched()){
                                    clientServer.friendsListModel.changeButtonStatus(user.getNickName() + "[match]/" + user.getUID() + "/" + user.isLogin());
                                }
                                else {
                                    clientServer.friendsListModel.changeButtonStatus(user.getNickName() + "/" + user.getUID() + "/" + user.isLogin());
                                }
                            }
                            if(!changed){ // if nickname or login status is not changed, check whether other information is changed.
                                String oldNickName = clientServer.friends.get(user.getUID()).getNickName();
                                String newNickName = user.getNickName();
                                if(!oldNickName.equals(newNickName)){
                                    changed = true;
                                    clientServer.friends.put(user.getUID(), user);
                                    if(user.getMatched()){
                                        clientServer.friendsListModel.changeButtonStatus(user.getNickName() + "[match]/" + user.getUID() + "/" + user.isLogin());
                                    }
                                    else {
                                        clientServer.friendsListModel.changeButtonStatus(user.getNickName() + "/" + user.getUID() + "/" + user.isLogin());
                                    }
                                }
                                // TODO: Below can be the detect of gender/age/nation
                                //       and don't need to refresh the friends list of GUI,
                                //       and check only if the nickname is not changed.
                                if(!changed){

                                }
                            }
                        }
                    }
                }

                if(bufferPack.groupsStatus != null){
                    for(String gid : bufferPack.groupsStatus.keySet()){
                        if(!clientServer.groupMembers.containsKey(gid)){
                            clientServer.groupMembers.put(gid, bufferPack.groupsStatus.get(gid));
                            String[] groupInfo = gid.split("/");
                            clientServer.friendsListModel.addElement(groupInfo[1] + "(Group)/" + groupInfo[0] + "/" + true + "/" + "group");
                            changed = true;
                        }
                    }
                }

                // if some infos of the friends or groups are changed, refresh the friends list of GUI.
                if(changed){
                    System.out.println("Changed");
                    clientServer.friendsList.setModel(clientServer.friendsListModel);
                    clientServer.friendsList.setCellRenderer(new CellRendererButton(clientServer));
                    changed = false;
                }

                // Deal with infos
                if(bufferPack.infoBuffer != null){
                    messages.addAll(bufferPack.infoBuffer);
                    for(Info info : bufferPack.infoBuffer){
                        if(info.isPrivate){ // if this info belongs to private chat
                            if(clientServer.friendsList.getSelectedValue()!=null && info.fromID.equals(clientServer.friendsList.getSelectedValue().split("/")[1])){
                                String nickName = clientServer.friends.get(info.fromID).getNickName();
                                String email = clientServer.friends.get(info.fromID).getEmail();
                                String message = "[" + info.time + "  " + nickName + "<" + email + ">]" + "\n" + info.message + "\n";
                                clientServer.messageArea.append(message);
                            }
                        }
                        else {
//                            System.out.println(info);
//                            System.out.println(clientServer.friendsList.getSelectedValue());
                            // if currently there is a friend or group is selected and is chatting now, add the corresponding info to the message area.
                            if(clientServer.friendsList.getSelectedValue() != null && info.toID.equals(clientServer.friendsList.getSelectedValue().split("/")[1])){
                                String gid = info.toID + "/" + clientServer.friendsList.getSelectedValue().split("/")[0];
                                gid = gid.replace("(Group)", "");
                                ArrayList<User> members = clientServer.groupMembers.get(gid);
                                String nickName = null;
                                String email = null;
                                for(User user : members){
                                    if(user.getUID().equals(info.fromID)){
                                        nickName = user.getNickName();
                                        email = user.getEmail();
                                    }
                                }
                                String message = "[" + info.time + "  " + nickName + "<" + email + ">]" + "\n" + info.message + "\n";
                                clientServer.messageArea.append(message);
                            }
                        }
                    }
                }

                // Deal with request
                if(bufferPack.friendsRequestBuffer != null){
                    if(bufferPack.friendsRequestBuffer.size() > 0)
                        new FriendRequestThread(bufferPack.friendsRequestBuffer).start();
                }

                // Deal with reply
                if(bufferPack.friendsReplyBuffer != null){
                    for (String s : bufferPack.friendsReplyBuffer){
                        String[] info = s.split("/");
                        Info firstMessage = new Info(info[1], currentUser.getUID(), "Now we are friends, let's begin out chat!", GetCurrentTime.getCurrentTime().toString(), true);
                        messages.add(firstMessage);

                        // TODO: Make a new thread to show the notification that these two people are friends now

                    }
                }
            }

            else
                tell("Receive Buffer from Server Failed");
            DataToServer.writeBoolean(receiveStatus);
            DataToServer.flush();

        } catch (IOException e) {
            DataToServer.writeBoolean(false);
            DataToServer.flush();
            error("IOException when receive buffer from server");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            DataToServer.writeBoolean(false);
            DataToServer.flush();
            error("ClassNotFoundException when receive buffer from buffer");
            e.printStackTrace();
        }
    }

    private void error(String s) {
        System.err.println(s);
//        System.exit(0);
    }

    private void tell(String message) {
//        System.out.println(message);
    }
}
