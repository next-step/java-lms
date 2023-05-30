package nextstep.qna.domain;

import static nextstep.qna.domain.QuestionTest.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswersTest {
	private Answers answers;

	@BeforeEach
	void setUp() {
		Answer answer = new Answer(NsUserTest.JAVAJIGI, Q1, "contents");

		this.answers = new Answers(Q1);

		answers.add(answer);
	}

	@Test
	public void 생성() {
		assertThat(answers).isInstanceOf(Answers.class);
	}

	@Test
	public void 삭제_성공() throws CannotDeleteException {
		answers.deleteAll();

		assertThat(answers.isAllDeleted()).isTrue();
	}

	@Test
	public void 삭제_실패_질문자와_답변자가_다른_경우() {
		Answers answers = new Answers(Q1);

		Answer answer = new Answer(NsUserTest.SANJIGI, Q1, "contents");

		answers.add(answer);

		assertThatThrownBy(() -> answers.deleteAll())
			.isInstanceOf(CannotDeleteException.class);
	}
}
