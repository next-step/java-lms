package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.*;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private Question question;
    private Answer answer1;
    private Answer answer2;

    @BeforeEach
    public void setUp() {
        question = new Question("1", NsUserTest.JAVAJIGI, "title1", "contents1");
        answer1 = new Answer("11", NsUserTest.JAVAJIGI, question, "Answers Contents1");
        answer2 = new Answer("11", NsUserTest.SANJIGI, question, "Answers Contents1");
    }

    @Test
    public void delete_성공() throws Exception {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(answerRepository.findByQuestion(1L)).thenReturn(List.of(answer1));

        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(NsUserTest.JAVAJIGI, 1L);

        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_다른_사람이_쓴_글() {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, 1L);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(answerRepository.findByQuestion(1L)).thenReturn(List.of(answer1));

        qnAService.deleteQuestion(NsUserTest.JAVAJIGI, 1L);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer1.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(answerRepository.findByQuestion(1L)).thenReturn(List.of(answer2));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, 1L);
        }).isInstanceOf(CannotDeleteException.class);
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = Arrays.asList(
            new DeleteHistory(ContentType.QUESTION, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, 11L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
