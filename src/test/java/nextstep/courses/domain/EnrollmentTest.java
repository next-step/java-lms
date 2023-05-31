package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EnrollmentTest {

    @Test
    void 생성자테스트() {
        NsUser jerry = new NsUser(1L, "jerry", "password", "name", "jerry@nextstep.com");
        Assertions.assertThat(new Enrollment(jerry,1L)).isInstanceOf(Enrollment.class);
    }

    @ParameterizedTest(name = "{displayName} [{index}] {arguments}")
    @NullAndEmptySource
    void NullOrEmpty입력(String input) {
        assertThatThrownBy(() -> {
            Student student = new Student(input,"제리","jerry@gamil.com");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("입력된 학생ID가 Null 또는 빈값 입니다.");

        assertThatThrownBy(() -> {
            Student student = new Student("jerry", input,"jerry@gamil.com");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("입력된 이름이 Null 또는 빈값 입니다.");

        assertThatThrownBy(() -> {
            Student student = new Student("jerry", "제리", input);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("입력된 이메일주소가 Null 또는 빈값 입니다.");
    }

    @ParameterizedTest(name = "{displayName} [{index}] {arguments}")
    @ValueSource(strings={"abcd", "jerry$gmail.com", "jerry.com"})
    void 이메일주소검증(String email) {
        assertThatThrownBy(() -> {
            Student student = new Student("jerry", "제리", email);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("입력된 이메일주소가 이메일주소 형식이 아닙니다.");
    }
}
