package seedu.address.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.DESC_VALID_DATE_OF_APPT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_VALID_DOCTOR_ID;
import static seedu.address.logic.commands.CommandTestUtil.DESC_VALID_PATIENT_ID;
import static seedu.address.logic.commands.CommandTestUtil.DESC_VALID_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OF_APPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.commands.AddMedHistCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteDoctorCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListDoctorCommand;
import seedu.address.logic.commands.ListPatientCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SearchPatientCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.SelectDoctorCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.FutureAppointment;
import seedu.address.model.medicalhistory.MedicalHistory;
import seedu.address.model.medicalhistory.WriteUp;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.PatientNameContainsKeywordsPredicate;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.DoctorUtil;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PatientUtil;

public class DocXParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final DocXParser parser = new DocXParser();

    @Test
    public void parseCommand_addDoctor() throws Exception {
        Doctor doctor = new DoctorBuilder().build();
        AddDoctorCommand command = (AddDoctorCommand) parser.parseCommand(DoctorUtil.getAddDoctorCommand(doctor));
        assertEquals(new AddDoctorCommand(doctor), command);
    }

    @Test
    public void parseCommand_addAppointment() throws Exception {
        AddAppointmentCommand command = (AddAppointmentCommand) parser.parseCommand(
                AddAppointmentCommand.COMMAND_WORD + DESC_VALID_PATIENT_ID + DESC_VALID_DOCTOR_ID
                        + DESC_VALID_DATE_OF_APPT + DESC_VALID_START_TIME);
        assertTrue(command instanceof AddAppointmentCommand);
        assertEquals(command, new AddAppointmentCommand(new FutureAppointment(Integer.parseInt(VALID_PATIENT_ID),
                Integer.parseInt(VALID_DOCTOR_ID), LocalDate.parse(VALID_DATE_OF_APPT),
                LocalTime.parse(VALID_START_TIME))));
    }

    @Test
    public void parseCommand_addMedHist() throws Exception {
        AddMedHistCommand command = (AddMedHistCommand) parser.parseCommand(
                AddMedHistCommand.COMMAND_WORD + " " + "pid/1 did/1 d/2018-05-05 sw/testWriteUp");
        assertTrue(command instanceof AddMedHistCommand);
        assertEquals(command, new AddMedHistCommand(
                new MedicalHistory("1", "1", LocalDate.parse("2018-05-05"), new WriteUp("testWriteUp"))));
    }

    @Test
    public void parseCommand_add() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddPatientCommand command = (AddPatientCommand) parser.parseCommand(PatientUtil.getAddPatientCommand(patient));
        assertEquals(new AddPatientCommand(patient), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeletePatientCommand command = (DeletePatientCommand) parser.parseCommand(
                DeletePatientCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePatientCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteDoctor() throws Exception {
        DeleteDoctorCommand command = (DeleteDoctorCommand) parser.parseCommand(
                DeleteDoctorCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteDoctorCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        EditPatientCommand command = (EditPatientCommand) parser.parseCommand(EditPatientCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditPatientCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        SearchPatientCommand command = (SearchPatientCommand) parser.parseCommand(
                SearchPatientCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new SearchPatientCommand(new PatientNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_history() throws Exception {
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD) instanceof HistoryCommand);
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD + " 3") instanceof HistoryCommand);

        try {
            parser.parseCommand("histories");
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(MESSAGE_UNKNOWN_COMMAND, pe.getMessage());
        }
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListPatientCommand.COMMAND_WORD) instanceof ListPatientCommand);
        assertTrue(parser.parseCommand(ListPatientCommand.COMMAND_WORD + " 3") instanceof ListPatientCommand);
    }

    @Test
    public void parseCommand_listDoctor() throws Exception {
        assertTrue(parser.parseCommand(ListDoctorCommand.COMMAND_WORD) instanceof ListDoctorCommand);
        assertTrue(parser.parseCommand(ListDoctorCommand.COMMAND_WORD + " 3") instanceof ListDoctorCommand);
    }

    @Test
    public void parseCommand_select() throws Exception {
        SelectCommand command = (SelectCommand) parser.parseCommand(
                SelectCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new SelectCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_selectDoctor() throws Exception {
        SelectDoctorCommand command = (SelectDoctorCommand) parser.parseCommand(
                SelectDoctorCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new SelectDoctorCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_redoCommandWord_returnsRedoCommand() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand("redo 1") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_undoCommandWord_returnsUndoCommand() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand("undo 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        parser.parseCommand("");
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_UNKNOWN_COMMAND);
        parser.parseCommand("unknownCommand");
    }
}
