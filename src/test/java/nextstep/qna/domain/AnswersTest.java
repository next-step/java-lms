package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @DisplayName("답변목록을 생성한다.")
    @Test
    void 답변목록을_생성한다() {
        //given
        List<Answer> answer = List.of(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        Answers answers = new Answers(answer);
        //when
        //then
        assertThat(answers.size()).isEqualTo(1);
    }
}
