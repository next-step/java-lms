package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {
	private List<Answer> answers;

	public Answers(List<Answer> answers) {
		this.answers = answers;
	}

	public void checkOwner(NsUser writer) throws CannotDeleteException {
		if(!isOwners(writer)) {
			throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
		}
	}

	public boolean isOwners(NsUser writer) {
		return answers.stream()
				.allMatch(answer -> answer.isOwner(writer));
	}

	public void setDeleteAnswers(NsUser writer) throws CannotDeleteException {
		checkOwner(writer);
		answers.forEach(answer -> answer.setDeleted(true));
	}

	public Answer getAnswer(int index) {
		return answers.get(index);
	}

}
