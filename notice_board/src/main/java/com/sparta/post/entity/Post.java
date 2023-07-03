// 게시판에 사용되는 정보 담기
package com.sparta.post.entity;

import com.sparta.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity //JPA가 관리할 수 있는 Entity 클래스 지정
@Getter // Getter 메서드를 자동으로 생성
@Setter // Setter 메서드를 자동으로 생성
@Table(name = "post") //매핑할 테이블의 이름을 지정
@NoArgsConstructor // 기본 생성자를 자동으로 생성
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 게시물 끼리 구분하기 위함
    @Column(name = "title", nullable = false)
    private String title; // 제목
    @Column(name = "contents", nullable = false)
    private String contents; // 내용

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public Post(PostRequestDto postrequestDto) {
        this.title = postrequestDto.getTitle();
        this.contents = postrequestDto.getContents();

    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
