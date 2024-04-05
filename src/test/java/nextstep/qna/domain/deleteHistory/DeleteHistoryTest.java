package nextstep.qna.domain.deleteHistory;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.answer.Answer;
import nextstep.qna.domain.answer.Answers;
import nextstep.qna.domain.question.Question;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {
  @Test
  @DisplayName("질문자와 답변자가 같은 경우" +
      "Answer 삭제되는지 검증")
  void deleteHistoryTest() throws CannotDeleteException {

    Answers answers = new Answers();
    Question question = new Question(1L, NsUserTest.JAVAJIGI, "질문입니다.", "내용입니다", answers);
    answers.add(new Answer(1L, NsUserTest.JAVAJIGI, question, "Answers Contents1"));
    answers.add(new Answer(2L, NsUserTest.JAVAJIGI, question, "Answers Contents2"));

    List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI);

    assertThat(deleteHistories).hasSize(3);
  }
}
