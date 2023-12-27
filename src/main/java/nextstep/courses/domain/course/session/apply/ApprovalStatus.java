package nextstep.courses.domain.course.session.apply;

import java.util.Arrays;

public enum ApprovalStatus {
    WAIT("대기중"),
    APPROVED("승인"),
    CANCELED("취소");

    private final String description;

    ApprovalStatus(String description) {
        this.description = description;
    }

    public static ApprovalStatus find(String name) {
        return Arrays.stream(values())
                .filter(status -> status.name().equals(name))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                String.format("허용하는 값은 다음과 같습니다.\n %s", descriptions())
                        )
                );
    }

    public static String descriptions() {
        StringBuilder sb = new StringBuilder();
        for (ApprovalStatus approvalStatus : values()) {
            sb.append(approvalStatus.description).append(", ");
        }
        sb.setLength(sb.length() - 2);

        return sb.toString();
    }

    public boolean approved() {
        return this == APPROVED;
    }

    public boolean canceled() {
        return this == CANCELED;
    }
}
