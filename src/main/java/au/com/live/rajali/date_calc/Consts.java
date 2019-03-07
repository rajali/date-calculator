package au.com.live.rajali.date_calc;

final class Consts {

    private Consts() {}

    static final class Prompts {
        private Prompts() {}

        //Operation Set Description
        static final String OPERATION = " ** Calculate days in between a start and end date **. ";
        static final String OPERATION_SET_DESCRIPTION = " The calculation excludes the provided start and end dates." +
                " \n The dates provided must be between 1901-01-01 and 2999-12-31." +
                " \n Valid date format is YYYY-MM-DD. ";

        //Command Line Arguments
        //* Main Operation
        static final String START_DATE_ARGUMENT_DESCRIPTION = "The start date.";
        static final String END_DATE_ARGUMENT_DESCRIPTION = "The end date.";

        //Display message and error Messages
        static final String REQUIRED_ARG_MISSING = "Required arguments are not provided.";
        static final String PARSING_INPUT_EXCEPTION = "Encountered exception while parsing the input arguments. \n";
    }

    static final class Operation {
        private Operation() {}

        //Short codes
        static final String START_DATE_OPT = "s";
        static final String END_DATE_OPT = "e";

        //Long names
        static final String START_DATE_LONG_OPT = "startdate";
        static final String END_DATE_LONG_OPT = "enddate";
    }

    static final class Values {
        private Values() {}
        static final String EMPTY_STRING = "";
        static final Integer ONE_HUNDRED = 100;
        static final Integer FIVE = 5;
    }

    static final class Regex {
        private Regex() {}
        static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\{2}$";
        static final String YEAR_RANGE = "(190[1-9]|299[0-9])";
        static final String FEB_DATES_RANGE = "(02)[-.](0[1-9]|1[0-9]|2[0-8])";
        static final String FEB_DATES_RANGE_LEAP_YEAR = "(02)[-.](0[1-9]|1[0-9]|2[0-9])";
        static final String DATE_REGEX = "^" + YEAR_RANGE + "-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
    }
}
