package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

  public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
      "Answers Contents1");
  public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1,
      "Answers Contents2");

  @DisplayName("답변을 삭제 한다.")
  @Test
  public void makeDeleted() {
    DeleteHistory deleteHistoryA1 = A1.makeDeleted();
    DeleteHistory deleteHistoryA2 = A2.makeDeleted();
    assertAll(
        () -> assertThat(A1.isDeleted()).isTrue(),
        () -> assertThat(deleteHistoryA1).isEqualTo(A1.toDeleteHistory()),
        () -> assertThat(A2.isDeleted()).isTrue(),
        () -> assertThat(deleteHistoryA2).isEqualTo(A2.toDeleteHistory())
    );
  }

  @DisplayName("답변의 주인인지 확인한다.")
  @Test
  public void checkAnswerOwner() {
    assertAll(
        () -> assertThat(A1.checkAnswerOwner(NsUserTest.JAVAJIGI)).isTrue(),
        () -> assertThat(A1.checkAnswerOwner(NsUserTest.SANJIGI)).isFalse(),
        () -> assertThat(A2.checkAnswerOwner(NsUserTest.JAVAJIGI)).isFalse(),
        () -> assertThat(A2.checkAnswerOwner(NsUserTest.SANJIGI)).isTrue()
    );
  }
}
