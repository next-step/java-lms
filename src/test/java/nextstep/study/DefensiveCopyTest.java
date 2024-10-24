package nextstep.study;

import nextstep.study.example.DefensiveCopyNames;
import nextstep.study.example.Name;
import nextstep.study.example.Names;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 생성자의 인자로 객체를 받았을 때
 * 외부에서 넘겨줬던 객체를 변경해도 내부의 객체는 변하지 않아야 한다.
 * 이 때, 방어적 복사를 사용한다.
 *
 * 내부 요소들(Name) 또한 불변이어야 한다.
 */
public class DefensiveCopyTest {

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
    void 인자전달후_외부변경에따른_변경() {
        List<Name> originalNames = new ArrayList<>();
        originalNames.add(crew1);
        originalNames.add(crew2);

        Names crewNames = new Names(originalNames);
        originalNames.add(crew3);

        Assertions.assertThat(crewNames.names()).containsExactly(crew1, crew2, crew3);
        //crewNames 의 names 는 객체가 생성된 이후에도 외부의 값 변경에 따라 같이 변하고 있다.
        //crewNames의 필드인 name과 originalNames가 주소를 공유하고 있기 때문이다.
    }

    @Test
    void 방어적_복사() {
        List<Name> originalNames = new ArrayList<>();
        originalNames.add(crew1);
        originalNames.add(crew2);

        DefensiveCopyNames defensiveCopyCrewNames = new DefensiveCopyNames(originalNames);
        originalNames.add(crew3);

        Assertions.assertThat(defensiveCopyCrewNames.names()).containsExactly(crew1, crew2);
        //crewNames의 names는 변하지 않는다.
        //new ArrayList<>()를 이용해 원본과의 주소 공유를 끊어냈기 때문이다.
    }

    @Test
    void 방어적_복사_내부_요소_Name_주소는_일치() {
        List<Name> originalNames = new ArrayList<>();
        originalNames.add(crew1);
        originalNames.add(crew2);

        DefensiveCopyNames defensiveCopyCrewNames = new DefensiveCopyNames(originalNames);
        originalNames.add(crew3);

        crew1.setName("Sally");// 방어적 복사를 하더라도 crew1 의 name 을 외부에서 handling 가능
        Assertions.assertThat(defensiveCopyCrewNames.names()).containsExactly(crew3, crew2);
        //Name 객체 fafi의 내부 값을 setName() 메서드를 이용해 sally로 변경했다.
        //그에 따라 방어적 복사를 통해 생성된 복사본 crewNames 의 내부 값도 변경된 것이다.
        //따라서 외부로부터의 변경에 취약하지 않도록 객체를 불변으로 만들고자 한다면 내부 요소들(Name) 또한 불변이어야 한다.
    }
}
