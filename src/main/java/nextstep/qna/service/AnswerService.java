package nextstep.qna.service;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

  private final AnswerRepository answerRepository;

  public AnswerService(AnswerRepository answerRepository) {
    this.answerRepository = answerRepository;
  }

  public List<Answer> deleteAnswers(NsUser loginUser, Question question) throws CannotDeleteException {
    List<Answer> answers = question.getAnswers();
    for (Answer answer : answers) {
      throwIfAnswerNotBelongsToLoginUser(loginUser, answer);
      answer.setDeleted(true);
    }

    return answers;
  }

  private void throwIfAnswerNotBelongsToLoginUser(NsUser nsUser, Answer answer) throws CannotDeleteException {
    if (!answer.isOwner(nsUser)) {
      throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
  }
}
