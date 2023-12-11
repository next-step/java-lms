package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.exception.PaidSessionException;
import nextstep.courses.exception.ParticipantsException;
import nextstep.courses.exception.SessionStateException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidSessionTest {

    private PaidSession paidSession;
    private List<CoverImage> coverImages;

    @BeforeEach
    void create() {
        LocalDateTime now = LocalDateTime.now();

        CoverImage coverImage1 = new CoverImage("images/test.gif", 1000_000, "gif", 300, 200, now);
        CoverImage coverImage2 = new CoverImage("images/test.jpeg", 1000_000, "jpeg", 300, 200, now);
        coverImages = new ArrayList<>(Arrays.asList(coverImage1, coverImage2));


        paidSession = new PaidSession(
                coverImages,
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 29),
                SessionProgressState.PROGRESSING,
                true,
                800_000L,
                1,
                now
        );
    }

    @Test
    @DisplayName("강의 수강신청은 강의 모집상태가 모집중일 때만 가능하다.")
    void 수강신청_강의모집상태_모집중_에러() {
        LocalDateTime now = LocalDateTime.now();
        PaidSession paidSession = new PaidSession(
                coverImages,
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 29),
                SessionProgressState.PREPARING,
                false,
                800_000L,
                1,
                LocalDateTime.now()
        );

        assertThatThrownBy(() -> paidSession.apply(Payment.ofPaid(2L, 1L, NsUserTest.SANJIGI, 800_000L)))
                .isInstanceOf(SessionStateException.class);


    }

    @Test
    @DisplayName("수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void 수강신청_결제금액_에러() {
        assertThatThrownBy(() -> paidSession.apply(Payment.ofPaid(1L, 1L, NsUserTest.SANJIGI, 700_000L)))
                .isInstanceOf(PaidSessionException.class);
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.")
    void 수강신청_인원Max_에러() {
        paidSession.apply(Payment.ofPaid(1L, 1L, NsUserTest.JAVAJIGI, 800_000L));

        assertThatThrownBy(() -> paidSession.apply(Payment.ofPaid(2L, 1L, NsUserTest.SANJIGI, 800_000L)))
                .isInstanceOf(ParticipantsException.class);
    }

    @Test
    @DisplayName("중복 수강신청은 불가능하다.")
    void 수강신청_동일신청_에러() {
        paidSession.apply(Payment.ofPaid(1L, 1L, NsUserTest.JAVAJIGI, 800_000L));

        assertThatThrownBy(() -> paidSession.apply(Payment.ofPaid(2L, 1L, NsUserTest.JAVAJIGI, 800_000L)))
                .isInstanceOf(ParticipantsException.class);
    }
}
