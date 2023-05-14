package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeleteHistoriesTest {

  private Question question;
  private Answer answer;
  private DeleteHistories deleteHistories;

  @BeforeEach
  public void setUp() {
    question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
    answer = new Answer(11L, NsUserTest.JAVAJIGI, new Question(NsUserTest.JAVAJIGI, "title1", "contents1"), "Answers Contents1");

    question.addAnswer(answer);
  }

  @Test
  @DisplayName("질문과 답변 하나씩 삭제 개수 확인 테스트")
  public void 개수_확인() throws CannotDeleteException {
    deleteHistories = DeleteHistories.createDeleteHistories(question, NsUserTest.JAVAJIGI);

    assertThat(deleteHistories.deleteHistories()).hasSize(2);
  }

  @Test
  @DisplayName("질문과 답변 모두 로그인 사용자인 경우 삭제 테스트")
  public void 본인_확인() throws CannotDeleteException {
    deleteHistories = DeleteHistories.createDeleteHistories(question, NsUserTest.JAVAJIGI);

    Assertions.assertAll(
            () -> assertThat(question.isDeleted()).isTrue(),
            () -> assertThat(deleteHistories.deleteHistories())
                    .extracting("deletedBy")
                    .containsOnly(NsUserTest.JAVAJIGI)
    );
  }

  @Test
  @DisplayName("삭제하려는 로그인 사용자가 작성자가 아닌 경우 CannotDeleteException throw")
  public void 본인_아닌_질문_삭제() {
    assertThatThrownBy(() -> DeleteHistories.createDeleteHistories(question, NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
  }

  @Test
  @DisplayName("답변 작성자 중 로그인 사용자가 아닌 답변이 있는 경우 CannotDeleteException throw")
  public void 본인_아닌_답변_삭제() {
    Answer answer2 = new Answer(NsUserTest.SANJIGI, question, "Answers Contents2");
    question.addAnswer(answer2);

    assertThatThrownBy(() -> DeleteHistories.createDeleteHistories(question, NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
  }
}
