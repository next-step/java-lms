package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.*;
import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;

public class AnswersTest {

	@DisplayName("대답 등록자와 삭제 요청자가 다르면 CannotDeleteException을 발생시킨다.")
	@Test
	void deleteAnswers_other_writer() {
		Answers answers = new Answers();
		answers.add(A2);

		assertThatThrownBy(
			() -> answers.deleteAnswers(
				JAVAJIGI, LocalDateTime.of(2023, 11, 27, 1, 0, 0)
			)
		)
			.isInstanceOf(CannotDeleteException.class);
	}
}
