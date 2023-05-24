package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.*;
import nextstep.qna.event.QuestionDeleteEvent;
import nextstep.qna.event.QuestionDeleteEventHandler;
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

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class QuestionDeleteEventHandlerTest {
    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QuestionDeleteEventHandler deleteEventHandler;

    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    void deleteHistory() throws CannotDeleteException {
        deleteEventHandler.handler(new QuestionDeleteEvent(question, NsUserTest.JAVAJIGI));
        verifyDeleteHistories();
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                DeleteHistory.of(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                DeleteHistory.of(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
