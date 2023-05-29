package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswersTest {
    private Answer answer;
    private Answers answers;
    @BeforeEach
    public void setUp() {
        answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answers = new Answers();
        answers.add(answer);
    }

    @Test
    @DisplayName("주어진 유저가 모든 댓글의 작성자와 일치하는지 확인할 수 있다.")
    void 작성자_비교() {
        assertThat(answers.isOwnerAll(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(answers.isOwnerAll(NsUserTest.SANJIGI)).isFalse();
    }

    @Test
    @DisplayName("답변 리스트의 답변을 삭제할 수 있다.")
    void 답변_삭제() {
        answers.delete();
        assertThat(answer.isDeleted()).isTrue();
    }
}
