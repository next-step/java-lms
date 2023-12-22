package nextstep.courses.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.ImageExtension;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionPaymentType;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.Sessions;
import nextstep.courses.dto.CoverImageDTO;
import nextstep.courses.dto.DurationDTO;
import nextstep.courses.dto.EnrollmentDTO;
import nextstep.courses.dto.NsUserLimitDTO;
import nextstep.courses.dto.NsUsersDTO;
import nextstep.courses.dto.SessionDTO;
import nextstep.courses.dto.SessionPaymentDTO;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionServiceTest {

    private List<NsUser> nsUserList;
    private SessionService sessionService;
    private EnrollmentRepository enrollmentRepository;

    @BeforeEach
    void setUp() {
        nsUserList = new ArrayList<>();
        enrollmentRepository = new EnrollmentRepository() {
            @Override
            public int save(Long userId, Long sessionId) {
                nsUserList.add(new NsUser(userId, "test","test","test","test"));
                return 0;
            }

            @Override
            public NsUsers findBySessionId(Long id) {
                return null;
            }
        };
        sessionService = new SessionService(new SessionRepository() {
            @Override
            public int save(SessionDTO session) {
                return 0;
            }

            @Override
            public Session findById(Long id) {
                return null;
            }

            @Override
            public Optional<Sessions> findByCourseId(Long courseId) {
                return Optional.empty();
            }
        },
                enrollmentRepository
        );
    }

    @Test
    void crud() {
        sessionService.enroll(NsUserTest.JAVAJIGI, new SessionDTO(1L, 1L,
                new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D),
                new DurationDTO(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                new SessionPaymentDTO(SessionPaymentType.FREE, 0L),
                SessionStatus.ENROLLING,
                new EnrollmentDTO(new NsUsersDTO(new ArrayList<>(List.of(NsUserTest.JAVAJIGI))), new NsUserLimitDTO(0)),
                LocalDateTime.now(),
                LocalDateTime.now()
                ));
        assertThat(nsUserList).contains(NsUserTest.JAVAJIGI);
    }
}
