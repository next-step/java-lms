package nextstep.qna.domain.answer;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.question.Question;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.answer.Answers.ANSWER_CAN_NOT_BE_DELETED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
  @Test
  @DisplayName("질문자와 답변자가 같은 경우" +
      "Answer 삭제되는지 검증")
  void answersTest() throws CannotDeleteException {
    NsUser given = NsUserTest.JAVAJIGI;
    Question question = new Question(given, "질문입니다.", "내용입니다");
    Answer answer1 = new Answer(given, question, "Answers Contents1");
    Answer answer2 = new Answer(given, question, "Answers Contents2");

    Answers answers = new Answers();
    answers.add(answer1);
    answers.add(answer2);

    assertThat(answers.deleteAll(given)).isTrue();
  }

  @Test
  @DisplayName("질문자와 답변자가 다른 경우" +
      "Answer 삭제 불가능 검증")
  void answersTest2() {
    NsUser given = NsUserTest.JAVAJIGI;
    Question question = new Question(given, "질문입니다.", "내용입니다");
    Answer answer1 = new Answer(given, question, "Answers Contents1");
    Answer answer2 = new Answer(given, question, "Answers Contents2");

    Answers answers = new Answers();
    answers.add(answer1);
    answers.add(answer2);

    assertThatThrownBy(() -> answers.deleteAll(NsUserTest.SANJIGI))
        .isInstanceOf(CannotDeleteException.class)
        .hasMessageContaining(ANSWER_CAN_NOT_BE_DELETED);
  }
}
