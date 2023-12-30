package nextstep.courses.domain;

import static nextstep.courses.domain.CourseBuilder.aCourse;
import static nextstep.courses.domain.CoverImageBuilder.aCoverImage;
import static nextstep.courses.domain.SessionBuilder.aSession;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionBuilderTest {
    @Test
    @DisplayName("id를 지정하여 Session를 생성")
    void withId() {
        SessionBuilder builder = aSession().withId(1L);
        Session session = builder.build();
        assertThat(session).isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("course을 지정하여 Session를 생성")
    void withCourse() {
        SessionBuilder builder = aSession().withCourse(aCourse().build());
        Session session = builder.build();
        assertThat(session).isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("sessionPaymentType를 지정하여 Session를 생성")
    void withSessionPaymentType() {
        SessionBuilder builder = aSession().withSessionPaymentType(SessionPaymentType.FREE);
        Session session = builder.build();
        assertThat(session).isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("amountOfPrice를 지정하여 Session를 생성")
    void withAmountOfPrice() {
        SessionBuilder builder = aSession().withAmountOfPrice(0L);
        Session session = builder.build();
        assertThat(session).isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("limitOfUsers를 지정하여 Session를 생성")
    void withLimitOfUsers() {
        SessionBuilder builder = aSession().withLimitOfUsers(0);
        Session session = builder.build();
        assertThat(session).isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("userList를 지정하여 Session를 생성")
    void withUserList() {
        SessionBuilder builder = aSession().withUserList(new ArrayList<>());
        Session session = builder.build();
        assertThat(session).isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("duration를 지정하여 Session를 생성")
    void withDuration() {
        SessionBuilder builder = aSession().withDuration(new Duration(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)));
        Session session = builder.build();
        assertThat(session).isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("status를 지정하여 Session를 생성")
    void withSessionStatus() {
        SessionBuilder builder = aSession().withSessionStatus(SessionStatus.READY);
        Session session = builder.build();
        assertThat(session).isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("coverImage를 지정하여 Session를 생성")
    void withCoverImage() {
        SessionBuilder builder = aSession().withCoverImage(aCoverImage().build());
        Session session = builder.build();
        assertThat(session).isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("createdAt를 지정하여 Session를 생성")
    void withCreatedAt() {
        SessionBuilder builder = aSession().withCreatedAt(LocalDateTime.now());
        Session session = builder.build();
        assertThat(session).isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("updatedAt를 지정하여 Session를 생성")
    void withUpdatedAt() {
        SessionBuilder builder = aSession().withUpdatedAt(null);
        Session session = builder.build();
        assertThat(session).isInstanceOf(Session.class);
    }
}
