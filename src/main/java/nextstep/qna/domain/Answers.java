package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private List<Answer> answerList = new ArrayList<>();

    public Answers() {
        this.answerList = new ArrayList<>();
    }

    public Answers(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void add(Answer answer) {
        answerList.add(answer);
    }

    int answersSize() {
        return this.answerList.size();
    }

    public boolean isAnswerOwner(NsUser loginUser) {
        long answerOwnerCount = answerList.stream().filter(answer -> answer.isOwner(loginUser)).count();
        System.out.println(answersSize() + " " + answerOwnerCount);
        if (answersSize() == answerOwnerCount) {
            return true;
        }
        return false;
    }

    public List<DeleteHistory> delete() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answerList) {
            deleteHistories.add(answer.delete());
        }
        return deleteHistories;
    }
}
