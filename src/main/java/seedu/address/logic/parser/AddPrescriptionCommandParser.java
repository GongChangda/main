package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddPrescriptionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.prescription.Description;
import seedu.address.model.prescription.Medicine;
import seedu.address.model.prescription.Prescription;


/**
 * Parses input arguments and creates a new AddMedHistCommand object
 */
public class AddPrescriptionCommandParser implements Parser<AddPrescriptionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPrescriptionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT_ID, PREFIX_DOCTOR_ID, PREFIX_MEDICINE_NAME, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_PATIENT_ID, PREFIX_DOCTOR_ID, PREFIX_MEDICINE_NAME, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPrescriptionCommand.MESSAGE_USAGE));
        }

        String patientId = argMultimap.getValue(PREFIX_PATIENT_ID).get();
        String doctorId = argMultimap.getValue(PREFIX_DOCTOR_ID).get();
        Medicine medicine = ParserUtil.parseMedicineName(argMultimap.getValue(PREFIX_MEDICINE_NAME).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Patient patient = null;
        Doctor doctor = null;
        Prescription prescription = new Prescription(patient, doctor, patientId, doctorId, medicine, description);

        return new AddPrescriptionCommand(prescription);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
