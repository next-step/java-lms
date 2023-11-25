package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
	public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
	public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
	public static final Answer A3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");
	public static final Answer A4 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents4");

	private Answer answer1;
	private Answer answer2;

	@BeforeEach
	public void init() {
		answer1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "testContents");
		answer2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q2, "testContents");
	}

	@Test
	@DisplayName("delete_답변 작성자가 아닌 loginUser_throw exception")
	void 답변글작성자_로그인유저_불일치() {
		assertThatThrownBy(() -> {
			answer1.delete(NsUserTest.SANJIGI);
		}).isInstanceOf(CannotDeleteException.class).hasMessage("답변 작성자와 로그인 사용자가 일치하지 않습니다.");
	}

	@Test
	@DisplayName("delete_질문 작성자와 다른 작성자인 답변_throw exception")
	void 답변글작성자_질문글작성자_불일치() {
		assertThatThrownBy(() -> {
			answer2.delete(NsUserTest.JAVAJIGI);
		}).isInstanceOf(CannotDeleteException.class).hasMessage("답변 작성자와 질문 작성자가 일치하지 않습니다.");
	}

	@Test
	@DisplayName("delete_답변 작성자, 질문 작성자, 로그인 유저 일치_삭제 상태 true")
	void 답변작성자_질문작성자_로그인유저_일치() {
		assertThatNoException().isThrownBy(() -> answer1.delete(NsUserTest.JAVAJIGI));
		assertThat(answer1.isDeleted()).isTrue();
	}
}
