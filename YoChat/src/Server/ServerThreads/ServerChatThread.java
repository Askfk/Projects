package Server.ServerThreads;

import Client.Utils.GetCurrentTime;
import Server.Buffers.FriendsReplyBuffer;
import Server.ServerDB.JDBConnection;
import Tools.ClassTools.Buffers.BufferPack;
import Server.Buffers.FriendRequestsBuffer;
import Server.Buffers.MessageBuffer;
import Tools.ClassTools.Infors.Info;
import Tools.ClassTools.Users.User;
import Tools.SocketInstructions;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This is the chat thread of server, deal with the buffer exchange with client
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class ServerChatThread extends Thread{

    private Socket client;
    private JDBConnection JDBConnection;

    private ObjectOutputStream ObjectToClient;
    private ObjectInputStream ObjectFromClient;
    private DataOutputStream DataToClient;
    private DataInputStream DataFromClient;

    public User serveUser;

    /**
     *
     * @param client Chat SSLSocket with client
     */
    public ServerChatThread(Socket client){
        this.client = client;
        this.JDBConnection = new JDBConnection();
//        System.out.println(GetCurrentTime.getCurrentTime());
    }

    public void run(){
        try {
            ObjectToClient = new ObjectOutputStream(client.getOutputStream());
            ObjectFromClient = new ObjectInputStream(client.getInputStream());
            DataToClient = new DataOutputStream(client.getOutputStream());
            DataFromClient = new DataInputStream(client.getInputStream());

            // serverUser can be initialize by the ip from DB directly.
            serveUser = JDBConnection.getUserIP(client.getInetAddress().toString().substring(1));
//            System.out.println(serveUser.getUID());
            receiveInstructors();
            sendBuffer();
            receiveInstructors();
            receiveBuffer();

//            String sign;
//
//            while(!(sign = receiveInstructors()).equals(SocketInstructions.REQUEST_ENDCONNECTION)){
//                switch (sign){
//                    case SocketInstructions.REQUEST_GETBUFFER: sendBuffer(); break;
//                    case SocketInstructions.REQUEST_SENDBUFFER: receiveBuffer(); break;
//                }
//            }

            finish();

        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    /**
     * Deal with the requests from client
     * @return
     * @throws IOException
     */
    String receiveInstructors() throws IOException {
        tell("Now receiving the request from " + client.getInetAddress());
        try{
            String sign = DataFromClient.readUTF();
//            System.out.println("------" + sign);
            DataToClient.writeBoolean(true);
            DataToClient.flush();
            return sign;
        } catch (IOException e) {
            DataToClient.writeBoolean(false);
            DataToClient.flush();
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * send buffer to client
     */
    void sendBuffer(){
        try{

            ArrayList<Info> infoBufferToClient = MessageBuffer.clientMessageBuffer.get(serveUser.getUID());
            ArrayList<User> friendsRequestBuffer = FriendRequestsBuffer.friendRequestBuffer.get(serveUser.getUID());
            ArrayList<String> friendsReplyBuffer = FriendsReplyBuffer.friendsReplyBuffer.get(serveUser.getUID());
            ArrayList<User> friendsStatus = JDBConnection.getFriendsStatus(serveUser.getUID());
            Map<String, ArrayList<User>> groupStatus = JDBConnection.getGroupStatus(serveUser.getUID());

            BufferPack bufferPack = new BufferPack(infoBufferToClient, friendsRequestBuffer, friendsReplyBuffer, friendsStatus, groupStatus);

            ObjectToClient.writeObject(bufferPack);
            ObjectToClient.flush();

            boolean sendStatus = DataFromClient.readBoolean();
            if(sendStatus){
                FriendRequestsBuffer.friendRequestBuffer.put(serveUser.getUID(), new ArrayList<>());
                FriendsReplyBuffer.friendsReplyBuffer.put(serveUser.getUID(), new ArrayList<String>());
                MessageBuffer.clientMessageBuffer.put(serveUser.getUID(), new ArrayList<Info>());

                tell("Write Buffer to Client " + serveUser.getNickName()+ " SUCCESSFULLY.");
            }
            else
                tell("Write Buffer to Client " + serveUser.getNickName()+ " FAILED.");

            DataToClient.writeUTF(SocketInstructions.READY);
            DataToClient.flush();

        } catch (IOException e) {
            error("IOException when send buffer");
            //e.printStackTrace();
        }
        catch (NullPointerException e){
            //e.printStackTrace();
        }
    }

    /**
     * receive buffer from client
     */
    void receiveBuffer(){
        try {
//            System.out.println("Here1");

            BufferPack bufferPack = (BufferPack) ObjectFromClient.readObject();
//            System.out.println("Here2");
            // Store info buffer
            for(Info info : bufferPack.infoBuffer){
                System.out.println(info.toString());
                JDBConnection.writeInfo(info);
                if(info.isPrivate){
                    MessageBuffer.addClientMessageBuffer(info);
                }
                else {
                    String toIds = JDBConnection.getGroupMembers(info.toID);
//                    System.out.println(toIds);
//                    System.out.println(serveUser.getUID());
                    toIds = toIds.replace(serveUser.getUID(), "");
//                    System.out.println(toIds);
                    MessageBuffer.addGroupMessageBuffer(toIds, info);
                }
            }

            // Store request buffer
            for(User user : bufferPack.friendsRequestBuffer){
                tell(user.toString());
                FriendRequestsBuffer.addFriendRequestsBuffer(user.getUID(), serveUser);
            }

            // Store reply buffer
            for(String s : bufferPack.friendsReplyBuffer){
                /**
                 * Reply should also add the friends to each user's DB arrow.
                 */
                // s is reply + / + toUID
                tell(s);
                String[] reply = s.split("/");
                if(reply[0].equals("accept")){
                    JDBConnection.addFriends(reply[1], serveUser.getUID());
                    FriendsReplyBuffer.addFriendsReplyBuffer(reply[1], reply[0] + "/" + serveUser.getUID());
                    FriendsReplyBuffer.addFriendsReplyBuffer(serveUser.getUID(), s);
                }

                FriendsReplyBuffer.addFriendsReplyBuffer(reply[1], reply[0] + "/" + serveUser.getUID());
            }

            //System.out.println("Here2");

//            System.out.println("Here4");
            DataToClient.writeBoolean(true);
            DataToClient.flush();

            tell("Receive Buffer from Client Successfully.");

        } catch (IOException e) {
            error("IOException when receive buffer from client");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            error("ClassNotFoundException when receive buffer from client");
            e.printStackTrace();
        }
    }

    /**
     * finish this Thread
     * @throws IOException
     */
    void finish() throws IOException {
        tell("Closing the connection with Client: " + client);
        client.close();
        ObjectToClient.close();
        ObjectFromClient.close();
        DataToClient.close();
        DataFromClient.close();
        JDBConnection.finish();
        tell("Connection closed.");
    }

    void tell(String s){
//        System.out.println(s);
    }

    void error(String s){
        System.err.println(s);
    }


}
