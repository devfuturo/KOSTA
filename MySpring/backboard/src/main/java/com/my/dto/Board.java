package com.my.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
@Component  // 롬곡 라이브러리 잘 설정 되었는지 테스트 해볼 수 있는 것 

@NoArgsConstructor // 매개변수 없는 생성자 만들어줌 / com.my.dto > Board.java > Board 확장 해 보면 확인 가능
@AllArgsConstructor // 매개변수 여러개를 갖는 생성자 만들어줌
@Setter @Getter // Setter, Getter 메서드 만들어 줌
@EqualsAndHashCode(of= {"boardNo"}) // Overriding 해주는 것. 게시글 번호가 다르면 서로 다른객체, 같으면 같은 객체
@ToString // 대단히 위험한 어노테이션
// @Data // 위의 모든 것을 다 대체 해 줌
public class Board {
	private int level; // 글 레벨 : 1 - 원글, 2-답글, 3-답답글, 4-답답답글...
	
	private int boardNo ; // 게시글번호
	private int boardParentNo ;
	
	private String boardTitle ;
	private String boardContent;
	@JsonFormat(pattern = "yy/MM/dd", timezone ="Asia/Seoul") // timezone 대신 locale 사용도 가능
	private Date boardDt; //java.util 패키지의 Date
	
	@NonNull // null로 설정(ex: setBoardId(null) 또는 new Board(~~~, null,~~) 되면 자동 NullPointerException 발생시켜줌
	private String boardId ; // private Customer boardC;
	// 게시글 작성자 아이디만 필요하다면  String 선언 ok
	// 게시글 작성자의 이름, 주소, 성별 정보 등을 알고싶다고 하면 login된 고객 객체를 게시글에서 참조해야함
	// join구문이 필요하기 때문에 customer와 has a 관계로 넣어주어야 함
	private int boardViewcount ;
	
}

