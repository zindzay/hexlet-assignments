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

    public static List<String> validate(Object instance) {
        List<String> nullFields = new ArrayList<>();

        try {
            for (Field field : instance.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(NotNull.class)) {
                    field.setAccessible(true);
                    if (field.get(instance) == null) {
                        nullFields.add(field.getName());
                    }
                    field.setAccessible(false);
                }
            }

            return nullFields;
        } catch (IllegalAccessException e) {
            return List.of();
        }
    }

    public static Map<String, List<String>> advancedValidate(Object instance) {
        Map<String, List<String>> errorsByField = new HashMap<>();

        for (Field field : instance.getClass().getDeclaredFields()) {
            List<String> errors = getErrorsForField(field, instance);

            if (!errors.isEmpty()) {
                errorsByField.put(field.getName(), errors);
            }
            field.setAccessible(false);
        }

        return errorsByField;
    }

    public static List<String> getErrorsForField(Field field, Object instance) {
        List<String> errors = new ArrayList<>();

        try {
            field.setAccessible(true);
            var value = (String) field.get(instance);

            if (field.isAnnotationPresent(NotNull.class) && value == null) {
                errors.add(NULL_ERROR_FORMAT_STRING);
            }

            if (field.isAnnotationPresent(MinLength.class)) {
                int minLength = field.getAnnotation(MinLength.class).minLength();
                if (value != null && minLength > value.length()) {
                    errors.add(String.format(LENGTH_ERROR_FORMAT_STRING, minLength));
                }
            }

            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            return List.of();
        }

        return errors;
    }
}
// END
