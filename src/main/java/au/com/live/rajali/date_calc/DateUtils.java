package au.com.live.rajali.date_calc;

import org.apache.commons.cli.ParseException;

import java.util.HashMap;

public class DateUtils {

    private final static HashMap<Integer, Integer> monthAndDaysMap = new HashMap<Integer, Integer>() {
        {
            put(1, 31);
            put(2, 28);
            put(3, 31);
            put(4, 30);
            put(5, 31);
            put(6, 30);
            put(7, 31);
            put(8, 31);
            put(9, 30);
            put(10, 31);
            put(11, 30);
            put(12, 31);
        }
    };

    private static int NO_OF_DAYS_NON_LEAP_YEAR;

    static {
        NO_OF_DAYS_NON_LEAP_YEAR = 0;
        for (Integer i : monthAndDaysMap.values()) {
            NO_OF_DAYS_NON_LEAP_YEAR += i;
        }
    }

    public DateUtils() {}

    static Boolean checkIfLeapYear(String year) {
        return (Integer.valueOf(year) % 400 == 0)
                || ((Integer.valueOf(year) % 4 == 0) && (Integer.valueOf(year) % 100 != 0));
    }

    private static Boolean validateDatesPrecedence(BetaDate startDate, BetaDate endDate) {
        if (startDate.getYearInt() > endDate.getYearInt()) {
            return Boolean.FALSE;
        } else if ((startDate.getYearInt() == endDate.getYearInt())
                && (startDate.getMonthInt() > endDate.getMonthInt())) {
            return Boolean.FALSE;
        } else if ((startDate.getYearInt() == endDate.getYearInt())
                && (startDate.getMonthInt() == endDate.getMonthInt())
                && (startDate.getDayInt() > endDate.getDayInt())) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    /*
    In the final result, deduct two days as we are excluding the start Date and end Date.
     */
    static int calculateDaysBetweenBetaDates(BetaDate startDate, BetaDate endDate) throws ParseException {

        int totalNoOfDays = 0;
        if (!validateDatesPrecedence(startDate, endDate)) {
            throw new ParseException("Start date cannot be ahead of the end date.");
        } else {
            int differenceInYear = endDate.getYearInt() - startDate.getYearInt();
            if (differenceInYear == 0) {
                //calculate different between month and days
            } else if (differenceInYear == 1) {
                //calculate days from start date till start of next year
                //calculate days from start of year to endDate
                totalNoOfDays += daysFromStartDateTillEndOfYear(startDate);
                totalNoOfDays += daysFromStartOfYearTillEndDate(endDate);
            } else {
                //calculate days from start date till start of next year
                //calculate days from start of year to endDate
                totalNoOfDays += daysFromStartDateTillEndOfYear(startDate);
                totalNoOfDays += daysFromStartOfYearTillEndDate(endDate);
                for (int i = 2; i < differenceInYear; i++) {
                    int year = startDate.getYearInt() + i;
                    if (!checkIfLeapYear(String.valueOf(year))) {
                        totalNoOfDays += NO_OF_DAYS_NON_LEAP_YEAR;
                    } else {
                        //count 29 days for Feb instead of 28
                        totalNoOfDays += NO_OF_DAYS_NON_LEAP_YEAR;
                        totalNoOfDays += 1;
                    }
                }
            }


            //int numberOfDaysStartDate = ( (1451 * startDate.getYearInt()) / 4) + ( (153*startDate.getMonthInt()) /5 ) + startDate.getDayInt();
            //int numberOfDaysEndDate = ( (1451 * endDate.getYearInt()) / 4) + ( (153*endDate.getMonthInt()) /5 ) + endDate.getDayInt();
            //return numberOfDaysEndDate - numberOfDaysStartDate;
            return totalNoOfDays;
        }
    }

    private static int daysFromStartDateTillEndOfYear(BetaDate startDate) {
        if (!checkIfLeapYear(startDate.getYearStr())) {

        } else {
            //if Feb not part of it count 29 days for Feb
        }

        return 1;
    }

    private static int daysFromStartOfYearTillEndDate(BetaDate endDate) {
        if (!checkIfLeapYear(endDate.getYearStr())) {

        } else {
            //if Feb not part of it count 29 days for Feb
        }
        return 1;
    }

    //Possibility 1
    private static final int msInOneDay = 1000*60*60*24;
    private static int getMillisecondsForDateSince19010101(BetaDate date) {

        return 1;
    }
}
