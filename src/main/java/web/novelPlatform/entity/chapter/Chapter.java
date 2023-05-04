package web.novelPlatform.entity.chapter;

import lombok.Getter;
import lombok.Setter;
import web.novelPlatform.entity.Novel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Chapter {
    @Id
    @GeneratedValue
    @Column(name = "bigChapter_id")
    private Long id;

    private String title;

    //여러개의 BigChapter가 하나의 Novel과 연관되어 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id")
    private Novel novel;

    @OneToMany(mappedBy = "bigChapter") //하위 챕터가 있는 경우 상위 챕터를 지울 수 없음
    private List<Content> contents = new ArrayList<>();

    //private int number;

}
