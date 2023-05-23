package nextstep.qna.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.qna.domain.QuestionTest;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {

  @Mock
  private QuestionRepository questionRepository;

  @Mock
  private DeleteHistoryService deleteHistoryService;

  @InjectMocks
  private QnAService qnAService;

  private Question question;
  private Answer answer;

  @BeforeEach
  public void setUp(){
    question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
    answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    question.addAnswer(answer);
  }

  @Test
  public void delete_성공(){
    when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

    assertThat(question.isDeleted()).isFalse();
    qnAService.deleteQuestion(NsUserTest.JAVAJIGI, 1L);

    assertThat(question.isDeleted()).isTrue();
    verifyDeleteHistories();
  }

  @Test
  public void delete_다른_사람이_쓴_글(){
    when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

    assertThatThrownBy(() -> {
      qnAService.deleteQuestion(NsUserTest.SANJIGI, 1L);
    }).isInstanceOf(CannotDeleteException.class);
  }

  @Test
  public void delete_성공_질문자_답변자_같음(){
    when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

    qnAService.deleteQuestion(NsUserTest.JAVAJIGI, 1L);

    assertThat(question.isDeleted()).isTrue();
    assertThat(answer.isDeleted()).isTrue();
    verifyDeleteHistories();
  }

  @Test
  public void delete_답변_중_다른_사람이_쓴_글(){
    when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

    assertThatThrownBy(() -> {
      qnAService.deleteQuestion(NsUserTest.SANJIGI, 1L);
    }).isInstanceOf(CannotDeleteException.class);
  }

  private void verifyDeleteHistories() {
    List<DeleteHistory> deleteHistories = Arrays.asList(
        question.toDeleteHistory(),
        answer.toDeleteHistory());
    verify(deleteHistoryService).saveAll(deleteHistories);
  }
}
