package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;
import org.springframework.util.Assert;

public class SelectionUser {
    SelectStatus status;
    NsUser user;

    public SelectionUser(SelectStatus status, NsUser user) {
        validate(status, user);
        
        this.status = status;
        this.user = user;
    }

    private void validate(final SelectStatus status, final NsUser user) {
        Assert.notNull(status, "status must not be null");
        Assert.notNull(user, "user must not be null");
    }
}
