package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.medicalhistory.Date;
import seedu.address.model.medicalhistory.MedicalHistory;
import seedu.address.model.medicalhistory.WriteUp;

/**
 * Jackson-friendly version of {@link MedicalHistory}.
 */
public class JsonAdaptedMedicalHistory {

    private final String medHistId;
    private final String patientId;
    private final String doctorId;
    private final String date;
    private final String writeUp;

    /**
     * Constructs a {@code JsonAdaptedMedicalHistory} with the given medical history details.
     */
    @JsonCreator
    public JsonAdaptedMedicalHistory(@JsonProperty("medHistId") String medHistId,
                                     @JsonProperty("patientId") String patientId,
                                     @JsonProperty("doctorId") String doctorId,
                                     @JsonProperty("date") String date,
                                     @JsonProperty("writeUp") String writeUp) {
        this.medHistId = medHistId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.writeUp = writeUp;
    }

    /**
     * Converts a given {@code MedicalHistory} into this class for Jackson use.
     */
    public JsonAdaptedMedicalHistory(MedicalHistory source) {
        medHistId = source.getMedHistId();
        patientId = source.getPatientId();
        doctorId = source.getDoctorId();
        date = source.getDate().value;
        writeUp = source.getWriteUp().value;
    }

    /**
     * Converts this Jackson-friendly adapted medical history object into the model's {@code MedicalHistory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted medical history.
     */
    public MedicalHistory toModelType() throws IllegalValueException {
        /*
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        */
        final Date modelDate = new Date(this.date);
        final WriteUp modelWriteUp = new WriteUp(this.writeUp);
        return new MedicalHistory(patientId, doctorId, modelDate, modelWriteUp);
    }


}


