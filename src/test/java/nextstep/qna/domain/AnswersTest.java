package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

	@Test
	public void 다른사람이_쓴글이_존재시_확인_테스트() {
		List<Answer> answerList = new ArrayList<>();
		answerList.add(AnswerTest.A1);
		answerList.add(AnswerTest.A2);
		Answers answers = new Answers(answerList);
		assertThatThrownBy(() -> answers.checkOwner(NsUserTest.SANJIGI))
				.isInstanceOf(CannotDeleteException.class)
				.hasMessageContaining("다른 사람");
	}

	@Test
	public void 자신만_쓴글이_존재시_테스트() {
		List<Answer> answerList = new ArrayList<>();
		answerList.add(AnswerTest.A1);
		answerList.add(AnswerTest.A1);
		Answers answers = new Answers(answerList);
		assertThat(answers.isOwners(NsUserTest.JAVAJIGI)).isEqualTo(true);
	}


}
