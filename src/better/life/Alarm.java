
package better.life;

import java.util.Calendar;


public class Alarm {
   String message;
   Calendar calendar;
   String emergencyContact1;
   

    public Alarm(String message, Calendar calendar, String emergencyContact1) {
        this.message = message;
        this.calendar = calendar;
        this.emergencyContact1 = emergencyContact1;
    }
    
}
