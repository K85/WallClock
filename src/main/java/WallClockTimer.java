import java.util.Calendar;
import java.util.Timer;

public class WallClockTimer implements Runnable{
    private final WallClock wallClock;
    public WallClockTimer(WallClock wallClock) {
        this.wallClock = wallClock;
    }

    @Override
    public void run() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        this.wallClock.update(hour, minute, second);
    }
}
