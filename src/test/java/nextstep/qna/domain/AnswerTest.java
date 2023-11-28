package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("사용자 정보를 인자로 받아 답변 작성자와 일치하는 지 확인한다.")
    @Test
    void isSameWriter() {
        // given
        Answer answer = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        // when & then
        assertThat(answer.isSameWriter(JAVAJIGI)).isTrue();
    }

    @DisplayName("Answer객체의 deleted상태를 true로 바꿔 질문을 삭제한다.")
    @Test
    void delete() {
        // given
        Answer answer = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        // when
        answer.delete();

        // then
        assertThat(answer.isDeleted()).isTrue();
    }
}
