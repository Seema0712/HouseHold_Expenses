package subscription;

import java.util.*;

public class CalendarUtility {

    static List<String> allWeekDays = Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
    static int totalDays ;

    /**
     * This method will return the current date
     * @return String[]
     */
    public static String[] getTime() {
        Calendar calendar = Calendar.getInstance();
        Date current_Date = new Date();
        calendar.setTime(current_Date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        totalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return calendar.getTime().toString().split(" ");
    }

    /**
     * This method will return the hashmap having the values of frequency of weekday
     * @return
     */
    public static HashMap<String,Integer>  getNumberOfDaysInWeek() {
        HashMap<String, Integer> weekDays = new HashMap<>();
        String []dates = getTime();
        int weekNumber = 4;
        int DaysDifference = totalDays - 28;
        int index = 0;
        int counter = 0;
        for(String str: allWeekDays) {
            if(str.startsWith(dates[0]))
                index = allWeekDays.indexOf(str);
        }
        while (counter<7) {
            if(index>=7)
                index = index -7;
            if(DaysDifference>0)
            {
                weekDays.put(allWeekDays.get(index),weekNumber+1);
                DaysDifference --;
            }
            else
                weekDays.put(allWeekDays.get(index),weekNumber);
            counter++;
            index++;
        }

        return weekDays;
}
}
