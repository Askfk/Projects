package Server.Buffers;

import Tools.ClassTools.Infors.Info;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class stores all the messages buffer in server
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class MessageBuffer {

    // Store UID and the message to this UID, <UID, Message>
    public static Map<String, ArrayList<Info>> clientMessageBuffer = new TreeMap<String, ArrayList<Info>>();

    /**
     *
     * @param info
     */
    public static synchronized void addClientMessageBuffer(Info info){
        String s = info.toID;
        insertInfoToId(info, s);
    }

    /**
     *
     * @param toIds
     * @param info
     */
    public static synchronized void addGroupMessageBuffer(String toIds, Info info){
        String[] ids = toIds.split("/");
        for(String id : ids){
            if(!id.equals(""))
                insertInfoToId(info, id);
        }
    }

    /**
     *
     * @param info
     * @param id
     */
    private static void insertInfoToId(Info info, String id) {
        if(clientMessageBuffer.containsKey(id)){
            ArrayList<Info> temp = clientMessageBuffer.get(id);
            temp.add(info);
            clientMessageBuffer.put(id, temp);
        }
        else {
            ArrayList<Info> temp = new ArrayList<Info>();
            temp.add(info);
            clientMessageBuffer.put(id, temp);
        }
    }
}
