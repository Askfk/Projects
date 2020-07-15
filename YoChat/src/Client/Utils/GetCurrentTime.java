package Client.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This util helps to get the current time.
 * @author Yiming Li
 * @version 2020-03
 */
public class GetCurrentTime {

    public synchronized static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataNowStr = sdf.format(date);

        return dataNowStr;
    }
}
