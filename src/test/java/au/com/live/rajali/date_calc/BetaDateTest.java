package au.com.live.rajali.date_calc;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BetaDateTest {

    @Test
    public void testValidateDate() {
        String dateOne = "199-02-28";
        String dateTwo = "1999-01-28";
        try {
            BetaDate.validateDate(dateOne);
        } catch (ParseException exception) {
            assertThat(true);
        }

        try {
            BetaDate.validateDate(dateTwo);
        } catch (ParseException exception) {
            assertThat(false);
        }
    }

}