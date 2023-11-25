package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {
	private final List<Answer> answers;

	public Answers(List<Answer> answers) {
		this.answers = answers;
	}

	public void delete(NsUser loginUser) throws CannotDeleteException {
		try {
			for (Answer answer : answers) {
				answer.delete(loginUser);
			}
		} catch (CannotDeleteException e) {
			throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
		}
	}

	public List<Answer> answers() {
		return answers;
	}
}
