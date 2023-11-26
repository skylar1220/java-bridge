package bridge.view.validator;

import bridge.util.validator.StringValidator;

public class InputValidator {
    private static InputValidator inputValidator;

    private InputValidator() {
    }

    public static InputValidator getInstance() {
        if (inputValidator == null) {
            return new InputValidator();
        }
        return inputValidator;
    }

    public void validateBridegeSize(String template, String target) {
        StringValidator.validateBlank(template, target);
        StringValidator.validateNumeric(template, target);
        StringValidator.validateIntegerRange(template, target);
    }

    public void validateMoving(String moving, String target) {
        StringValidator.validateBlank(moving, target);
    }

    public void validateRetryOption(String retry, String target) {
        StringValidator.validateBlank(retry, target);
    }
}
