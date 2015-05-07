package lk.dialog.kuppi.dialogandroidintro3;

/**
 * Created by Tharu on 2015-05-07.
 */
public class Utilities {

    public static int COUNTER = 0;

    public static int doLargeTask(int weight) {

        try {
            Thread.sleep(weight * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return COUNTER++;
    }
}
