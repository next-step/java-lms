package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswersTest {

  public Answer a1;
  public Answer a2;
  public Answers answers;

  @BeforeEach
  public void setUp() {
    a1 = new Answer(NsUserTest.JAVAJIGI, new Question(NsUserTest.JAVAJIGI, "title1", "contents1"), "Answers Contents1");
    a2 = new Answer(NsUserTest.JAVAJIGI, new Question(NsUserTest.JAVAJIGI, "title1", "contents1"), "Answers Contents2");

    answers = new Answers();
    answers.addNewAnswer(a1);
    answers.addNewAnswer(a2);
  }

  @Test
  @DisplayName("답변 작성자가 로그인 사용자 다른 경우 CannotDeleteException throw")
  public void 답변_본인_확인() throws CannotDeleteException {
    assertThatThrownBy(() -> answers.validateAnswers(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
  }

  @Test
  @DisplayName("답변 모두 삭제한 경우 deleted 상태 true 확인")
  public void 답변_삭제_확인() {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    answers.deleteAnswers(deleteHistories);

    assertAll(
            () -> assertThat(deleteHistories).extracting("deletedBy").containsOnly(NsUserTest.JAVAJIGI),
            () -> assertThat(answers.answers()).extracting("deleted").containsOnly(true)
    );
  }
}
