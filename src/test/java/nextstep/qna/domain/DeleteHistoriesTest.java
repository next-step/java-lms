package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeleteHistoriesTest {

    @DisplayName("DeleteHistories에 질문 삭제 이력을 추가한다.")
    @Test
    void 질문_삭제_이력_추가() {
        //givn
        DeleteHistories deleteHistories = new DeleteHistories();
        Question question = new Question(NsUserTest.SANJIGI, "testTitle", "testContents");
        DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.QUESTION, question.getId(), NsUserTest.SANJIGI, LocalDateTime.now());

        //when
        deleteHistories.addQuestion(question);

        //then
        Assertions.assertThat(deleteHistories.getDeleteHistories()).containsExactly(expectedDeleteHistory);
    }
}