package nextstep.qna.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuestionTest {

  public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
  public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

  private NsUser user;
  private Question question;

  @BeforeEach
  public void setUp() throws Exception {
    user = NsUserTest.JAVAJIGI;
    question = new Question(1L, user, "title1", "contents1");
  }


  @Test
  void delete_성공() throws Exception {
    question.addAnswer(AnswerTest.A1);
    List<DeleteHistory> deleteHistories = question.delete(user);

    // JPA를 사용하면 getter를 열어두는 경우가 많기 때문에 getter는 도메인에서 제거하지 않고 남겨놓음
    // 대신 테스트에서만 사용한다고 팀에서 약속했다고 가정
    assertTrue(question.isDeleted());

    // 고민 포인트 1. answer가 deleted되었는지에 대한 확인은 Question의 메서드에서 확인해야 하는가?
    // 찬 : question.delete()가 기대하는 바는 answer들 또한 삭제되는 일이다
    // 반 : 이는 answer에서 처리해야 할 영역이다. answer의 동작이 변경될 경우 이 테스트 코드는 영향을 받는다
    for (Answer answer : question.getAnswers()) {
      assertTrue(answer.isDeleted());
    }
    Assertions.assertThat(deleteHistories)
        .hasSize(2);
  }

  @Test
  void delete_로그인_사용자와_질문한_사람이_다르면_실패() throws Exception {
    NsUser otherUser = NsUserTest.SANJIGI;

    Assertions.assertThatThrownBy(() -> question.delete(otherUser))
        .isInstanceOf(CannotDeleteException.class)
        .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
  }

  @Test
  void delete_다른_사용자의_답변이_존재하면_삭제가_불가능() throws Exception {
    question.addAnswer(AnswerTest.A2);

    Assertions.assertThatThrownBy(() -> question.delete(user))
        .isInstanceOf(CannotDeleteException.class)
        .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
  }

  @Test
  void delete_같은_사용자의_답변만_존재하면_삭제_가능() throws Exception {
    // given
    question.addAnswer(AnswerTest.A1);
    question.addAnswer(AnswerTest.A1);

    // when
    List<DeleteHistory> deleteHistories = question.delete(user);

    // then
    assertTrue(question.isDeleted());

    for (Answer answer : question.getAnswers()) {
      assertTrue(answer.isDeleted());
    }
    Assertions.assertThat(deleteHistories)
        .hasSize(3);
  }

  @Test
  void delete_다른_사용자의_답변이_하나라도_존재하면_삭제_불가능() throws Exception {
    question.addAnswer(AnswerTest.A1);
    question.addAnswer(AnswerTest.A1);
    question.addAnswer(AnswerTest.A2);

    Assertions.assertThatThrownBy(() -> question.delete(user))
        .isInstanceOf(CannotDeleteException.class)
        .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
  }
}
