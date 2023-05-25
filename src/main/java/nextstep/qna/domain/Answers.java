package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers(){
        this.answers = new ArrayList<>();
    }

    public List<Answer> getAnswers(){
        return this.answers;
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }
}
