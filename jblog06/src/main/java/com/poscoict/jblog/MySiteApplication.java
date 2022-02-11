package com.poscoict.jblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * run 안에서 일어나는 bootspring의 관정
 * 1. 애플리케이션 컨텍스트(Application Context, Spring Container) 생성
 * 2. 웹애플리케이션 타입 결정(Web Application 인 경우 , MVC or Reactive 타입을 결정한다.)
 * 3. 어노테이션 스캐닝(auto) or Configuration Class(Explicit) 통한 빈 생성/등록 및 와이어링 작업    // 다 자동으로 설정이 일어난다.
 * 4. 웹 어플리케이션(MVC)
 * 	- 내장(embeded) 서버(TomcatEmbeddedServletContainer) 인스턴스 생성
 * 	- 서버 인스턴스 웹 애플리케이션을 배포시킨다.
 * 	- 서버 인스턴스를 실행한다.
 * 5. ApplicationRunner 인터피이스 구현한 빈을 찾아서 실행(run 호출)    //마지막으로 ex05를 해본다.
 * 
 * 
 */



@SpringBootApplication   //이거 다시 공부해보기  여기에 controllerscan이 포함되어있다. mysite 하부 디렉토리의 파일 쭉 스캔하고
//mvc라고 판단하고 자동 설정을 할 때
public class MySiteApplication {
	public static void main(String[] args) {
		SpringApplication.run(MySiteApplication.class, args); // 웹에서는 try catch 사용안함
		//여기서는 try catch 사용 안함 콘솔용 어플리케이션에서만 한다.
		
	}

}
