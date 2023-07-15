package de.dreierschach.dsablatt.heldendokument.utility;

import com.vaadin.flow.data.binder.ErrorLevel;
import com.vaadin.flow.data.binder.Validator;
import org.apache.commons.lang3.StringUtils;

import static com.vaadin.flow.data.binder.ValidationResult.create;
import static com.vaadin.flow.data.binder.ValidationResult.ok;

public class BinderUtil {

    public static final String FLOAT_PATTERN = "^[0-9]*(\\.[0-9]+)?$";

    public static String mapToString(Integer value) {
        if (value == null || value == 0) {
            return "";
        }
        return String.valueOf(value);
    }

    public static String mapToString(Float value) {
        if (value == null || value == 0) {
            return "";
        }
        return String.valueOf(value);
    }

    public static int mapToInt(String s) {
        if (StringUtils.isEmpty(s) || !StringUtils.isNumericSpace(s)) {
            return 0;
        }
        return Integer.parseInt(s.trim());
    }

    public static float mapToFloat(String s) {
        if (s==null || !s.matches(FLOAT_PATTERN)) {
            return 0;
        }
        return Float.parseFloat(s.trim());
    }

    public static final Validator<String> validateInteger = (s, c) -> StringUtils.isNumericSpace(s) ? ok() : create("keine Ganzzahl", ErrorLevel.WARNING);
    public static final Validator<String> validateFloat = (s, c) -> s != null && s.matches(FLOAT_PATTERN) ? ok() : create("keine Kommazahl", ErrorLevel.WARNING);
}
