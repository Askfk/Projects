package Server.Buffers;

import Tools.Sets.ArraySet;
import Tools.ClassTools.Users.User;

/**
 * This class stores all the users that join the match list
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class MatchList {

    public static ArraySet<User> maleList = new ArraySet<User>();
    public static ArraySet<User> femaleList = new ArraySet<User>();

    public static synchronized void addMaleList(User user){
        maleList.add(user);
    }

    public static synchronized void addFemaleList(User user){
        femaleList.add(user);
    }

    public static synchronized void removeMaleList(User user){
        maleList.remove(user);
    }

    public static synchronized void removeFemaleList(User user){
        femaleList.remove(user);
    }

}
