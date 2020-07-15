package Tools.ClassTools.Infors;

import java.io.Serializable;

/**
 * Encapsulate the messages in the info class.
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class Info implements Serializable {

    private static final long serialVersionUID = 5412413123L;

    public String fromID;
    public String toID;
    public String message;
    public String time;
    public boolean isPrivate;

    /**
     *
     * @param fromID
     * @param toID
     * @param message
     * @param time
     * @param isPrivate
     */
    public Info(String fromID, String toID, String message, String time, boolean isPrivate){
        this.fromID = fromID;
        this.toID = toID;
        this.message = message;
        this.time = time;
        this.isPrivate = isPrivate;
    }


    public String toString(){
        String s = "";
        s += fromID + " said " + "'" + message + "' to " + toID + " " + time;
        return s;
    }
}
