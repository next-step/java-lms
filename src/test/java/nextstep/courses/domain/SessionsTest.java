package nextstep.courses.domain;

import static nextstep.courses.domain.CoverImageBuilder.aCoverImage;
import static nextstep.courses.domain.SessionBuilder.aSession;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import nextstep.users.domain.NsUserTest;

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
        Session session = aSession().build();
        sessions.add(session);
        assertThat(sessionSet).contains(session);
    }

    @ParameterizedTest
    @DisplayName("Sessions 추가")
    @ValueSource(longs = {1L, 2L, 3L, 4L})
    void addAll(Long expected) {
        Set<Session> sessionSet = new LinkedHashSet<>(List.of(
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

        Set<Session> adderSessionSet = new LinkedHashSet<>(List.of(
                aSession()
                        .withId(3L)
                        .withAmountOfPrice(10000L)
                        .withSessionPaymentType(SessionPaymentType.PAID)
                        .withUserList(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI))
                        .withLimitOfUsers(10)
                        .build(),
                aSession()
                        .withId(4L)
                        .withAmountOfPrice(10000L)
                        .withSessionPaymentType(SessionPaymentType.PAID)
                        .withUserList(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI))
                        .withLimitOfUsers(10)
                        .withCoverImage(aCoverImage().withName("sanjigi.png").build())
                        .build()
        ));
        Sessions actual = new Sessions(sessionSet);
        Sessions data = new Sessions(adderSessionSet);
        actual.addAll(data);
        assertThat(sessionSet).anyMatch(e->e.isSameId(expected));
    }
}
