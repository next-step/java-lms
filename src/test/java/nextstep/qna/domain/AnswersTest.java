package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @DisplayName("답변목록을 생성한다.")
    @Test
    void 답변목록을_생성한다() {
        List<Answer> answer = List.of(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        Answers answers = new Answers(answer);
        assertThat(answers.size()).isEqualTo(1);
    }

    @DisplayName("답변목록에 답변을 추가한다.")
    @Test
    void 답변목록에_답변을_추가한다() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answers answers = new Answers(answer);
        answers.add(new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));
        assertThat(answers.size()).isEqualTo(2);
    }

    @DisplayName("답변을 삭제한다.")
    @Test
    void 답변을_삭제한다() {
        List<Answer> answer = List.of(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        Answers answers = new Answers(answer);
        List<DeleteHistory> histories = answers.deleteAnswers(NsUserTest.JAVAJIGI);
        assertThat(histories.size()).isEqualTo(1);
    }

    @DisplayName("답변을 삭제 할 권한이 없다.")
    @Test
    void 답변을_삭제_할_권한이_없다() {
        List<Answer> answer = List.of(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        Answers answers = new Answers(answer);
        assertThatThrownBy(() -> {
            answers.deleteAnswers(NsUserTest.SANJIGI);
        }).isInstanceOf(UnAuthorizedException.class);
    }
}