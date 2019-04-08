package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SearchPatientPidCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.RecordContainsPatientIdPredicate;

/**
 * Parses input arguments and creates a new SearchPatientCommand object
 */
public class SearchPatientPidCommandParser implements Parser<SearchPatientPidCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchPatientPidCommand
     * and returns an SearchPatientPidCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchPatientPidCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchPatientPidCommand.MESSAGE_USAGE));
        }

        return new SearchPatientPidCommand(new RecordContainsPatientIdPredicate(new PersonId(trimmedArgs)));
    }

}
