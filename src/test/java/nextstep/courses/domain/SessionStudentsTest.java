package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class SessionStudentsTest {

    private Student javajigi = new Student(1L, NsUserTest.JAVAJIGI.getId());
    private Student sanjigi = new Student(1L, NsUserTest.SANJIGI.getId());
    private SessionStudents students = new SessionStudents(new ArrayList<>(List.of(javajigi)));

    @Test
    public void isWithinCapacity_유료강의_정원_마감_시_False_반환테스트() {
        SessionType paidSessionType = SessionType.determineSessionType(true, 1L, 1, 100);

        assertThat(students.isWithinCapacity(paidSessionType)).isFalse();
    }

    @Test
    public void isWithinCapacity_유료강의_정원_남을_시_True_반환테스트() {
        SessionType paidSessionType = SessionType.determineSessionType(true, 1L, 2, 100);

        assertThat(students.isWithinCapacity(paidSessionType)).isTrue();
    }

    @Test
    public void isWithinCapacity_무료강의_정원_마감되어도_True_반환테스트() {
        SessionType freeSessionType = SessionType.determineSessionType(false, 1L, null, null);

        assertThat(students.isWithinCapacity(freeSessionType)).isTrue();
    }

    @Test
    public void enroll_Students_사이즈_증가_테스트() {
        students.enroll(sanjigi);

        assertThat(students.getStudents().size()).isEqualTo(2);
    }
}
