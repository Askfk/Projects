package Client.ClientThreads;

import Client.ClientGUI.Frames.ClientInitialFrame;
import Client.ClientGUI.JButtonListModel.JButtonListModel;
import Client.ServerInfos;

import Tools.ClassTools.Users.User;
import Tools.SocketInstructions;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.swing.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;
import java.util.Timer;

/**
 * This is the main thread of client server that contains all the functions that account-related.
 * The commons of these functions is that they will never happen simultaneously, so we can put them into one thread.
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class ClientServer {

    private SSLSocket serverFunc;

    public ObjectOutputStream ObjectToServer;
    public ObjectInputStream ObjectFromServer;
    public DataOutputStream DataToServer;
    public DataInputStream DataFromServer;

    private Timer timer;
    private TimerTask chatTimerTask;

    private String LoginInfos;
    private User currentUser;
    private boolean isOccupied = false;

    // Array List to store the friends and group chat Friends after the first buffer exchange
    public Map<String, User> friends;
    public Map<String, ArrayList<User>> groupMembers;


    public JTextArea messageArea;
    public JList<String> friendsList;
    public JButtonListModel friendsListModel;
    public JButton groupMemberDetails;

    /**
     * In this construction that initials all the SSLSocket connections with server and the DB connection with the database.
     * and all the data streams.
     */
    public ClientServer(){
        try {
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
            // Initialize TrustManagerFactory Obj
            tmf.init(tks);
            // Initialize SSLContext Obj
            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            serverFunc = (SSLSocket) ctx.getSocketFactory().createSocket(ServerInfos.serverName, ServerInfos.funcPort);
            tell("Successfully connect to ChatServer port: " + ServerInfos.funcPort);

            ObjectToServer = new ObjectOutputStream(serverFunc.getOutputStream());
            ObjectFromServer = new ObjectInputStream(serverFunc.getInputStream());
            DataToServer = new DataOutputStream(serverFunc.getOutputStream());
            DataFromServer = new DataInputStream(serverFunc.getInputStream());

            // TODO: Initialize all the basic buffers when initializing the client thread for info security

            // <UserID, User>
            this.friends = new HashMap<String, User>();
            // <GroupID + / + GroupName>
            this.groupMembers = new HashMap<>();

            new ClientInitialFrame(this);

        } catch (UnknownHostException e) {
            error("Error code: -7, Unknown Server host: " + ServerInfos.serverName);
        } catch (IOException e) {
            error("Error code: -8, Couldn't get I/O for the connection to " + ServerInfos.serverName);
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
     * This function send given request to server and receive the server ready status.
     * @param request the given request
     * @return server's ready status
     */
    boolean sendRequest(String request){
        try{
            DataToServer.writeUTF(request);
            DataToServer.flush();

            boolean status = DataFromServer.readBoolean();
            return status;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This function executes login operation.
     * @param infos consist of "username/password"
     * @return login operation result status
     */
    public boolean login(String infos){
        this.LoginInfos = infos;
        System.out.println();
        tell("Now you are starting to login...");
        System.out.println();

        // TODO: Make multi-processes limitations to avoid double account online on the same devices
        // if current client server is not occupied, which means double login is not allowed.
        if(!isOccupied()){
            try {
                //System.out.println("1");
                boolean sendStatus = sendRequest(SocketInstructions.REQUEST_LOGIN);
                //System.out.println("2");
                if(sendStatus){
                    DataToServer.writeUTF(infos);
                    DataToServer.flush();
                    // if login successfully, server will send all the user infos of current user.
                    currentUser = (User) ObjectFromServer.readObject();
                    if(currentUser == null){
                        error("Login failed due to Server Error or incorrect username/pw.");
                        return false;
                    }
                    else {
                        tell("Login successfully.");
                        setOccupied(true);

                        // if login successfully, start the chat thread.
                        chatTimerTask = new ClientChatTimerTask(this);
                        timer = new Timer();
                        timer.schedule(chatTimerTask, 0, 500);

                        tell("The current client " + currentUser + " login status is " + currentUser.isLogin());
                        return currentUser.isLogin();
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                setOccupied(false);
                error("The login status is false due to Client IOException.");
                return false;
            }
        }
        else
            error("The current client is occupied.");
        return true;

    }

    /**
     * This function executes singUp operation.
     * @param infos sign up infos seperated by "/", "username/password/email"
     * @return signUp status.
     */
    public boolean signUp(String infos){ // When sign up, need to clarify the type of user.
        String[] info = infos.split("/");
        try{
            boolean sendStatus = sendRequest(SocketInstructions.REQUEST_SIGNUP);

            if(sendStatus){
                User request = new User(info[0], info[1], info[2]);
                ObjectToServer.writeObject(request);
                ObjectToServer.flush();
                tell("Sending user " + request + " SignUp request to server...");
            }

            Boolean signUpStatus = DataFromServer.readBoolean();

            if(signUpStatus){
                tell("Sign up successfully.");
            }
            else
                error("Sign up failed due to Server Exception.");
            return signUpStatus;
        } catch (IOException e) {
            e.printStackTrace();
            error("Sign up failed due to Client IOException.");
            return false;
        }

    }

    /**
     * This function executes logout operation.
     * @return
     */
    boolean logout(){
        boolean logoutStatus = sendRequest(SocketInstructions.REQUEST_END_CONNECTION);
        if(logoutStatus){
            tell("Logout successfully.");
        }
        else
            error("Logout failed due to the Server Exception.");
        return logoutStatus;
    }

    /**
     * This function executes change online status operation.
     * This func can help to realize that make other friends see current user offline while current user keep online.
     * @param onlineStatus new online status, true is online
     * @return change online status result.
     */
    public boolean changeOnlineStatus(boolean onlineStatus) {
        boolean status = sendRequest(SocketInstructions.CHANGE_ONLINE_STATUS);
        try {
            if(status){
                DataToServer.writeBoolean(onlineStatus);
                DataToServer.flush();

                boolean changeStatus = DataFromServer.readBoolean();
                return changeStatus;
            }
            else
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This func helps to change the current user's personal infos.
     * @param user current user with new infos.
     * @return change infos status.
     */
    public boolean changeInfos(User user){
        try {
            boolean conStatus = sendRequest(SocketInstructions.REQUEST_CHANGE_INFO);
            if(conStatus){
                ObjectToServer.writeObject(user);
                ObjectToServer.flush();
                boolean changeStatus = DataFromServer.readBoolean();
                if(changeStatus){
                    tell("Change personal information successfully");
                    this.currentUser = user;
                    return true;
                }
                else {
                    error("Change personal information failed due to the Server IOException of the SQLException");
                    return false;
                }
            }
            else {
                error("Change info failed due to the Socket Error.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This func help to create group chats.
     * @param groupMembersId group members' Id, seperated by "/"
     * @return create group chat status.
     */
    public boolean createGroupChat(String groupMembersId){
        try {
            boolean status = sendRequest(SocketInstructions.REQUEST_CREATE_GROUP_CHAT);
            if(status){
                DataToServer.writeUTF(groupMembersId);
                DataToServer.flush();

                boolean createStatus = DataFromServer.readBoolean();
                return createStatus;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This func helps to get user info from server by UID
     */
    public User getUserInfoById(String id){
        try{
            boolean status = sendRequest(SocketInstructions.REQUEST_GET_USER_INFO_BY_ID);
            if(status){
                DataToServer.writeUTF(id);
                DataToServer.flush();
                User user = (User) ObjectFromServer.readObject();
                return user;
            }
            else
                return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This func helps to get user info from server by email
     */
    public User getUserInfoByEmail(String email){
        try{
            boolean status = sendRequest(SocketInstructions.REQUEST_GET_USER_INFO_BY_EMAIL);
            if(status){
                DataToServer.writeUTF(email);
                DataToServer.flush();
                User user = (User) ObjectFromServer.readObject();
                return user;
            }
            else
                return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This func helps current user to match a stranger and chat with stranger.
     * Stranger will on be the one in different gender.
     * @return join match list results.
     */
    public boolean match(){
        boolean status = sendRequest(SocketInstructions.REQUEST_MATCH);
        if(status){
            tell("Join match list successfully");
        }
        else
            tell("Join match list failed.");
        return status;
    }

    /**
     * get the current server connection
     * @return current server connection
     */
    public Socket getServer() {
        return serverFunc;
    }

    /**
     * Set current user
     * @param user current user
     */
    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    /**
     * get current user
     * @return current user
     */
    public User getCurrentUser(){
        return this.currentUser;
    }

    /**
     * get the current user's login username/password
     * @return current user's login username/password
     */
    public String getLoginInfos(){
        return this.LoginInfos;
    }

    /**
     * return whether client server main thread is occupied
     * @return whether is occupied.
     */
    public boolean isOccupied() {
        return this.isOccupied;
    }

    /**
     * set client server main thread occupied status
     * @param occupied new client server main thread occupied status
     */
    public void setOccupied(boolean occupied) {
        currentUser.setLogin(occupied);
        this.isOccupied = occupied;
    }

    private void error(String s) {
        System.err.println(s);
        //System.exit(0);
    }

    private void tell(String message) {
        System.out.println(message);
    }

    /**
     * Close all the connections, close all the data streams, close all the threads, logout.
     */
    public void finalize () {
        try {
            // Let server know we are done.
            // Our convention is to send "0" to indicate this.

            logout();
            //timer.cancel();
            // Close the streams:

            ObjectToServer.close();
            ObjectFromServer.close();
            DataToServer.close();
            DataFromServer.close();

            timer.cancel();

            // Close the connection:

            serverFunc.close();
            this.setOccupied(false);
            System.exit(0);
        }
        catch (IOException e) {
            error("Something went wrong ending the client server.");
        }
    }

}
