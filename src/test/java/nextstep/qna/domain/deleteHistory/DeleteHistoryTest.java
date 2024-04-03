package nextstep.qna.domain.deleteHistory;

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
  void deleteHistoryTest() {

    Question question = new Question(NsUserTest.JAVAJIGI, "질문입니다.", "내용입니다");
    Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
    Answer answer2 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents2");

    Answers answers = new Answers();
    answers.add(answer1);
    answers.add(answer2);

    List<DeleteHistory> deleteHistories = DeleteHistory.makeDeleteHistories(
        question.getId(),
        question.getWriter(),
        answers);

    assertThat(deleteHistories).hasSize(3);
  }
}
