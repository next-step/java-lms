package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


class DeleteHistoriesTest {

    @Test
    @DisplayName("질문과 답변 삭제 이력을 객체로 저장한다.")
    void 삭제_이력_객체_저장() {
        Question Q3 = Question.of(NsUserTest.JAVAJIGI, "title1", "contents1", true);
        Answer answer1 = Answer.of(NsUserTest.JAVAJIGI, Q3, "Answers Contents1",true);
        Answer answer2 = Answer.of(NsUserTest.JAVAJIGI, Q3, "Answers Contents2", true);

        Q3.addAnswer(answer1);
        Q3.addAnswer(answer2);

        DeleteHistories deleteHistories = DeleteHistories.from(new ArrayList<>());
        deleteHistories.addQuestionDeleteHistory(Q3);
        deleteHistories.addAnswerDeleteHistory(answer1);
        deleteHistories.addAnswerDeleteHistory(answer2);

        assertThat(deleteHistories.equals(DeleteHistories.from(Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, Q3.getId(), Q3.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), LocalDateTime.now()))))).isTrue();
    }

}