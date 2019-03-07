package au.com.live.rajali.date_calc;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import static au.com.live.rajali.date_calc.Consts.Operation.END_DATE_LONG_OPT;
import static au.com.live.rajali.date_calc.Consts.Operation.END_DATE_OPT;
import static au.com.live.rajali.date_calc.Consts.Operation.START_DATE_LONG_OPT;
import static au.com.live.rajali.date_calc.Consts.Operation.START_DATE_OPT;
import static au.com.live.rajali.date_calc.Consts.Values.EMPTY_STRING;
import static au.com.live.rajali.date_calc.Consts.Values.FIVE;
import static au.com.live.rajali.date_calc.Consts.Values.ONE_HUNDRED;

/**
 * Simple main application
 */
public class App {
    private static Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String APP_NAME = "date-calculator" + "-{version-no}" + ".jar";
    private static BetaDate startDate;
    private static BetaDate endDate;

    public static void main(String[] args) throws ParseException {
        printUsage(constructCommandOptions());
        displayBlankLines();
        useArgumentsParser(args);
        printCalculatedDaysBetweenDates();
    }

    private static void printCalculatedDaysBetweenDates() throws ParseException {
        if (startDate.getDateStr().equalsIgnoreCase(endDate.getDateStr())) {
            System.out.println(0);
        } else {
            System.out.println(DateUtils.calculateDaysBetweenBetaDates(startDate, endDate));
        }
    }

    private static void useArgumentsParser(final String[] commandLineArgs) {
        final CommandLineParser cmdLineParser = new DefaultParser();
        final Options options = constructCommandOptions();
        CommandLine commandLine;
        try {
            LOGGER.debug("Before parsing input.");
            commandLine = cmdLineParser.parse(options, commandLineArgs);
            LOGGER.debug("Input received as: -s " + commandLine.getOptionValue(START_DATE_OPT));
            LOGGER.debug("Input received as: -e " + commandLine.getOptionValue(END_DATE_OPT));

            if ((commandLine.hasOption(START_DATE_OPT) || commandLine.hasOption(START_DATE_LONG_OPT))
                    && (commandLine.hasOption(END_DATE_OPT) || commandLine.hasOption(END_DATE_LONG_OPT))) {
                LOGGER.info(Consts.Prompts.OPERATION);
                startDate = new BetaDate((null != commandLine.getOptionValue(START_DATE_OPT))
                        ? commandLine.getOptionValue(START_DATE_OPT) : commandLine.getOptionValue(START_DATE_LONG_OPT));
                endDate = new BetaDate((null != commandLine.getOptionValue(END_DATE_OPT))
                        ? commandLine.getOptionValue(END_DATE_OPT) : commandLine.getOptionValue(END_DATE_LONG_OPT));
            } else {
                LOGGER.info(Consts.Prompts.REQUIRED_ARG_MISSING);
                displayBlankLines();
                printUsage(constructCommandOptions());
                displayBlankLines();
            }
        } catch (ParseException e) {
            LOGGER.error(Consts.Prompts.PARSING_INPUT_EXCEPTION + e.getMessage());
            displayBlankLines();
            printUsage(constructCommandOptions());
            displayBlankLines();
        }
    }

    private static Options constructCommandOptions() {
        final Options options = new Options();
        options.addRequiredOption(START_DATE_OPT, START_DATE_LONG_OPT, true,
                Consts.Prompts.OPERATION + Consts.Prompts.OPERATION_SET_DESCRIPTION
                        + Consts.Prompts.START_DATE_ARGUMENT_DESCRIPTION);
        options.addRequiredOption(END_DATE_OPT, END_DATE_LONG_OPT, true,
                Consts.Prompts.OPERATION + Consts.Prompts.OPERATION_SET_DESCRIPTION
                        + Consts.Prompts.END_DATE_ARGUMENT_DESCRIPTION);
        return options;
    }

    private static void printUsage(final Options options) {
        PrintWriter writer = new PrintWriter(System.out);
        final HelpFormatter usageFormatter = new HelpFormatter();
        usageFormatter.printHelp(writer, ONE_HUNDRED, APP_NAME, EMPTY_STRING, options, FIVE, FIVE, EMPTY_STRING, Boolean.TRUE);
        writer.flush();
        writer.close();
    }

    private static void displayBlankLines() {
        try {
            for (int i = 0; i < 2; i++) {
                ((OutputStream) System.out).write("\n".getBytes());
            }
        } catch (IOException ex) {
            for (int i = 0; i < 2; i++) {
                System.out.println();
            }
        }
    }
}
