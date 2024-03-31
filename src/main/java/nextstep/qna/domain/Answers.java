package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(NsUser loginUser) {
//        answers.stream()
//                .allMatch(answer -> answer.isOwner(loginUser));
//        for (int i = 0; i < this.answers.size(); i++) {
//            answers.get(i).
//        }
    }

//    public void isWr(NsUser loginUser) throws CannotDeleteException {
//        if (!this.writer.matchUser(loginUser)) {
//            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
//        }
//    }

    public List<DeleteHistory> asDeleteHistoryTargets() {
        return null;
    }
}
