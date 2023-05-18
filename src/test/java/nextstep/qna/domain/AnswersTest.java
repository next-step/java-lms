package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswersTest {

	private Question question;

	@BeforeEach
	public void setUp() {
		this.question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
	}

	@DisplayName("다른 사람이 작성한 답변이 있다.")
	@Test
	void test1() {
		Answers answers = new Answers(new Answer(NsUserTest.SANJIGI, question, "answer1"));
		assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI, LocalDateTime.now())).isInstanceOf(CannotDeleteException.class);
	}

	@DisplayName("답변 삭제 성공")
	@Test
	void test2() throws CannotDeleteException {
		Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");
		Answer answer2 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");

		Answers answers = new Answers(answer1, answer2);

		answers.delete(NsUserTest.JAVAJIGI, LocalDateTime.now());

		assertThat(answer1.isDeleted()).isTrue();
		assertThat(answer2.isDeleted()).isTrue();
	}

	@DisplayName("답변 삭제 이력 생성")
	@Test
	void test3() throws CannotDeleteException {
		Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");
		Answer answer2 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");

		Answers answers = new Answers(answer1, answer2);

		LocalDateTime deleteTime = LocalDateTime.now();

		List<DeleteHistory> deleteHistories = answers.delete(NsUserTest.JAVAJIGI, deleteTime);

		assertThat(deleteHistories).containsExactly(
			new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), deleteTime),
			new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), deleteTime)
		);
	}
}
