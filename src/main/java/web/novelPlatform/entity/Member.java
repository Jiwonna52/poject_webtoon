package web.novelPlatform.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    //Novel에 있는 user와 연결 -> Novel에서 해당 유저를 가지고 있는 소설 목록을 뽑아서 가지고 옴
    //만일 유저에 있는 내용이 바뀌면 소설 또한 내용이 바뀌어야 한다.
    private List<Novel> novelList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> commentList;
}
