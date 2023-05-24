package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.*;
import nextstep.qna.event.QuestionDeleteEvent;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

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
        verify(applicationEventPublisher, times(1)).publishEvent(new QuestionDeleteEvent(question, NsUserTest.JAVAJIGI));
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
        verify(applicationEventPublisher, times(1)).publishEvent(new QuestionDeleteEvent(question, NsUserTest.JAVAJIGI));
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }
}