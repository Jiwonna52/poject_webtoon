package web.novelPlatform.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Novel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "novel_id")
    private Long id;

    private String title;

    private String introduce;

    //User와 ManytoOne 관계

    @ManyToOne(fetch = FetchType.LAZY) //소설을 조회할 때 유저도 조회해야 할까?
    @JoinColumn(name = "member_id") //user에 있는 user_id와 연결
    private Member member;

    @Enumerated(EnumType.STRING) //문자열로 보이도록
    private SerialState serialState;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Content> contents = new ArrayList<>();

}
