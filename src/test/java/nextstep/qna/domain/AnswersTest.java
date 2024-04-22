package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

	private Answers sameOwnerAnswers;
	private Answers otherOwnerAnswers;

	@BeforeEach
	public void setUp() {
		List<Answer> otherAnswerList = new ArrayList<>();
		otherAnswerList.add(AnswerTest.A1);
		otherAnswerList.add(AnswerTest.A2);
		otherOwnerAnswers = new Answers(otherAnswerList);

		List<Answer> sameAnswerList = new ArrayList<>();
		sameAnswerList.add(AnswerTest.A1);
		sameAnswerList.add(AnswerTest.A1);
		sameOwnerAnswers = new Answers(sameAnswerList);
	}

	@Test
	public void 다른사람이_쓴글이_존재시_확인_테스트() {
		assertThat(sameOwnerAnswers.isOwners(NsUserTest.JAVAJIGI)).isFalse();
	}

	@Test
	public void 자신만_쓴글이_존재시_테스트() {
		assertThat(sameOwnerAnswers.isOwners(NsUserTest.JAVAJIGI)).isTrue();
	}

	@Test
	public void 답변_전체삭제_실패_테스트() {
		assertThatThrownBy(() -> otherOwnerAnswers.setDeleteAnswers(NsUserTest.JAVAJIGI));
	}

	@Test
	public void 답변_전체삭제_성공_테스트() throws CannotDeleteException {
		sameOwnerAnswers.setDeleteAnswers(NsUserTest.JAVAJIGI);
		assertThat(sameOwnerAnswers.getAnswer(0).isDeleted()).isTrue();
	}

}
