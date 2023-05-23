package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

  public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
  public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

  @DisplayName("질문을 삭제한다.")
  @Test
  public void delete() {
    List<DeleteHistory> deleteHistoriesQ1 = Q1.delete();
    List<DeleteHistory> deleteHistoriesQ2 = Q2.delete();
    assertAll(
        () -> assertThat(deleteHistoriesQ1.get(0)).isEqualTo(Q1.toDeleteHistory()),
        () -> assertThat(Q1.isDeleted()).isTrue(),
        () -> assertThat(deleteHistoriesQ2.get(0)).isEqualTo(Q2.toDeleteHistory()),
        () -> assertThat(Q2.isDeleted()).isTrue()
    );
  }

  @DisplayName("질문의 주인인지 확인한다.")
  @Test
  public void checkQuestionOwner() {
    assertAll(
        () -> assertThat(Q1.checkQuestionOwner(NsUserTest.JAVAJIGI)).isTrue(),
        () -> assertThat(Q1.checkQuestionOwner(NsUserTest.SANJIGI)).isFalse(),
        () -> assertThat(Q2.checkQuestionOwner(NsUserTest.JAVAJIGI)).isFalse(),
        () -> assertThat(Q2.checkQuestionOwner(NsUserTest.SANJIGI)).isTrue()
    );
  }
}
