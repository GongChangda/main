package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.medicalhistory.MedicalHistory;
import seedu.address.model.medicalhistory.WriteUp;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Specialisation;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code docX} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Gender("M"), new Age("25"), new Phone("87438807"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("foodpoisoning")),
            new Patient(new Name("Bernice Yu"), new Gender("F"), new Age("7"), new Phone("99272758"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("highbloodpressure", "stroke")),
            new Patient(new Name("Charlotte Oliveiro"), new Gender("F"), new Age("101"), new Phone("93210283"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("others")),
            new Patient(new Name("David Li"), new Gender("M"), new Age("28"), new Phone("91031282"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("diabetes")),
            new Patient(new Name("Irfan Ibrahim"), new Gender("M"), new Age("60"), new Phone("92492021"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("lungcancer")),
            new Patient(new Name("Roy Balakrishnan"), new Gender("M"), new Age("110"), new Phone("92624417"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("flu"))
        };
    }

    public static Doctor[] getSampleDoctors() {
        return new Doctor[] {
            new Doctor(new Name("Alex Yeoh"), new Phone("87438807"), new Gender("M"), new Age("21"),
                    getSpecSet("acupuncture")),
            new Doctor(new Name("Bernice Yu"), new Phone("99272758"), new Gender("F"), new Age("32"),
                    getSpecSet("acupuncture, general")),
            new Doctor(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Gender("F"), new Age("41"),
                    getSpecSet("massage")),
            new Doctor(new Name("David Li"), new Phone("91031282"), new Gender("M"), new Age("43"),
                    getSpecSet("massage, acupuncture")),
            new Doctor(new Name("Ivan Teo"), new Phone("92492021"), new Gender("M"), new Age("37"),
                    getSpecSet("acupuncture")),
            new Doctor(new Name("Roy Balakrishnan"), new Phone("92624417"), new Gender("M"), new Age("28"),
                    getSpecSet("general"))
        };
    }

    public static MedicalHistory[] getSampleMedHists() {
        return new MedicalHistory[] {
            new MedicalHistory(null, null, new Name("Alex Yeoh"), new WriteUp("Fever"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        for (MedicalHistory sampleMedHist : getSampleMedHists()) {
            sampleAb.addMedHist(sampleMedHist);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Set<Specialisation> getSpecSet(String... strings) {
        return Arrays.stream(strings)
                .map(Specialisation::new)
                .collect(Collectors.toSet());
    }

    public static List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<Appointment>();
        appointments.add(new Appointment(1, 1, "2019-03-25", "9"));
        appointments.add(new Appointment(2, 2, "2019-04-25", "10"));

        return appointments;
    }
}
