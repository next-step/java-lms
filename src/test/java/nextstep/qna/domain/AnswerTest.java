package nextstep.qna.domain;

import static nextstep.qna.ExceptionMessage.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
	@Nested
	@DisplayName("deleteByUser() 테스트")
	class DeleteByUserTest {

		public final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
		public final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

		@Test
		@DisplayName("Answer가 본인이 작성하지 않으면 삭제할 수 없다.")
		void isNotMyAnswer() {
			assertThatThrownBy(() -> A1.validateDeletable(NsUserTest.SANJIGI)).isInstanceOf(
				CannotDeleteException.class);
		}

		@Test
		@DisplayName("본인이 작성한 답변은 삭제할 수 있다.")
		void isMyAnswer() {
			assertThatNoException().isThrownBy(() -> A1.validateDeletable(NsUserTest.JAVAJIGI));
		}
	}
}