package utils;

import java.util.Optional;

public final class MiscUtils {

    private MiscUtils() {
    }

    /**
     * @param boolValue {@link Boolean}
     * @return boolean value of the provided 'boolValue'. As 'Boolean' could contain null, it returns false in such cases.
     */
    public static boolean evaluateBoolean(Boolean boolValue) {
        return Optional.ofNullable(boolValue).orElse(false);
    }


}
