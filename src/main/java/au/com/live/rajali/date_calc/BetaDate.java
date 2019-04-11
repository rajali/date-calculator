package au.com.live.rajali.date_calc;

import org.apache.commons.cli.ParseException;

import static au.com.live.rajali.date_calc.Consts.Regex.DATE_PATTERN;
import static au.com.live.rajali.date_calc.Consts.Regex.DATE_REGEX;
import static au.com.live.rajali.date_calc.Consts.Regex.FEB_DATES_RANGE;
import static au.com.live.rajali.date_calc.Consts.Regex.FEB_DATES_RANGE_LEAP_YEAR;
import static au.com.live.rajali.date_calc.DateUtils.checkIfLeapYear;


class BetaDate {

    private String dateStr;
    private String yearStr;
    private int yearInt;
    private int monthInt;
    private int dayInt;

    BetaDate(String date) throws ParseException {
        this(date.substring(0, date.indexOf("-")), date.substring(date.indexOf("-") + 1, date.indexOf("-") + 3),
                date.substring(date.indexOf("-") + 4));
        this.dateStr = date;
        validateDate(date);
    }

    private BetaDate(String year, String month, String day) {
        this.yearStr = year;
        this.yearInt = Integer.valueOf(year);
        this.monthInt = Integer.valueOf(month);
        this.dayInt = Integer.valueOf(day);
    }

    String getYearStr() {
        return yearStr;
    }

    int getYearInt() {
        return yearInt;
    }

    int getMonthInt() {
        return monthInt;
    }

    int getDayInt() {
        return dayInt;
    }

    String getDateStr() {
        return dateStr;
    }

    static void validateDate(final String date) throws ParseException {
        String extractedDate = date.substring(date.indexOf("-") + 1);

        if (!date.matches(DATE_PATTERN)) {
            throw new ParseException("Invalid date pattern.");
        } else if (!date.matches(DATE_REGEX)) {
            throw new ParseException("Invalid date range provided.");
        } else if ("02".equalsIgnoreCase(date.substring(date.indexOf("-") + 1, date.indexOf("-") + 3))) {
            if (checkIfLeapYear(date.substring(0, date.indexOf("-")))) {
                if (!extractedDate.matches(FEB_DATES_RANGE_LEAP_YEAR)) {
                    throw new ParseException("Invalid date range for the monthStr of February.");
                }
            } else {
                if (!extractedDate.matches(FEB_DATES_RANGE)) {
                    throw new ParseException("Invalid date range for the monthStr of February.");
                }

            }
        }
    }
}
