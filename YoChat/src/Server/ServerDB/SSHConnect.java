package Server.ServerDB;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHConnect {

    private static final String url = "mod-msc-sw1.cs.bham.ac.uk";
    private static final String sshurl = "tinky-winky.cs.bham.ac.uk";
    private static final String sshuser = "yxl1215";
    private static final String sshpw = "Mg.123QQ..";

    public static void configVPN(){
        try{
            JSch jSch = new JSch();
            Session session = jSch.getSession(sshuser, sshurl, 22);
            session.setPassword(sshpw);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());

            int assigned_port = session.setPortForwardingL(10000, url, 5432);
            System.out.println("localhost: " + assigned_port);

        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SSHConnect.configVPN();
    }
}
