package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문의 답변자 중 질문자 이외 사람 여부를 확인할 수 있다")
    public void other_answer_writer() {
        Q1.addAnswer(new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));

        assertThat(Q1.hasAnswerOfOthers()).isTrue();
    }

    @Test
    @DisplayName("질문을 삭제할 수 있다")
    public void delete_question() {
        Q1.deleted();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 삭제 시 히스토리를 생성할 수 있다")
    public void delete_question_history() {
        assertThat(Q1.deleted()).isInstanceOf(DeleteHistory.class);
    }

}
