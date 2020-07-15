package Client.Utils;

import Tools.ClassTools.Infors.Info;

import java.io.*;
import java.util.ArrayList;

/**
 * This utils record and find all the chat histories of the client.
 * @author Yiming Li
 * @version 2020-03
 */

public class RecordWriter {

    private final static String INTERVAL_SIGN = "<Interval Sign></Interval Sign>";

    private final static String DIRECTORY = "src/Client/MessageRecord/";

    /**
     * Write chat messages
     * @param infos all the unrecorded personal chat messages
     */
    public synchronized static void writeMessages(ArrayList<Info> infos) {
        File folder = new File("/MessageRecord");
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdir();
        }
        try {
            for(Info info : infos) {
                String fromID = info.fromID;
                String toID = info.toID;
                String message = info.message;
                String chatTime = info.time;
                boolean isPrivate = info.isPrivate;

                if(isPrivate){ // if is private
                    String pair;
                    if(Integer.valueOf(fromID) > Integer.valueOf(toID)){
                        pair = toID + "vs" + fromID;
                    }
                    else
                        pair = fromID + "vs" + toID;
                    //Define the file storage path and file name
                    String path = DIRECTORY + pair + ".txt";
                    // Determine if the folder storing the chat software exists
                    File file = new File(path);
                    FileWriter fw;
                    if (!file.exists()) {
                        file.createNewFile();
                        fw = new FileWriter(file);
                    } else {
                        fw = new FileWriter(file, true);
                    }

                    BufferedWriter bfr = new BufferedWriter(fw);

                    bfr.write(fromID + INTERVAL_SIGN);
                    bfr.write(toID + INTERVAL_SIGN);
                    bfr.write(message + INTERVAL_SIGN);
                    bfr.write(chatTime + INTERVAL_SIGN);
                    bfr.write("private");
                    bfr.newLine();
                    bfr.flush();
                    bfr.close();
                    fw.close();
                }
                else { // if is group
                    String path = DIRECTORY + "GroupChat_" + info.toID + ".txt";
                    File file = new File(path);
                    FileWriter fw;
                    if (!file.exists()) {
                        file.createNewFile();
                        fw = new FileWriter(file);
                    } else {
                        fw = new FileWriter(file, true);
                    }
                    BufferedWriter bfr = new BufferedWriter(fw);

                    bfr.write(fromID + INTERVAL_SIGN);
                    bfr.write(toID + INTERVAL_SIGN);
                    bfr.write(message + INTERVAL_SIGN);
                    bfr.write(chatTime + INTERVAL_SIGN);
                    bfr.write("private");
                    bfr.newLine();
                    bfr.flush();
                    bfr.close();
                    fw.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read private chat infos with keyword
     * @param fromId fromId
     * @param toId toID
     * @param keyword chat keywords
     * @return
     */
    public synchronized static ArrayList<Info> readMessagesByKey(String fromId, String toId, String keyword){
        ArrayList<Info> infos = new ArrayList<Info>();
        try{
            String pair;
            if(Integer.valueOf(fromId) > Integer.valueOf(toId))
                pair = toId + "vs" + fromId;
            else
                pair = fromId + "vs" + toId;
            String path = DIRECTORY + pair + ".txt";
            File file = new File(path);
            if(file.exists()){
                FileReader fw = new FileReader(path);
                BufferedReader bfr = new BufferedReader(fw);
                String line;
                while ((line = bfr.readLine())!=null){
                    if(line.contains(keyword)) {
                        String[] temp = line.split(INTERVAL_SIGN);
                        Info info = new Info(temp[0], temp[1], temp[2], temp[3], true);
                        infos.add(info);
                    }
                }
                fw.close();
                bfr.close();
            }

            return infos;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Read group chat infos with keyword
     * @param groupId groupId
     * @param keyword chat keywords
     * @return messages
     */
    public synchronized static ArrayList<Info> readGroupMessagesByKey(String groupId, String keyword) {
        ArrayList<Info> infos = new ArrayList<Info>();
        String path = DIRECTORY + "GroupChat_" + groupId + ".txt";
        try {
            File file = new File(path);
            if (file.exists()) {
                FileReader fw = new FileReader(path);
                BufferedReader bfr = new BufferedReader(fw);
                String line;
                while ((line = bfr.readLine()) != null) {
                    if (line.contains(keyword)) {
                        String[] temp = line.split(INTERVAL_SIGN);
                        Info info = new Info(temp[0], temp[1], temp[2], temp[3], false);
                        infos.add(info);
                    }
                }
                fw.close();
                bfr.close();
            }
            return infos;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Read all the private chat infos
     * @param fromId fromId
     * @param toId toId
     * @return messages
     */
    public synchronized static ArrayList<Info> readAllMessages(String fromId, String toId){
        ArrayList<Info> infos = new ArrayList<Info>();
        try{
            String pair;
            if(Integer.valueOf(fromId) > Integer.valueOf(toId))
                pair = toId + "vs" + fromId;
            else
                pair = fromId + "vs" + toId;
            String path = DIRECTORY + pair + ".txt";
            File file = new File(path);
            if(file.exists()){
                FileReader fw = new FileReader(path);
                BufferedReader bfr = new BufferedReader(fw);
                String line;
                while ((line = bfr.readLine())!=null){
                    String[] temp = line.split(INTERVAL_SIGN);
                    Info info = new Info(temp[0], temp[1], temp[2], temp[3], true);
                    infos.add(info);
                }
                fw.close();
                bfr.close();
            }
            return infos;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Read all the private chat infos
     */
    public synchronized static ArrayList<Info> readAllGroupMessages(String groupId) {
        ArrayList<Info> infos = new ArrayList<Info>();
        String path = DIRECTORY + "GroupChat_" + groupId + ".txt";
        try {
            File file = new File(path);
            if (file.exists()) {
                FileReader fw = new FileReader(path);
                BufferedReader bfr = new BufferedReader(fw);
                String line;
                while ((line = bfr.readLine()) != null) {
                    String[] temp = line.split(INTERVAL_SIGN);
                    Info info = new Info(temp[0], temp[1], temp[2], temp[3], false);
                    infos.add(info);
                }
                fw.close();
                bfr.close();
            }
            return infos;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
