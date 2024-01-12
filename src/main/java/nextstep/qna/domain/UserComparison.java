package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

@FunctionalInterface
public interface UserComparison {
	void compare(TextBody target, NsUser user) throws UnAuthorizedException;
}
