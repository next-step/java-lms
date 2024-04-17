package nextstep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
 * 강의가 진행 중인 상태에서도 수강신청이 가능해야 한다.
 * 강의 진행 상태(준비중, 진행중, 종료)와 모집 상태(비모집중, 모집중)로 상태 값을 분리해야 한다.
 * 강의는 강의 커버 이미지 정보를 가진다.
 * 강의는 하나 이상의 커버 이미지를 가질 수 있다.
 * 강사가 승인하지 않아도 수강 신청하는 모든 사람이 수강 가능하다.
 * 우아한테크코스(무료), 우아한테크캠프 Pro(유료)와 같이 선발된 인원만 수강 가능해야 한다.
 * 강사는 수강신청한 사람 중 선발된 인원에 대해서만 수강 승인이 가능해야 한다.
 * 강사는 수강신청한 사람 중 선발되지 않은 사람은 수강을 취소할 수 있어야 한다.
 */
@SpringBootApplication
public class NextstepApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextstepApplication.class, args);
	}

}
