package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswersTest {

  public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
      "Answers Contents1");
  public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1,
      "Answers Contents2");

  @DisplayName("로그인한 User가 Answer들의 주인인지 확인한다.")
  @Test
  public void checkAnswersOwner() {
    Answers answers = new Answers();

    assertAll(
        () -> answers.add(A1),
        () -> assertThat(answers.checkAnswersOwner(NsUserTest.JAVAJIGI)).isTrue(),
        () -> answers.add(A2),
        () -> assertThat(answers.checkAnswersOwner(NsUserTest.JAVAJIGI)).isFalse()
    );
  }
}
