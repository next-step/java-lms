package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SessionsTest {
    @Test
    @DisplayName("Sessions 생성")
    void create() {
        assertThat(new Sessions()).isInstanceOf(Sessions.class);
    }

    @Test
    @DisplayName("단일 Session 추가")
    void add() {
        Set<Session> sessionSet = new LinkedHashSet<>();
        Sessions sessions = new Sessions(sessionSet);
        Session session = new Session();
        sessions.add(session);
        assertThat(sessionSet).contains(session);
    }

    @ParameterizedTest
    @DisplayName("Sessions 추가")
    @ValueSource(longs = {1L, 2L, 3L, 4L})
    void addAll(Long expected) {
        Set<Session> sessionSet = new LinkedHashSet<>(List.of(
                new Session(
                        1L,
                        new Course(),
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
                        new Course(),
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

        Set<Session> adderSessionSet = new LinkedHashSet<>(List.of(
                new Session(
                        3L,
                        new Course(),
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
                        4L,
                        new Course(),
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
        Sessions actual = new Sessions(sessionSet);
        Sessions data = new Sessions(adderSessionSet);
        actual.addAll(data);
        assertThat(sessionSet).anyMatch(e->e.isSameId(expected));
    }
}
