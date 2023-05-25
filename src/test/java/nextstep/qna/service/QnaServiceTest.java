package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.*;
import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private Question question;
    private Answer answer;


    @BeforeEach
    public void setUp() {
        SimpleIdGenerator.initialize();
        question = Question.of(NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = Answer.of(11L, NsUserTest.JAVAJIGI, question, "Answers Contents1", null);
        Answers answers = Answers.of(List.of(answer));
        question.loadAnswers(answers);
    }

    @Test
    public void delete_성공() {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(NsUserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_다른_사람이_쓴_글() {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        qnAService.deleteQuestion(NsUserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    private void verifyDeleteHistories() {
        SimpleIdGenerator.initialize();
        List<DeleteHistory> deleteHistories = Arrays.asList(
                DeleteHistory.of(ContentType.QUESTION, question.getId(), question.getWriter()),
                DeleteHistory.of(ContentType.ANSWER, answer.getId(), answer.getWriter()));

        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
