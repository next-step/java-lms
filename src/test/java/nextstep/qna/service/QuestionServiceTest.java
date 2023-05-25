package nextstep.qna.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.qna.domain.QuestionTest;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

  @InjectMocks
  private QuestionService sut;

  @Mock
  private AnswerService answerService;

  @Mock
  private QuestionRepository questionRepository;

  private Question 답변이_있는_질문;
  private Question 답변이_없는_질문;
  private Answer answer;

  @BeforeEach
  public void setUp() throws Exception {
    답변이_있는_질문 = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
    답변이_없는_질문 = new Question(2L, NsUserTest.JAVAJIGI, "title2", "contents2");
    answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    답변이_있는_질문.addAnswer(answer);
  }

  @Test
  @DisplayName("다른 사람이 작성한 질문은 삭제 할 수 없다.")
  public void delete_다른_사람이_쓴_글() throws Exception {
    // given
    NsUser 작정자가_아닌_유저 = NsUserTest.SANJIGI;

    // when & then
    assertThatThrownBy(() -> {
      sut.delete(작정자가_아닌_유저, 답변이_있는_질문);
    }).isInstanceOf(CannotDeleteException.class);
  }

  @Test
  @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.")
  public void delete_성공() throws Exception {
    // given
    NsUser 질문_작성자 = NsUserTest.JAVAJIGI;
    assertThat(답변이_없는_질문.isDeleted()).isFalse();

    // when
    sut.delete(질문_작성자, 답변이_없는_질문);

    // then
    assertThat(답변이_없는_질문.isDeleted()).isTrue();
  }

  @Test
  @DisplayName("답변이 없는 경우 삭제가 가능하다.")
  public void test() throws Exception {
    // given
    assertThat(답변이_없는_질문.isDeleted()).isFalse();
    NsUser 질문_작성자 = NsUserTest.JAVAJIGI;

    // when
    sut.delete(질문_작성자, 답변이_없는_질문);

    // then
    assertThat(답변이_없는_질문.isDeleted()).isTrue();
  }
}
