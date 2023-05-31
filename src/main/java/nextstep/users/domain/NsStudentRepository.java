package nextstep.users.domain;

import nextstep.users.domain.NsStudent;

import java.util.Optional;

public interface NsStudentRepository {

    int[] save(NsStudent nsStudent);
}
