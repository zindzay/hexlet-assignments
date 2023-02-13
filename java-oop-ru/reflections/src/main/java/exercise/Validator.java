package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public final class Validator {
    private static final String NULL_ERROR_FORMAT_STRING = "can not be null";
    private static final String LENGTH_ERROR_FORMAT_STRING = "length less than %d";

    public static List<String> validate(Address address) {
        List<String> nullFields = new ArrayList<>();

        try {
            for (Field field : address.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(NotNull.class)) {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        nullFields.add(field.getName());
                    }
                }
            }

            return nullFields;
        } catch (IllegalAccessException e) {
            return List.of();
        }
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> errorsByField = new HashMap<>();

        try {
            for (Field field : address.getClass().getDeclaredFields()) {
                List<String> errors = new ArrayList<>();
                field.setAccessible(true);

                if (field.isAnnotationPresent(NotNull.class) && field.get(address) == null) {
                    errors.add(NULL_ERROR_FORMAT_STRING);
                }

                if (field.isAnnotationPresent(MinLength.class)) {
                    int minLength = field.getAnnotation(MinLength.class).minLength();
                    if (minLength > ((String) field.get(address)).length()) {
                        errors.add(String.format(LENGTH_ERROR_FORMAT_STRING, minLength));
                    }

                }

                if (!errors.isEmpty()) {
                    errorsByField.put(field.getName(), errors);
                }
            }

            return errorsByField;
        } catch (IllegalAccessException e) {
            return Map.of();
        }
    }
}
// END
