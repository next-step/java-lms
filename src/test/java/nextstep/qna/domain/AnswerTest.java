package nextstep.qna.domain;

import nextstep.qna.service.DeleteHistoryService;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @Test
    void 작성자_본인인지_확인(){
        assertThat(A1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(A1.isOwner(NsUserTest.SANJIGI)).isFalse();
    }
    
    @Test
    void 답변_삭제_확인_및_삭제_이력_확인(){
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
        verifyDeleteHistories(A1);
    }

    private void verifyDeleteHistories(Answer answer) {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }

}
