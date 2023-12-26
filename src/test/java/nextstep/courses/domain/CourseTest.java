package nextstep.courses.domain;

import static nextstep.courses.domain.CoverImageBuilder.aCoverImage;
import static nextstep.courses.domain.SessionBuilder.aSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import nextstep.users.domain.NsUserTest;
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
                aSession()
                        .withId(1L)
                        .withAmountOfPrice(10000L)
                        .withSessionPaymentType(SessionPaymentType.PAID)
                        .withUserList(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI))
                        .withLimitOfUsers(10)
                        .build(),
                aSession()
                        .withId(2L)
                        .withAmountOfPrice(10000L)
                        .withSessionPaymentType(SessionPaymentType.PAID)
                        .withUserList(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI))
                        .withLimitOfUsers(10)
                        .withCoverImage(aCoverImage().withName("sanjigi.png").build())
                        .build()
        ));

        Sessions expectedSessions = new Sessions(expectedSessionSet);

        course.addSessions(expectedSessions);

        assertIterableEquals(sessionSet, expectedSessionSet);
    }
}
