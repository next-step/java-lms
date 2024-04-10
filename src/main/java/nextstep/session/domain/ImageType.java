package nextstep.session.domain;

import nextstep.session.InvalidImageConditionsException;

public enum ImageType {
    gif, jpg, jpeg, png, svg;

    public static boolean validateType(String input) throws InvalidImageConditionsException {
        try {
            ImageType.valueOf(input.trim().toLowerCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;

        }
    }
}
