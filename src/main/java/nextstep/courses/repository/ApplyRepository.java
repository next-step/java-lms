package nextstep.courses.repository;

import nextstep.courses.domain.Apply;

public interface ApplyRepository {
    int save(Apply image);

    Apply findById(Long id);
}

