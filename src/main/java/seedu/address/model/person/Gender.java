package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidgender(String)}
 */
public class Gender {

    public static final String MESSgender_CONSTRAINTS =
            "Gender should only contain character, and it should only be Male or Female";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender string.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidgender(gender), MESSgender_CONSTRAINTS);
        value = gender;
    }

    /**
     * Returns true if a given string is a valid gender number.
     */
    public static boolean isValidgender(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && value.equals(((Gender) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
