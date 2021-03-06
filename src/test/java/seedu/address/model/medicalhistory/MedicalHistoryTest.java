package seedu.address.model.medicalhistory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MedicalHistoryTest {

    @Test
    public void equals() {
        MedicalHistory mh1 = new MedicalHistory("1", "1", new Date("2018-05-05"), new WriteUp("testWriteUp"));
        MedicalHistory mh2 = new MedicalHistory("1", "2", new Date("2018-05-05"), new WriteUp("testWriteUp"));
        MedicalHistory mh3 = new MedicalHistory("1", "1", new Date("2018-05-06"), new WriteUp("testWriteUp"));
        MedicalHistory mh1Copy = new MedicalHistory("1", "1", new Date("2018-05-05"), new WriteUp("testWriteUp"));

        // same object -> returns true
        assertTrue(mh1.equals(mh1));

        // same values -> returns true
        assertTrue(mh1.equals(mh1Copy));

        // null -> returns false
        assertFalse(mh1.equals(null));

        // different type -> returns false
        assertFalse(mh1.equals(5));

        // different id -> returns false
        assertFalse(mh1.equals(mh2));

        // different date -> returns false
        assertFalse(mh1.equals(mh3));
    }

}
