package nextstep.courses.domain;

import nextstep.courses.domain.base.CreatedDate;
import nextstep.users.domain.NsUser;

public class SessionApply extends CreatedDate {

    private Long id;
    private Session session;
    private NsUser user;

    public SessionApply(Long id, Session session, NsUser user) {
        this.id = id;
        this.session = session;
        this.user = user;
    }

}
