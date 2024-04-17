package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.util.Optional;

public interface SelectionRepository {
    boolean isSelected(Payment payment);

    Long saveAndGetId(Selection selection);

    Optional<Selection> findById(Long savedId);
}
