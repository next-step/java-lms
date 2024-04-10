package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.CommonMock;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    public void 질문삭제권한_실패_테스트() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        assertThrows(CannotDeleteException.class, () -> question.delete(NsUserTest.SANJIGI));
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하며 삭제 히스토리가 추가된다")
    public void deletett() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        DeleteHistorys historys = question.delete(NsUserTest.JAVAJIGI);

        List<DeleteHistory> sut = historys.toList();
        assertThat(sut.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("답변이 없는 경우 삭제 가능하며 삭제 히스토리가 추가된다")
    public void deleteWhenAnswerEmpty() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        DeleteHistorys historys = question.delete(NsUserTest.JAVAJIGI);

        List<DeleteHistory> sut = historys.toList();
        assertThat(sut.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("답변과 질문 삭제 테스트")
    public void deleteHistoryTest() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(CommonMock.A1);

        DeleteHistorys historys = question.delete(NsUserTest.JAVAJIGI);
        List<DeleteHistory> deleteHistoryList = historys.toList();
        List<ContentType> types = deleteHistoryList.stream().map(DeleteHistory::getContentType)
            .collect(Collectors.toList());

        assertThat(types).contains(ContentType.QUESTION, ContentType.ANSWER);
    }

    @Test
    public void delete() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        Question sut = question.setDeleted(true);

        assertThat(sut.isDeleted()).isEqualTo(true);
    }
}
