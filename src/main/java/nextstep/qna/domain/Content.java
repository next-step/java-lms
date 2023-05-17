package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public interface Content {

    Long getId();

    NsUser getWriter();

    Content setDeleted(boolean isDeleted);


}
