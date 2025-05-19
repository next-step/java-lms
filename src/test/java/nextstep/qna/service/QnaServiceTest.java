package nextstep.qna.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.ContentType;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.qna.domain.QuestionTest;
import nextstep.users.domain.NsUserTest;

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
    public void setUp() throws Exception {
        question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void delete_성공() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(NsUserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        qnAService.deleteQuestion(NsUserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자와 모든 답변자가 같은 경우 삭제 가능")
    public void deleteQuestion_OwnerUse_Success() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "content");
        Answer answer = new Answer(11L, NsUserTest.JAVAJIGI, question, "answer content");
        question.addAnswer(answer);

        question.delete(NsUserTest.JAVAJIGI);

        assertTrue(question.isDeleted());
        assertTrue(answer.isDeleted());
        verifyDeleteHistories();
    }

    @Test
    @DisplayName("로그인 사용자와 질문자가 다른 경우 삭제 불가능")
    public void deleteQuestion_NotOwner_throwsException() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "content");

        assertThrows(CannotDeleteException.class, () -> {
            question.delete(NsUserTest.SANJIGI);
        });
    }

    @Test
    @DisplayName("로그인 사용자와 질문자가 같고 답변이 없는 경우 삭제 가능")
    public void deleteQuestion_WithOwnAnswer_Success() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "content");
        
        List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI);

        assertTrue(question.isDeleted());
        assertThat(deleteHistories).hasSize(1);

        verifyDeleteHistories();
    }

    @Test
    @DisplayName("다른 사용자의 답변이 있는 경우 삭제 불가능")
    public void deleteQuestion_fail() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "content");
        Answer ownAnswer = new Answer(11L, NsUserTest.JAVAJIGI, question, "answer content");
        Answer otherAnswer = new Answer(12L, NsUserTest.SANJIGI, question, "answer content2");
        question.addAnswer(ownAnswer);
        question.addAnswer(otherAnswer);

        CannotDeleteException exception = assertThrows(CannotDeleteException.class, () -> {
            question.delete(NsUserTest.JAVAJIGI);
        });

        assertThat(exception.getMessage()).contains("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다");
        assertFalse(question.isDeleted());
        assertFalse(ownAnswer.isDeleted());
        assertFalse(otherAnswer.isDeleted());
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
