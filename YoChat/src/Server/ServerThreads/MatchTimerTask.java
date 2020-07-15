package Server.ServerThreads;

import Server.ServerDB.JDBConnection;
import Server.Buffers.MatchList;
import Tools.ClassTools.Users.User;

import java.util.Random;
import java.util.TimerTask;

/**
 * This TimerTask do the match func, match male user and female user in the match list.
 */
public class MatchTimerTask extends TimerTask {

    private JDBConnection JDBConnection;

    public MatchTimerTask(JDBConnection JDBConnection){
        this.JDBConnection = JDBConnection;
    }

    @Override
    public void run(){
        if(MatchList.femaleList.size() > 0 && MatchList.maleList.size() > 0){
            System.out.println("Now female user in queue: " + MatchList.femaleList.size());
            System.out.println("Now male user in queue: " + MatchList.maleList.size());
            match();
        }
    }

    /**
     * do the match func
     */
    void match(){
        int indexOfMale = randInt(0, MatchList.maleList.size());
        int indexOfFemale = randInt(0, MatchList.femaleList.size());

        User maleUser = MatchList.maleList.get(indexOfMale);
        MatchList.removeMaleList(maleUser);
        User femaleUser = MatchList.femaleList.get(indexOfFemale);
        MatchList.removeFemaleList(femaleUser);

        JDBConnection.writeMatchPair(maleUser.getUID(), femaleUser.getUID());
    }

    /**
     * get random int in given range
     * @param min
     * @param max
     * @return
     */
    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt(max - min) + min;

        return randomNum;
    }

}
