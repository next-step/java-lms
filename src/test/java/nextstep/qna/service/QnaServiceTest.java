package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.*;
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

import static nextstep.fixture.NsUserFixture.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {

    private static final String TEST_STRING = "test";

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private Question question;

    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        Times times = new Times(LocalDateTime.now(), null);
        question = new Question(1L, new QuestionInformation(new QuestionTexts(TEST_STRING, TEST_STRING),
                                                            JAVAJIGI,
                                                            times,
                                                            false));
        answer = new Answer(11L, new AnswerInformation(TEST_STRING, JAVAJIGI, false, times));
        Answers answers = new Answers(answer);
        question.addAnswers(answers);
    }

    @Test
    public void delete_성공() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        when(answerRepository.findByQuestion(question.getId())).thenReturn(new Answers(answer));

        qnAService.deleteQuestion(JAVAJIGI, question.getId());

        verifyDeleteHistories();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        when(answerRepository.findByQuestion(question.getId())).thenReturn(new Answers(answer));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        when(answerRepository.findByQuestion(question.getId())).thenReturn(new Answers(answer));

        qnAService.deleteQuestion(JAVAJIGI, question.getId());

        verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        when(answerRepository.findByQuestion(question.getId())).thenReturn(new Answers(answer));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    private void verifyDeleteHistories() {
        List<DeletedHistory> deleteHistories = Arrays.asList(
                new DeletedHistory(ContentType.QUESTION, question.getId(), question.getWriter()),
                new DeletedHistory(ContentType.ANSWER, answer.getId(), answer.getWriter()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
