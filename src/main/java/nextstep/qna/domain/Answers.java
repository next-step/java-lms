package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public void deleteAll(){
        answers.stream().forEach(Answer::delete);
    }

    public void add(Answer answer){
        answers.add(answer);
    }

    public boolean contain(NsUser writer){
        return answers.stream().anyMatch(answer -> answer.equals(writer));
    }

    public boolean isContainExcludeTarget(NsUser writer){
        return answers.stream().anyMatch(answer -> !answer.isSameWriter(writer));
    }

    public List<DeleteHistory> toHistorys(){
        return answers.stream().map(answer -> answer.toHistory()).collect(Collectors.toList());
    }

}
