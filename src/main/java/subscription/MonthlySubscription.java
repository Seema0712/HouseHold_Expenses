package subscription;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MonthlySubscription implements HouseholdExpense{

    static List<String> allWeekDays = Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");

    public static void main (String args[]) throws Exception {

        MonthlySubscription subscription = new MonthlySubscription();
        String newsPapers = null;
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        System.out.println("Enter the newspaper names");
        newsPapers = bufferedReader.readLine();
        for(String a: newsPapers.split(","))
        {
            System.out.println(subscription.getExpense(a));
        }

    }

    /**
     *
     * @param givenPaper
     * @return Total monthly expense for newspaper
     */
    @Override
    public String getExpense(String givenPaper) {

        JSONParser parser = new JSONParser();
        BigDecimal expense = new BigDecimal(0);
        HashMap<String,Integer> weekDays = CalendarUtility.getNumberOfDaysInWeek();
        try {
            FileReader reader = new FileReader(new File("/Users/seema/Documents/HouseholdExpenses/NewsPaperExpense.json"));
            JSONObject paperExpense = (JSONObject) parser.parse(reader);
            JSONArray papers = (JSONArray) paperExpense.get("Papers");
            JSONArray days = null;
            for(Object o : papers) {
                JSONObject paper = (JSONObject) o;
               String paperName = (String) paper.get("NewsPaperName");
               if(paperName.equals(givenPaper)) {
                   days = (JSONArray) paper.get("Days");
                   for (int i = 0; i < allWeekDays.size(); i++) {
                       JSONObject daysInWeek = (JSONObject) days.get(i);
                       String dayValue = (String) daysInWeek.get(allWeekDays.get(i));
                       BigDecimal val = new BigDecimal(dayValue);
                       BigDecimal weekMultiplier = new BigDecimal(weekDays.get(allWeekDays.get(i)));
                       expense = val.multiply(weekMultiplier).add(expense);
                   }
               }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return expense.toString();
    }

}

