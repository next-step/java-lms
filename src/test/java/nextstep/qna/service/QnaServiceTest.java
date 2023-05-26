package nextstep.qna.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import config.MockTest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.ContentType;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionDeleteHistory;
import nextstep.qna.domain.QuestionTest;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class QnaServiceTest extends MockTest {

    @InjectMocks
    private QnAService qnAService;

    @Mock
    private QuestionService questionService;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    private Question question;
    private Answer answer;

    private NsUser 질문자;

    @BeforeEach
    public void setUp() throws Exception {
        질문자 = NsUserTest.JAVAJIGI;
        question = new Question(1L, 질문자, "title1", "contents1");
        answer = new Answer(11L, 질문자, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    @DisplayName("질문과 답변 모두 삭제되면 DeleteHistory를 활용해 기록을 남긴다.")
    public void delete_성공_질문자_답변자_같음() throws Exception {
        when(questionService.getQuestionOrThrowIfNotExist(question.getId())).thenReturn(question);
        when(questionService.delete(질문자, question)).thenReturn(new QuestionDeleteHistory(question, question.getAnswers()));

        qnAService.deleteQuestion(NsUserTest.JAVAJIGI, this.question.getId());

        verifyDeleteHistories();
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = Arrays.asList(
            new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
