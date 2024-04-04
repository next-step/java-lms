package nextstep.courses.domain;


import nextstep.courses.domain.vo.Price;
import nextstep.users.domain.NsUser;

public interface EnrollCommand {
    NsUser userToEnroll(Price price);
}
