package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionDescriptorTest {

    @DisplayName("SessionDescriptor 인스턴스 생성")
    @Test
    public void testConstructor() {
        assertDoesNotThrow(() -> new SessionDescriptor(new SessionPeriod(), new SessionEnrollPolicy(), new SessionImages()));
    }
}
