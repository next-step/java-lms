package nextstep.qna.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    @Test
    @DisplayName("삭제가 deleted를 true로 만드는 것인지 체크하는 테스트.")
    void stateDeleteTest(){
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }
    @Test
    @DisplayName("답변자가 질문자와 같은경우 삭제 할 수 있다.")
    void ownerDeleteTest(){
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }
    @Test
    @DisplayName("답변자가 질문자와 다른경우 삭제 할 수 없다.")
    void anotherDeleteTest(){
        assertThatThrownBy(()->{
            A2.delete();
        }).isInstanceOf(CannotDeleteException.class);
    }
}
