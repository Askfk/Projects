package Client.ClientGUI.JButtonListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

/**
 * Create a JButtonListModel that extends  AbstractListModel with typy String
 * @author Yiming Li
 * @version 2020-03
 */
public class JButtonListModel extends AbstractListModel<String> {
    private static final long serialVersionUID = 1L;
    // jbuttonFile contains all the button messages: "name/id/isOnline/isGroup"
    private List<String> jbuttonFile = new ArrayList<String>();
    // buttonInfoMap contains all the button messages with id: name/id/isOnline/isGroup
    private Map<String, String> buttonInfoMap = new HashMap<String, String>();

    /**
     * This method add elements (button infos)
     * @param string button infos
     */
    public void addElement(String string){
        this.jbuttonFile.add(string);
        this.buttonInfoMap.put(string.split("/")[1], string);
    }

    /**
     * This method returns the number of buttons
     * @return The number of buttons
     */
    public int getSize(){
        return jbuttonFile.size();
    }

    /**
     * This method return the button info of a given index
     * @param index given index
     * @return the button info
     */
    @Override
    public String getElementAt(int index) {
        return jbuttonFile.get(index);
    }

    /**
     * This method check whether the given id of the user of the button is already exist.
     * @param userId The user id of the button
     * @return true if contain otherwise false.
     */
    public boolean contains(String userId){
        return buttonInfoMap.containsKey(userId);
    }

    /**
     * This method change the button info, due to the id of the button is fixed, it is easy to
     * locate the button by id in status.
     * @param status New button info.
     */
    public void changeButtonStatus(String status){
        // status: name/id/isOnline/isGroup
        for(String jButton : jbuttonFile){
            String[] ele = jButton.split("/");
            String[] status_ = status.split("/");
            if(ele[1].equals(status_[1])){
                int index = jbuttonFile.indexOf(jButton);
                jbuttonFile.set(index, status);
            }
        }
    }
}
