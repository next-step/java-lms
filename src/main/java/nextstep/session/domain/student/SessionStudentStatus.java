package nextstep.session.domain.student;

import java.util.Arrays;

public enum SessionStudentStatus {
    REQUEST("승인요청 대기"),
    APPROVE("승인"),
    REFUSAL("거절");

    private final String studentStatus;

    SessionStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public static SessionStudentStatus of(String status) {
        return Arrays.stream(values()).filter(sessionStudentStatus -> status.equals(sessionStudentStatus.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 학생 상태입니다."));
    }
}
