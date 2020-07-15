package Server.Start;

import Server.PortInfos;
import Server.ServerDB.JDBConnection;
import Server.ServerDB.SSHConnect;
import Server.ServerThreads.MatchTimerTask;
import Server.ServerThreads.ServerMainThread;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Start the function server
 *
 * @author Yiming Li
 * @version 2020-03
 */

public class FuncServer {

    private SSLServerSocket serverSocketFunc = null;

    /**
     * initialization()
     */
    void initialization(){

        ExecutorService tasks = Executors.newFixedThreadPool(100);
        SSHConnect.configVPN();
        try {
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

            serverSocketFunc = (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(PortInfos.funcPort);
            serverSocketFunc.setNeedClientAuth(true);

            System.out.println("Function Server started");

            JDBConnection JDBConnection = new JDBConnection();
            TimerTask timerTask = new MatchTimerTask(JDBConnection);
            Timer timer = new Timer();
            System.out.println("Match service is running...");
            timer.schedule(timerTask, 10, 30);

        } catch (IOException | KeyStoreException | KeyManagementException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException e) {
            System.err.println("Couldn't listen on port: " + PortInfos.funcPort);
            System.exit(-1);
        }

        // Listen to the socket, accepting connections from new clients,
        // and running a new thread to serve each new client:
        try {
            while (true) {
                Socket clientSocketFunc = serverSocketFunc.accept();
                System.out.println("Client connected from " + clientSocketFunc.getInetAddress());
                // get stuck until somebody connects
                ServerMainThread mainThread = new ServerMainThread(clientSocketFunc);
                tasks.execute(mainThread);
            }
        }
        catch (Exception e) {
            try {
                serverSocketFunc.close();
            }
            catch (IOException io) {
                System.err.println("Couldn't close server socket" + io.getMessage());
            }
        }
    }

    public static void main(String[] args){
        FuncServer s = new FuncServer();
        s.initialization();
    }

}
