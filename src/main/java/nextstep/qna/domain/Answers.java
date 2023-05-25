package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer>answers= new ArrayList();

   public void add(Answer answer){
       this.answers.add(answer);
   }

   public List<DeleteHistory> deleteAll(NsUser loginUser) throws CannotDeleteException {
       List<DeleteHistory> deleteHistories = new ArrayList<>();
       for (Answer answer : answers) {
           if (!answer.isOwner(loginUser)) {
               throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
           }
       }

       for (Answer answer : answers) {
           answer.setDeleted(true);
           deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
       }
       return deleteHistories;
   }
}
