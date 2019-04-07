package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.person.DoctorSpecialisationContainsKeywordsPredicate;

/**
 * Searches and lists all doctors in docX record whose specialisation contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchDoctorSpecialisationCommand extends Command {

    public static final String COMMAND_WORD = "search-spec";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches all doctors whose specialisations"
            + " contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: [KEYWORD] [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " acupuncture general";

    private final DoctorSpecialisationContainsKeywordsPredicate predicate;

    public SearchDoctorSpecialisationCommand(DoctorSpecialisationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredDoctorList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_DOCTORS_LISTED_OVERVIEW, model.getFilteredDoctorList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchDoctorSpecialisationCommand // instanceof handles nulls
                && predicate.equals(((SearchDoctorSpecialisationCommand) other).predicate)); // state check
    }
}