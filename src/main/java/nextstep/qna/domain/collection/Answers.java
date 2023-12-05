package nextstep.qna.domain.collection;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.DeleteHistory;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Answer의 일급 컬렉션 객체
 */
public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    /**
     * 모든 Answer의 작성자가 주어진 writer와 일치할 경우에 삭제 표식을 남깁니다.
     * 주의! 컬렉션에서 제거하는 것을 의미하지 않습니다! 삭제 표식만 남깁니다.
     * 일치하지 않을 경우 예외가 던져집니다.
     * @param writer 검사 조건
     * @return 삭제 후 생성된 DeleteHistroy 목록
     */
    public DeleteHistoryBulk markDeletionAllIfWriter(NsUser writer) {
        DeleteHistoryBulk deleteHistories = new DeleteHistoryBulk();

        for (Answer answer : this.answers) {
            deleteHistories.add(deleteAnswer(writer, answer));
        }

        return deleteHistories;
    }

    private static DeleteHistory deleteAnswer(NsUser writer, Answer answer) {
        try {
            return answer.deleteIfWriter(writer);
        }
        catch (CannotDeleteException reason) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}