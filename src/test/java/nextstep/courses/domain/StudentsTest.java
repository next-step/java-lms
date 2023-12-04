package nextstep.courses.domain;

import static nextstep.users.domain.NsUserTest.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentsTest {

	private Students students;

	@BeforeEach
	void setUp() {
		students = new Students();
	}

	@DisplayName("add 메서드는 사용자를 수강생 리스트에 더하는 행위를 한다.")
	@Test
	void valid_add() {
		students.add(JAVAJIGI);
		Assertions.assertThat(students.number()).isEqualTo(1);
	}

	@DisplayName("number메서드는 수강생들의 숫자를 반환하는 행위를 한다.")
	@Test
	void valid_number() {
		students.add(JAVAJIGI);
		students.add(SANJIGI);
		Assertions.assertThat(students.number()).isEqualTo(2);
	}
}
