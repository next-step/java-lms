package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionApplyTest {

    // 유료 - 수강생 결제 금액과 수강료 일치 시 수강 신청 가능
    @Test
    void 수강신청_모집_중이_아닐_때() {
        Session session = new Session(
                1L, SessionTest.startAt, SessionTest.endAt, SessionTest.image, SessionTest.progress
        );
        Student student = new Student(1L);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionApply().apply(session, student))
                .withMessageMatching("모집 중인 강의가 아닙니다.");
    }

    @Test
    void 수강신청_유료강의_수강인원_초과() {
        Session session = new Session(
                1L, SessionTest.startAt, SessionTest.endAt, SessionTest.image, new SessionProgress("모집중"), false
        );
        session.setMaxStudents(0);
        Student student = new Student(1L);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionApply().apply(session, student))
                .withMessageMatching("수강 인원 초과 과정입니다.");
    }
}
