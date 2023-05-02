package web.novelPlatform.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Novel {

    @Id
    @GeneratedValue
    @Column(name = "novel_id")
    private Long id;

    private String title;

    private String introduce;

    //User와 ManytoOne 관계

    @ManyToOne(fetch = FetchType.LAZY) //소설을 조회할 때 유저도 조회해야 할까?
    @JoinColumn(name = "user_id") //user에 있는 user_id와 연결
    private User user;

    @Enumerated(EnumType.STRING) //문자열로 보이도록
    private SerialState serialState;

    @Enumerated(EnumType.STRING)
    private Genre genre;

}
