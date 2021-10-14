import java.sql.Timestamp;
import java.text.SimpleDateFormat;
/* Author: Simon */

//Found the code on : https://mkyong.com/java/how-to-get-current-timestamps-in-java/
public class TimeStampExample {

    // 2021.03.24.16.34.26
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    // 2021-03-24T16:44:39.083+08:00
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    // 2021-03-24 16:48:05
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static Timestamp getTimeStamp(){
       return new Timestamp(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }
}
