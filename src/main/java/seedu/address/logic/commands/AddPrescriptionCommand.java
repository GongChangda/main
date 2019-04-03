package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.prescription.Prescription;


/**
 * Adds a prescription.
 */
public class AddPrescriptionCommand extends Command {

    public static final String COMMAND_WORD = "add-prescription";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a medical history of a patient to the docX."
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "one dose of amoxicillin and two doses of cephalexin";
    public static final String MESSAGE_SUCCESS = "New prescription added: %1$s";
    public static final String MESSAGE_DUPLICATE_PRESCRIPTION = "This prescription already exists in the docX";

    private final Prescription prescriptionToAdd;

    /**
     * Creates an addPatientCommand to add the specified {@code Patient}
     */
    public AddPrescriptionCommand(Prescription prescriptionToAdd) {
        requireNonNull(prescriptionToAdd);
        this.prescriptionToAdd = prescriptionToAdd;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasPrescription(prescriptionToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRESCRIPTION);
        }
        model.addPrescription(prescriptionToAdd);
        model.commitDocX();
        return new CommandResult(String.format(MESSAGE_SUCCESS, prescriptionToAdd));

    }



    @Override
    public boolean equals(Object other) {
        return other instanceof AddPrescriptionCommand
                && this.prescriptionToAdd.equals(((AddPrescriptionCommand) other).prescriptionToAdd);
    }

}



