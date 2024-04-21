package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class SessionApplyTest {

    @Test
    void 수강신청_모집_중이_아닐_때() {
        Session session = new Session(
                1L, SessionTest.startAt, SessionTest.endAt, SessionTest.image, SessionTest.progress
        );
        Student student = new Student(1L);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionApply.apply(session, student))
                .withMessageMatching("모집 중인 강의가 아닙니다.");
    }

    @Test
    void 유료과정_수강인원_제한() {
        SessionType sessionType = new SessionType(false);
        sessionType.setMaxStudents(0);

        Session session = new Session(
                1L, SessionTest.startAt, SessionTest.endAt, SessionTest.image, new SessionProgress("모집중"), sessionType
        );

        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionApply.apply(session, new Student(1L)))
                .withMessageMatching("수강 신청 인원 초과 과정입니다.");
    }

    @Test
    void 유료강의_수강신청_성공() {
        int maxStudents = 1;
        SessionType sessionType = new SessionType(false);
        sessionType.setMaxStudents(maxStudents);
        Student student = new Student(1L);
        Students students = new Students(new ArrayList<>());

        Session session = new Session(
                1L, new SessionDate(SessionTest.startAt, SessionTest.endAt), SessionTest.image,
                new SessionProgress("모집중"), sessionType, students
        );

        SessionApply.apply(session, student);
        assertThat(students.isContains(student)).isTrue();
    }

    @Test
    void 무료강의_수강신청_성공() {
        SessionType sessionType = new SessionType(true);
        Student student = new Student(1L);
        Students students = new Students(new ArrayList<>());

        Session session = new Session(
                1L, new SessionDate(SessionTest.startAt, SessionTest.endAt), SessionTest.image,
                new SessionProgress("모집중"), sessionType, students
        );

        SessionApply.apply(session, student);
        assertThat(students.isContains(student)).isTrue();
    }
}
