package nextstep.users.domain;

import nextstep.courses.domain.registration.RegistrationStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StudentTest {
    public static Student student1 = new Student(1L, 1L);
    public static Student student2 = new Student(2L, 1L);

    @Test
    @DisplayName("수강 승인")
    void approve() {
        Student student = student1.approveSession();
        assertThat(student.getRegistrationStatus()).isEqualTo(RegistrationStatus.APPROVED);
    }
}
