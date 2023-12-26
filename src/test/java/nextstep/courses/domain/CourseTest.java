package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {
    public static final Course C1 = new Course(1L, "JAVA, TDD with Clean Code", NsUserTest.JAVAJIGI.getId(), LocalDateTime.now(), LocalDateTime.now());
    public static final Course C2 = new Course(2L, "Kotlin, TDD with Clean Code", NsUserTest.SANJIGI.getId(), LocalDateTime.now(), LocalDateTime.now());

    @Test
    @DisplayName("과정 생성")
    void create() {
        assertThat(new Course()).isInstanceOf(Course.class);
    }

    @Test
    @DisplayName("Sessions 추가")
    void addSessions() {
        Set<Session> sessionSet = new LinkedHashSet<>();
        Sessions sessions = new Sessions(sessionSet);
        Course course = new Course(1L, "JAVA, TDD with Clean Code", 1L, sessions);
        Set<Session> expectedSessionSet = new LinkedHashSet<>(List.of(
                new Session(
                        1L,
                        course,
                        10000L,
                        SessionPaymentType.PAID,
                        new NsUsers(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)),
                        10,
                        new Duration(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                        SessionStatus.READY,
                        new CoverImage("pobi.png", 500L, 300D, 200D),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ),
                new Session(
                        2L,
                        course,
                        10000L,
                        SessionPaymentType.PAID,
                        new NsUsers(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)),
                        10,
                        new Duration(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                        SessionStatus.READY,
                        new CoverImage("sanjigi.png", 500L, 300D, 200D),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        ));

        Sessions expectedSessions = new Sessions(expectedSessionSet);

        course.addSessions(expectedSessions);

        assertIterableEquals(sessionSet, expectedSessionSet);
    }
}
