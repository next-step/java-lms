package nextstep.qna.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionTest;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {

  Question question;
  Answer answer;
  NsUser user;

  @Mock
  private AnswerRepository answerRepository;

  @InjectMocks
  private AnswerService sut;

  @BeforeEach
  void setup() {
    user = NsUserTest.JAVAJIGI;

    question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
    answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
  }

  @Test
  @DisplayName("AnswerServiceTest | 질문자와 답변자가 다른경우 답변을 삭제할수없다.")
  void delete_다른_사람이_쓴_글() {
    // given
    NsUser otherUser = NsUserTest.SANJIGI;
    Answer otherUsersAnswer = new Answer(12L, otherUser, QuestionTest.Q1, "Answers Contents1");
    question.addAnswer(answer);
    question.addAnswer(otherUsersAnswer);

    when(answerRepository.findByQuestion(question.getId())).thenReturn(question.getAnswers());

    // when & then
    Assertions.assertThatThrownBy(() -> sut.deleteAnswersOfQuestion(user, question))
        .isInstanceOf(CannotDeleteException.class);
  }
}
