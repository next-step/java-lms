package nextstep.courses.domain.session.strategy;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nextstep.courses.domain.session.EnrollmentCount;
import nextstep.payments.domain.Money;

public enum StrategyType {

    FREE(FreeSessionStrategy.STRATEGY_NAME, (fee, enrollmentLimit) -> new FreeSessionStrategy()),
    PAID(PaidSessionStrategy.STRATEGY_NAME, (fee, enrollmentLimit) ->
            new PaidSessionStrategy(
                    new Money(fee),
                    new EnrollmentCount(enrollmentLimit)
            )
    );

    private static final Map<String, StrategyType> strategyTypes = Stream.of(values())
            .collect(Collectors.toUnmodifiableMap(type -> type.typeName, Function.identity()));

    private final String typeName;
    private final BiFunction<Integer, Integer, SessionStrategy> builder;

    StrategyType(final String typeName, final BiFunction<Integer, Integer, SessionStrategy> builder) {
        this.typeName = typeName;
        this.builder = builder;
    }

    public static SessionStrategy buildStrategy(final String typeName, final int fee, final int enrollmentLimit) {
        final StrategyType type = Optional.ofNullable(strategyTypes.get(typeName))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의 유형입니다. 유형: " + typeName));

        return type.build(fee, enrollmentLimit);
    }

    private SessionStrategy build(final int fee, final int enrollmentLimit) {
        return this.builder.apply(fee, enrollmentLimit);
    }
}
