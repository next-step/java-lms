package nextstep.students.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum StudentApprovalType {
    APPLICATION("신청"),
    APPROVED("승인"),
    REJECTED("반려");

    private static final Map<String, StudentApprovalType> VALUE_MAP
            = Arrays.stream(StudentApprovalType.values())
            .collect(Collectors.toUnmodifiableMap(StudentApprovalType::getName, o -> o));

    private final String name;

    StudentApprovalType(String name) {
        this.name = name;
    }

    public static StudentApprovalType find(String name) {
        if (VALUE_MAP.containsKey(name)) {
            return VALUE_MAP.get(name);
        }
        throw new IllegalArgumentException("유효하지 않은 승인유형입니다.(" + name + ")");
    }

    public String getName() {
        return name;
    }

    public boolean isApproved() {
        return this == APPROVED;
    }

}
