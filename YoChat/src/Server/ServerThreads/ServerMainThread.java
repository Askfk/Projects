package Server.ServerThreads;

import Server.Buffers.MatchList;
import Server.Buffers.MessageBuffer;
import Server.ServerDB.JDBConnection;
import Tools.ClassTools.Infors.Info;
import Tools.ClassTools.Users.User;
import Tools.SocketInstructions;
import jdk.nashorn.internal.scripts.JD;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

/**
 * This thread is the main thread deal with the account-related request from client
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class ServerMainThread extends Thread {
    private Socket client;
    private ObjectOutputStream ObjectToClient;
    private ObjectInputStream ObjectFromClient;
    private DataOutputStream DataToClient;
    private DataInputStream DataFromClient;


    public JDBConnection JDBConnection;
    public User serveUser;
//    public Map<String, ArrayList<User>> groups

    /**
     *
     * @param client main SSLSocket with client
     */
    public ServerMainThread(Socket client){
        this.client = client;
        this.JDBConnection = new JDBConnection();
    }

    /**
     * run()
     */
    public void run(){
        try{
            ObjectToClient = new ObjectOutputStream(client.getOutputStream());
            ObjectFromClient = new ObjectInputStream(client.getInputStream());
            DataToClient = new DataOutputStream(client.getOutputStream());
            DataFromClient = new DataInputStream(client.getInputStream());

            String sign;

            while(!(sign = receiveInstructors()).equals(SocketInstructions.REQUEST_END_CONNECTION)){
                switch (sign){
                    case SocketInstructions.REQUEST_LOGIN: login(); break;
                    case SocketInstructions.REQUEST_SIGNUP: signUp(); break;
                    case SocketInstructions.REQUEST_CANCELLATION_ACC: break;
                    case SocketInstructions.CHANGE_ONLINE_STATUS: changeOnlineStatus(); break;
                    case SocketInstructions.REQUEST_CHANGE_INFO: changeInfo(); break;
                    case SocketInstructions.REQUEST_CREATE_GROUP_CHAT: createGroupChat(); break;
                    case SocketInstructions.REQUEST_GET_USER_INFO_BY_ID: getUserInfobyID(); break;
                    case SocketInstructions.REQUEST_GET_USER_INFO_BY_EMAIL: getUserInfobyEmail(); break;
                    case SocketInstructions.REQUEST_MATCH: match(); break;
                    default: System.err.println("Invalid request instructor from Client: " + client + " as " + sign); break;
                }
            }

            finish();

        } catch (IOException e) {
            e.printStackTrace();
            try {
                finish();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Deal with the request from
     * @return
     * @throws IOException
     */
    String receiveInstructors() throws IOException {
        //System.out.println("Now receiving the request from " + client.getInetAddress());
        try{
            String sign = DataFromClient.readUTF();
            //System.out.println(sign);
            DataToClient.writeBoolean(true);
            DataToClient.flush();
            return sign;
        } catch (IOException e) {
            try {
                DataToClient.writeBoolean(false);
                DataToClient.flush();
            } catch (IOException ex) {
                //ex.printStackTrace();
                finish();
            }

            //e.printStackTrace();
            return null;
        }
    }

    /**
     * SigUp()
     */
    void signUp() {
        //System.out.println("Now sign up");
        try {
            User user = (User) ObjectFromClient.readObject();
            boolean isCreated = JDBConnection.addUser(user);
            DataToClient.writeBoolean(isCreated);
            DataToClient.flush();
        } catch (Exception e) {
//            try {
//                finish();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
            e.printStackTrace();
        }
    }

    /**
     * login()
     */
    void login(){
        //System.out.println("Now login");
        try{
            String info = DataFromClient.readUTF();
            String[] infos = info.split("/");
            //System.out.println(infos);
            User user = JDBConnection.Login(infos, client.getInetAddress());
            if(user == null){
                ObjectToClient.writeObject(null);
                ObjectToClient.flush();
            }
            else{
                serveUser = user;
                ObjectToClient.writeObject(user);
                ObjectToClient.flush();

                if(!MessageBuffer.clientMessageBuffer.containsKey(user.getUID())){
                    MessageBuffer.clientMessageBuffer.put(user.getUID(), new ArrayList<Info>());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            //finish();
        }
    }

    /**
     * logout()
     */
    void logout(){
        JDBConnection.logout(serveUser.getUID());
    }

    /**
     * ChangeOnlienStatus()
     * @throws IOException
     */
    void changeOnlineStatus() throws IOException {
        try {
            boolean onlineStatus = DataFromClient.readBoolean();

            boolean changeStatus = JDBConnection.changeOnlineStatus(onlineStatus, serveUser.getUID());
            DataToClient.writeBoolean(changeStatus);
            DataToClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
            DataToClient.writeBoolean(false);
            DataToClient.flush();
        }
    }

    /**
     * ChangeInfo()
     * @throws IOException
     */
    void changeInfo() throws IOException {
        try {
            //System.out.println("here 1 server");
            User user = (User) ObjectFromClient.readObject();
            boolean changeStatus = JDBConnection.changeInfo(user);
            if(changeStatus){
                serveUser = user;
            }
            DataToClient.writeBoolean(changeStatus);
            DataToClient.flush();
            //System.out.println("here 2 server");
        } catch (IOException e) {
            DataToClient.writeBoolean(false);
            DataToClient.flush();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * CreateGroupChat()
     * @throws IOException
     */
    void createGroupChat() throws IOException {
        try {
            String groupMembersId = DataFromClient.readUTF();
            boolean createStatus = JDBConnection.createGroupChat(groupMembersId);
            DataToClient.writeBoolean(createStatus);
            DataToClient.flush();

        } catch (IOException e) {
            DataToClient.writeBoolean(false);
            DataToClient.flush();
            e.printStackTrace();
        }
    }

    /**
     * GetUserInfoByID()
     * @throws IOException
     */
    void getUserInfobyID() throws IOException {
        try{
            String id = DataFromClient.readUTF();
            User user = JDBConnection.getUserById(id);
            ObjectToClient.writeObject(user);
            ObjectToClient.flush();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("IOException Error when get User Info");
            ObjectToClient.writeObject(null);
            ObjectToClient.flush();
        }
    }

    /**
     * GetUserInfoByEmail()
     * @throws IOException
     */
    void getUserInfobyEmail() throws IOException {
        try{
            String email = DataFromClient.readUTF();
            User user = JDBConnection.getUserByEmail(email);
            ObjectToClient.writeObject(user);
            ObjectToClient.flush();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("IOException Error when get User Info");
            ObjectToClient.writeObject(null);
            ObjectToClient.flush();
        }
    }

    /**
     * Match()
     */
    void match(){
        System.out.println("Now matching...");
        String gender = serveUser.getGender();
        if(gender.equals("Male")){
            MatchList.addMaleList(serveUser);
        }
        else
            MatchList.addFemaleList(serveUser);
    }

//    void addFriend(){
//        try{
//            String id = DataFromClient.readUTF();
//            User user = mainDB.getUser(id);
//            ObjectToClient.writeObject(user);
//            ObjectToClient.flush();
//
//            if(FriendRequestsBuffer.friendRequestBuffer.containsKey(id)){
//                Set<User> users = FriendRequestsBuffer.friendRequestBuffer.get(id);
//                users.add(serveUser);
//                FriendRequestsBuffer.friendRequestBuffer.put(id, users);
//            }
//            else{
//                Set<User> users = new TreeSet<User>();
//                users.add(serveUser);
//                FriendRequestsBuffer.friendRequestBuffer.put(id, users);
//            }
//
//            System.out.println("addFriendBuffer done");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Finish the thread.
     * @throws IOException
     */
    void finish() throws IOException {
        System.out.println("Closing the connection with Client: " + client);
        logout();
        client.close();
        ObjectToClient.close();
        ObjectFromClient.close();
        DataToClient.close();
        DataFromClient.close();
        JDBConnection.finish();
        System.out.println("Connection closed.");
    }

}
