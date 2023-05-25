package nextstep.lms.domain;

import nextstep.lms.domain.Session;
import nextstep.lms.domain.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @Test
    @DisplayName("Session_생성_test")
    public void Session_생성_test(){

        LocalDate startedAt = LocalDate.now();
        LocalDate endedAt = LocalDate.now();
        String coverImage = "image";
        Type type = Type.FREE;
        Status status = Status.READY;
        int maximumNumberOfStudents = 30;


        assertThat(new Session(startedAt, endedAt, coverImage, type, status, maximumNumberOfStudents)).isEqualTo(new Session(startedAt, endedAt, coverImage, type, status, maximumNumberOfStudents));
    }

    @Test
    @DisplayName("Validate_date_test")
    public void Validate_date_test(){
        LocalDate startedAt = LocalDate.of(2022, 11, 10);
        LocalDate endedAt = LocalDate.of(2021, 11, 10);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    new Session(startedAt, endedAt);
                });
    }

    @Test
    @DisplayName("register_method_test")
    public void register_method_test(){
        Session session = new Session(Status.RECRUIT);
        session.register();

        assertThat(session.getNumberOfRegisteredStudent()).isEqualTo(1);
    }

    @Test
    @DisplayName("validate_capacityStudent_test")
    public void validate_capacityStudent_test(){
        Session session = new Session(2);
        session.register();
        session.register();

        assertThatIllegalStateException()
                .isThrownBy(session::register);
    }

    @Test
    @DisplayName("validate_Status_test")
    public void validate_Status_test(){
        Session session = new Session(Status.READY);
        assertThatIllegalStateException()
                .isThrownBy(session::register);
    }
}
