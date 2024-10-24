package nextstep.study;

import nextstep.study.example.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * getter를 통해 객체를 리턴할 때
 * 이 상황에선 방어적 복사를 통해 복사본을 반환해도 좋고, Unmodifiable Collection을 이용한 값을 반환하는 것도 좋다.
 */
public class UnmodifiableTest {
    private Name crew1;
    private Name crew2;
    private Name crew3;

    @BeforeEach
    void setUp() {
        crew1 = new Name("Fafi");
        crew2 = new Name("Kevin");
        crew3 = new Name("Sally");
    }
    @Test
    void 원본_자체에_수정이_일어나면_가변() {
        List<Name> originalNames = new ArrayList<>();
        originalNames.add(crew1);
        originalNames.add(crew2);

        List<Name> crewNames = Collections.unmodifiableList(originalNames); // crewNames: Fafi, Kevin

        originalNames.add(crew3); //crewNames: Fafi, Kevin, Sally
        Assertions.assertThat(crewNames).containsExactly(crew1, crew2, crew3);
    }
}
