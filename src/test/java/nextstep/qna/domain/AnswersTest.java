package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnswersTest {
  Answers answers;
  Answers answersBy2Users;
  @BeforeEach
  void init(){
    answers = new Answers(List.of(
        new Answer(
            new NsUser(1L, "userId", "password", "name", "email"),
            new Question(), "내용"
        )
      )
    );

    answersBy2Users = new Answers(List.of(
        new Answer(
            new NsUser(1L),
            new Question(), "내용"
        ),
        new Answer(
            new NsUser(2L),
            new Question(), "내용2"
        )
    ));
  }
  @Test
  void 답변을_삭제한다() {
    assertThatNoException().isThrownBy(() -> answers.deleteBy(new NsUser(1L)));
    assertThat(answers.allDeleted()).isTrue();
  }

  @Test
  void 다른사람이_쓴_답변은_삭제시_예외() throws CannotDeleteException {
    assertThatThrownBy(() -> answersBy2Users.deleteBy(new NsUser(1L)))
        .isInstanceOf(CannotDeleteException.class)
        .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
  }

  @Test
  void 삭제된_답변의_기록을_남긴다() throws CannotDeleteException {
    answers.deleteBy(new NsUser(1L));
    List<DeleteHistory> deleteHistories = answers.createDeleteHistories();
    assertThat(deleteHistories).hasSize(1);
  }

  @Test
  void 답변을_추가할_수_있다() {
    Answer newAnswer = new Answer(new NsUser(3L), new Question(), "새로추가된내용");
    answers.create(newAnswer);
    assertThat(answers.contains(newAnswer)).isTrue();
  }
}