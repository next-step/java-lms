package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @Test
    void session_수강신청강의상태확인(){
        Session session = new Session(1L, 1L, LocalDate.of(2023,12,01)
                ,LocalDate.of(2024,6,01), 150_000L
                ,new SessionImage(1L, "jpg", 1024, 300, 200)
                ,SessionStatus.FINISHED
                ,SessionFeeType.FREE
                ,new SessionStudentCount(0));
        assertThatThrownBy(()-> session.register(new Payment())).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void session_수강신청유료강의인원확인(){
        Session session = new Session(1L, 1L, LocalDate.of(2023,12,01)
                ,LocalDate.of(2024,6,01), 150_000L
                ,new SessionImage(1L, "jpg", 1024, 300, 200)
                ,SessionStatus.RECRUITING
                ,SessionFeeType.PAID
                ,new SessionStudentCount(6,5));
        assertThatThrownBy(()-> session.register(new Payment())).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void session_수강신청수강료결제금액확인(){
        Session session = new Session(1L, 1L, LocalDate.of(2023,12,01)
                ,LocalDate.of(2024,6,01), 150_000L
                ,new SessionImage(1L, "jpg", 1024, 300, 200)
                ,SessionStatus.RECRUITING
                ,SessionFeeType.PAID
                ,new SessionStudentCount(6,5));
        assertThatThrownBy(()-> session.register(new Payment("abc", 1L, 123L, 200_000L))).isInstanceOf(IllegalArgumentException.class);
    }
}
