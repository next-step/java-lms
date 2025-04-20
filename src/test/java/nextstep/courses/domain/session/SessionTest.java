package nextstep.courses.domain.session;

import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SessionTest {

    @DisplayName("Session 인스턴스 생성")
    @Test
    public void testConstructor() throws IOException {
        SessionConstraint constraint = new SessionConstraint(100, 80);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new SessionEnrollPolicy(),
            new SessionImages()
        );

        assertDoesNotThrow(() -> new Session("1", constraint, descriptor));
    }
}
