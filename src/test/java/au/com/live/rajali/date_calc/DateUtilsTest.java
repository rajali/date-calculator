package au.com.live.rajali.date_calc;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilsTest {

    @Test
    public void testCalculateDaysBetweenBetaDatesTestcaseOne() throws ParseException {
        BetaDate startDate = new BetaDate("1983-06-02");
        BetaDate endDate = new BetaDate("1983-06-22");
        int result = DateUtils.calculateDaysBetweenBetaDates(startDate, endDate);

        assertThat(result).isEqualTo(19);
    }

    @Test
    public void testCalculateDaysBetweenBetaDatesTestcaseTwo() throws ParseException {
        BetaDate startDate = new BetaDate("1984-07-04");
        BetaDate endDate = new BetaDate("1984-12-25");
        int result = DateUtils.calculateDaysBetweenBetaDates(startDate, endDate);

        assertThat(result).isEqualTo(173);
    }

    @Test
    public void testCalculateDaysBetweenBetaDatesTestcaseThree() throws ParseException {
        BetaDate startDate = new BetaDate("1983-06-02");
        BetaDate endDate = new BetaDate("1988-06-22");
        int result = DateUtils.calculateDaysBetweenBetaDates(startDate, endDate);

        assertThat(result).isEqualTo(1845);
    }

    @Test
    public void testCheckIfLeapYear() {
        String leapYearOne = "2020";
        String nonLeapYearOne = "1999";

        Boolean resultInvalid = DateUtils.checkIfLeapYear(nonLeapYearOne);
        Boolean resultValid = DateUtils.checkIfLeapYear(leapYearOne);

        assertThat(resultValid).isEqualTo(Boolean.TRUE);
        assertThat(resultInvalid).isEqualTo(Boolean.FALSE);
    }

    @Test
    public void testValidateDatesPrecedence() throws ParseException {
        BetaDate startDateInvalid = new BetaDate("1999-02-28");
        BetaDate endDateInvalid = new BetaDate("1999-01-28");

        Boolean result = DateUtils.validateDatesPrecedence(startDateInvalid, endDateInvalid);

        assertThat(result).isEqualTo(Boolean.FALSE);

        BetaDate startDateValid = new BetaDate("1999-01-28");
        BetaDate endDateValid = new BetaDate("1999-02-28");

        Boolean resultValid = DateUtils.validateDatesPrecedence(startDateValid, endDateValid);

        assertThat(resultValid).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void testDaysBetweenMonthAndDays() {
        String yearStr = "1999";
        Integer startMonth = 1;
        Integer startDay = 1;
        Integer endMonth = 8;
        Integer endDay = 30;
        int totalDaysInBetween = 240;
        int result = DateUtils.daysBetweenMonthAndDays(yearStr, startMonth, startDay, endMonth, endDay);
        assertThat(result).isEqualTo(totalDaysInBetween);
    }

    @Test
    public void testDaysBetweenMonthAndDaysTestCaseTwo() {
        String yearStr = "1983";
        Integer startMonth = 6;
        Integer startDay = 2;
        Integer endMonth = 6;
        Integer endDay = 22;
        int totalDaysInBetween = 19;
        int result = DateUtils.daysBetweenMonthAndDays(yearStr, startMonth, startDay, endMonth, endDay);
        assertThat(result).isEqualTo(totalDaysInBetween);
    }

    @Test
    public void testDaysBetweenMonthAndDaysTestCaseThree() {
        String yearStr = "1984";
        Integer startMonth = 7;
        Integer startDay = 4;
        Integer endMonth = 12;
        Integer endDay = 25;
        int totalDaysInBetween = 173;
        int result = DateUtils.daysBetweenMonthAndDays(yearStr, startMonth, startDay, endMonth, endDay);
        assertThat(result).isEqualTo(totalDaysInBetween);
    }

    @Test
    public void testDaysForGivenNumberOfMonths() {
        int noOfMonths = 2;
        int startMonth = 1;
        int totalDaysJanFeb = 59;
        int noOfDays = DateUtils.daysForGivenNumberOfMonths(noOfMonths, startMonth, true);
        assertThat(noOfDays).isEqualTo(totalDaysJanFeb);
    }

    @Test
    public void testDaysFromStartDateTillEndOfYearNonLeapYear() throws ParseException {
        BetaDate date = new BetaDate("1999-02-28");
        //Days between 1999-02-28 and 2000-01-01; excluding start date, so start from 1st March, 1999.
        int totalDaysLeftTillYearEnd = 306;
        int noOfDays = DateUtils.daysFromStartDateTillEndOfYear(date);
        assertThat(noOfDays).isEqualTo(totalDaysLeftTillYearEnd);
    }

    @Test
    public void testDaysFromStartDateTillEndOfYearLeapYear() throws ParseException {
        BetaDate date = new BetaDate("2020-02-29");
        int totalDaysLeftTillYearEnd = 305;
        int noOfDays = DateUtils.daysFromStartDateTillEndOfYear(date);
        assertThat(noOfDays).isEqualTo(totalDaysLeftTillYearEnd);
    }

    @Test
    public void testDaysFromStartOfYearTillEndDateForLeapYear() throws ParseException {
        BetaDate endDate = new BetaDate("2020-02-29");
        int totalDaysLeftTillYearEnd = 59;
        int noOfDays = DateUtils.daysFromStartOfYearTillEndDate(endDate);
        assertThat(noOfDays).isEqualTo(totalDaysLeftTillYearEnd);
    }

    @Test
    public void testDaysFromStartOfYearTillEndDateForNonLeapYear() throws ParseException {
        BetaDate endDate = new BetaDate("1999-02-28");
        //Days between 1999-01-01 and 1999-02-28; excluding end date, so ending on 27th February, 1999.
        int totalDaysLeftTillYearEnd = 58;
        int noOfDays = DateUtils.daysFromStartOfYearTillEndDate(endDate);
        assertThat(noOfDays).isEqualTo(totalDaysLeftTillYearEnd);
    }
}