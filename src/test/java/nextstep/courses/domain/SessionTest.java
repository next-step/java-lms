package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.dto.SessionDTO;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {
    @Test
    @DisplayName("강의 생성")
    void create() {
        assertThat(new Session())
                .isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("강의 생성시 가격 타입 & 가격 매칭 실패 에러 던짐")
    void create_throw_exception_sessionPaymentType_price() {
        assertThatThrownBy(() ->
                new Session(0L,
                        CourseTest.C1,
                        0L,
                        SessionPaymentType.PAID,
                        new NsUsers(List.of(NsUserTest.JAVAJIGI)),
                        1,
                        new Duration(
                                LocalDateTime.now(),
                                LocalDateTime.now().plusMonths(1L)
                        ),
                        SessionStatus.READY,
                        new CoverImage("pobi.png", 500L, 300D, 200D),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Enrollment의 nsUsers를 새 NsUsers로 교체")
    void replaceEnrollmentNsUsers() {
        List<NsUser> userList = new ArrayList<>(List.of(NsUserTest.JAVAJIGI));
        Session session = new Session(0L,
                CourseTest.C1,
                0L,
                SessionPaymentType.FREE,
                new NsUsers(userList),
                1,
                new Duration(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMonths(1L)
                ),
                SessionStatus.READY,
                new CoverImage("pobi.png", 500L, 300D, 200D),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        NsUsers users = new NsUsers(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI));
        session.replaceEnrollmentNsUsers(e->users);
        assertThat(userList).contains(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI);
    }

    @Test
    @DisplayName("전용 DTO 모델을 반환함")
    void toDto() {
        assertThat(new Session().toDto())
                .isInstanceOf(SessionDTO.class);
    }
}
