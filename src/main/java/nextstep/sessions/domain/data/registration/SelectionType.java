package nextstep.sessions.domain.data.registration;

import java.util.Arrays;

import nextstep.sessions.domain.exception.CannotSelectRegistrationException;

public enum SelectionType {
    SELECTION("Y", "선발"),
    BEFORE_SELECTION("N", "미선발");

    private final String code;
    private final String title;

    SelectionType(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public static SelectionType valueOfCode(String code) {
        return Arrays.stream(values())
            .filter(selectionType -> selectionType.code.equals(code))
            .findFirst()
            .orElseThrow(() -> new CannotSelectRegistrationException("일치하는 코드 값이 없습니다."));
    }

    public String code() {
        return code;
    }

    public boolean isBeforeSelection() {
        return this == BEFORE_SELECTION;
    }

    public boolean isSelected() {
        return this == SELECTION;
    }
}
