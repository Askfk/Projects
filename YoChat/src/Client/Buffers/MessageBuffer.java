package Client.Buffers;

import Tools.ClassTools.Infors.Info;

import java.util.ArrayList;

/**
 * This is the message buffer which contains all the unsettled messages that current user made.
 * @author Yiming Li
 * @version 2020-03
 */

public class MessageBuffer {


    // Store UID and the message to this UID <UID, Message>
    public static ArrayList<Info> serverMessageBuffer = new ArrayList<Info>();

    public static synchronized void addServerMessageBuffer(Info info){
        serverMessageBuffer.add(info);
    }

}
