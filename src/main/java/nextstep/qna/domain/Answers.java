package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer){
        this.answers.add(answer);
    }

    public int AnswerSize(){
        return this.answers.size();
    }

    public boolean otherOwnerCheck(NsUser loginUser){
        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {
                return false;
            }
        }
        return true;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (!otherOwnerCheck(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }
}
