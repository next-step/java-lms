package nextstep.qna.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.ContentType;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.qna.exception.unchecked.CannotDeleteException;
import nextstep.qna.service.repository.QuestionRepository;
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
    public void setUp() {
        question = new Question(1L, 1L, "title1", "contents1");
        answer = new Answer(11L, 1L, question, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void delete_성공() {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        LocalDateTime fixedNow = LocalDateTime.now();

        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(1L, 1L, fixedNow);

        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories(fixedNow);
    }

    @Test
    public void delete_다른_사람이_쓴_글() {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        LocalDateTime fixedNow = LocalDateTime.now();

        assertThatThrownBy(() ->
                qnAService.deleteQuestion(2L, 1L, fixedNow)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        LocalDateTime fixedNow = LocalDateTime.now();

        qnAService.deleteQuestion(1L, 1L, fixedNow);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verifyDeleteHistories(fixedNow);
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        LocalDateTime fixedNow = LocalDateTime.now();

        assertThatThrownBy(() ->
                qnAService.deleteQuestion(2L, 1L, fixedNow)
        ).isInstanceOf(CannotDeleteException.class);
    }

    private void verifyDeleteHistories(LocalDateTime fixedNow) {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, 1L, 1L, fixedNow),
                new DeleteHistory(ContentType.ANSWER, 11L, 1L, fixedNow));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
