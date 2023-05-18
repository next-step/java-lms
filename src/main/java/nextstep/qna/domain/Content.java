package nextstep.qna.domain;

import nextstep.users.domain.NextStepUser;

public interface Content {

    Long getId();

    NextStepUser getWriter();

    Content setDeleted(boolean isDeleted);


}
