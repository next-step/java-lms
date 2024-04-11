package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answerList = new ArrayList<Answer>();

    public void add(Answer answer){
        this.answerList.add(answer);
    }

    public boolean isAllOwner(NsUser loginUser){
        return this.answerList
                .stream()
                .allMatch( answer -> answer.isOwner(loginUser));
    }

    private boolean isEmpty(){
        return this.answerList.isEmpty();
    }

    public boolean isDeletable(NsUser loginUser){
        boolean isEmpty = this.isEmpty();
        boolean isAllOwner =  this.isAllOwner(loginUser);

        return isEmpty || isAllOwner;
    }

    public void setAllDeleted(boolean deleted){
        this.answerList
                .forEach( answer -> answer.setDeleted(deleted));
    }

    public List<Answer> getAll(){
        return this.answerList;
    }
}
