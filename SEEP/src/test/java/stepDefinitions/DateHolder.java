package stepDefinitions;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateHolder {

    /**
     * this class will:
     *  Return the current date without the current time
     * @return the current date without the current time
     */

    public Calendar getDate() {
        Calendar calendar = new GregorianCalendar();
        return new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
    }
}
