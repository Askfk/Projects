package Server.Start;

import Server.ServerDB.JDBConnection;
import Server.ServerDB.SSHConnect;
import Server.ServerThreads.ServerChatThread;
import Server.PortInfos;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Start the chat server
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class ChatServer {

    private SSLServerSocket serverSocketChat = null;

    public Map<String, JDBConnection> mainDBServer = new HashMap<String, JDBConnection>();

    /**
     * initialization()
     */
    void initialization(){
        ExecutorService tasks = Executors.newFixedThreadPool(100);
        try{
            SSLContext ctx = SSLContext.getInstance("SSL");

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory tmf =  TrustManagerFactory.getInstance("SunX509");

            KeyStore ks = KeyStore.getInstance("JKS");
            KeyStore tks = KeyStore.getInstance("JKS");

            ks.load(new FileInputStream("src/Server/keys/kserver.keystore"), PortInfos.SERVER_KEY_STORE_PASSWORD.toCharArray());
            tks.load(new FileInputStream("src/Server/keys/tserver.keystore"), PortInfos.SERVER_TRUST_KEY_STORE_PASSWORD.toCharArray());

            kmf.init(ks, PortInfos.SERVER_KEY_STORE_PASSWORD.toCharArray());
            tmf.init(tks);

            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            serverSocketChat = (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(PortInfos.chatPort);
            serverSocketChat.setNeedClientAuth(true);
            System.out.println("Chat Server started");
        } catch (IOException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
            System.err.println("Couldn't listen on port: " + PortInfos.chatPort);
            System.exit(-1);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Listen to the socket, accepting connections from new clients,
        // and running a new thread to serve each new client:
        try {
            while (true) {
                Socket clientSocketChat = serverSocketChat.accept();

//                String address = clientSocketChat.getInetAddress().toString();
//                MainDB mainDB;// = new MainDB();
//                if(mainDBServer.containsKey(address)){
//                    if(mainDBServer.get(address) != null){
//                        mainDB = mainDBServer.get(address);
//                    }
//                    else {
//                        mainDB = new MainDB();
//                        mainDBServer.put(address, mainDB);
//                    }
//                }
//                else {
//                    mainDB = new MainDB();
//                    mainDBServer.put(address, mainDB);
//                }
//
//                System.out.println("Client connected from " + clientSocketChat.getInetAddress());

                ServerChatThread chatThread = new ServerChatThread(clientSocketChat);
                tasks.execute(chatThread);
            }
        }
        catch (Exception e) {
            try {
                serverSocketChat.close();
            }
            catch (IOException io) {
                System.err.println("Couldn't close server socket" + io.getMessage());
            }
        }
    }

    public static void main(String[] args){
        ChatServer s = new ChatServer();
        s.initialization();
    }
}
