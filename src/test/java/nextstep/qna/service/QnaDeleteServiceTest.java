package nextstep.qna.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import nextstep.dummy.answer.AnswerDummy;
import nextstep.dummy.answer.NsUserDummy;
import nextstep.dummy.answer.QuestionDummy;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QnaDeleteServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QnADeleteService qnADeleteService;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    private Question question;

    private NsUser javajigi;

    private NsUser sanjigi;

    @BeforeEach
    public void setUp() {
        question = new QuestionDummy().getJavajigiQuestion();
        question.addAnswer(new AnswerDummy().javajigi_answer);
        NsUserDummy nsUserDummy = new NsUserDummy();
        javajigi = nsUserDummy.javajigi;
        sanjigi = nsUserDummy.sanjigi;
    }

    @Test
    public void delete_성공() {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        assertThat(question.isQuestionDeleted()).isFalse();
        qnADeleteService.deleteQuestion(javajigi, question.getId());

        assertThat(question.isQuestionDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_다른_사람이_쓴_글() {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnADeleteService.deleteQuestion(sanjigi, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        qnADeleteService.deleteQuestion(javajigi, question.getId());

        assertThat(question.isQuestionDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnADeleteService.deleteQuestion(sanjigi, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = question.deleteHistories();
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
