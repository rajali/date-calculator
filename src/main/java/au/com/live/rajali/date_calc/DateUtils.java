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

    private static final int NO_OF_DAYS_NON_LEAP_YEAR = 365;

    public DateUtils() {}

    static Boolean checkIfLeapYear(String year) {
        return (Integer.valueOf(year) % 400 == 0)
                || ((Integer.valueOf(year) % 4 == 0) && (Integer.valueOf(year) % 100 != 0));
    }

    static Boolean validateDatesPrecedence(BetaDate startDate, BetaDate endDate) {
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

    static int calculateDaysBetweenBetaDates(BetaDate startDate, BetaDate endDate) throws ParseException {

        int totalNoOfDays = 0;
        if (!validateDatesPrecedence(startDate, endDate)) {
            throw new ParseException("Start date cannot be ahead of the end date.");
        } else {
            int differenceInYear = endDate.getYearInt() - startDate.getYearInt();
            if (differenceInYear == 0) {
                totalNoOfDays += daysBetweenMonthAndDays(startDate.getYearStr(), startDate.getMonthInt(), startDate.getDayInt(),
                        endDate.getMonthInt(), endDate.getDayInt());
            } else if (differenceInYear == 1) {
                totalNoOfDays += daysFromStartDateTillEndOfYear(startDate);
                totalNoOfDays += daysFromStartOfYearTillEndDate(endDate);
            } else {
                totalNoOfDays += daysFromStartDateTillEndOfYear(startDate);
                totalNoOfDays += daysFromStartOfYearTillEndDate(endDate);
                int year = startDate.getYearInt();
                for (int i = 1; i < differenceInYear; i++) {
                    year++;
                    totalNoOfDays += NO_OF_DAYS_NON_LEAP_YEAR;
                    if (checkIfLeapYear(String.valueOf(year))) {
                        totalNoOfDays += 1;
                    }
                }
            }
            return totalNoOfDays;
        }
    }

    static int daysBetweenMonthAndDays(String yearStr, Integer startMonth, Integer startDay, Integer endMonth, Integer endDay) {
        int totalDaysInBetween = 0;
        endDay = endDay - 1;
        int differenceInMonths = endMonth - startMonth;
        if (differenceInMonths == 0) {
            totalDaysInBetween += (endDay - startDay);
        } else {
            totalDaysInBetween += (monthAndDaysMap.get(startMonth) - startDay);

            if (endDay < 1) {
                endMonth--;
                totalDaysInBetween += monthAndDaysMap.get(endMonth);
            } else {
                totalDaysInBetween += endDay;
            }

            for (int loop = 0; loop < differenceInMonths - 1; loop++) {
                if (startMonth.equals(endMonth)) {
                    break;
                }
                startMonth++;
                totalDaysInBetween += monthAndDaysMap.get(startMonth);

                if (checkIfLeapYear(yearStr)
                        &&  ( "2".equalsIgnoreCase(String.valueOf(startMonth))
                        || "2".equalsIgnoreCase(String.valueOf(endMonth)))) {
                    totalDaysInBetween += 1;
                }
            }
        }

        return totalDaysInBetween;
    }

    static int daysFromStartDateTillEndOfYear(BetaDate startDate) {
        int totalNoOfDaysTillYearEnd = 0;
        int month = startDate.getMonthInt();
        int day = startDate.getDayInt();
        int differenceInMonths = 12 - month;

        if (!checkIfLeapYear(startDate.getYearStr())) {
            if (day > monthAndDaysMap.get(month)) {
                totalNoOfDaysTillYearEnd += monthAndDaysMap.get(month);
                month++;
            } else {
                totalNoOfDaysTillYearEnd += (monthAndDaysMap.get(month) - day);
                month++;
            }

        } else {
            if (month == 2 && (day == 28)) {
                totalNoOfDaysTillYearEnd += monthAndDaysMap.get(month);
                totalNoOfDaysTillYearEnd += 1;
                month++;
            } else {
                totalNoOfDaysTillYearEnd += (monthAndDaysMap.get(month) - day);
                month++;
            }
        }

        totalNoOfDaysTillYearEnd += daysForGivenNumberOfMonths(differenceInMonths, month, true);
        return totalNoOfDaysTillYearEnd;
    }

    static int daysFromStartOfYearTillEndDate(BetaDate endDate) {
        int totalNoOfDaysTillYearEnd = 0;
        int month = endDate.getMonthInt();
        int day = endDate.getDayInt();
        int differenceInMonths = month;
        day--;
        if ( day == 0) {
            totalNoOfDaysTillYearEnd += monthAndDaysMap.get(month);
            month--;
        } else {
            totalNoOfDaysTillYearEnd += day;
            month--;
        }

        if (checkIfLeapYear(endDate.getYearStr()) && month == 2) {
            totalNoOfDaysTillYearEnd += 1;
        }

        totalNoOfDaysTillYearEnd += daysForGivenNumberOfMonths(differenceInMonths, month, false);

        return totalNoOfDaysTillYearEnd;
    }

    static int daysForGivenNumberOfMonths(Integer noOfMonths, Integer startMonth, Boolean isDateToEndOfYear) {
        Integer totalDays = 0;
        for (int loop = 0; loop < noOfMonths; loop++) {
            totalDays += monthAndDaysMap.get(startMonth);
            if (isDateToEndOfYear) {
                startMonth++;
                if (startMonth > 12) {
                    break;
                }
            } else {
                startMonth--;
                if (startMonth == 0) {
                    break;
                }
            }
        }
        return totalDays;
    }
}
