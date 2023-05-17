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
    @Column(name = "chapter_id")
    private Long id;

    private String title;

    //여러개의 BigChapter가 하나의 Novel과 연관되어 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id")
    private Novel novel;

    @OneToMany(mappedBy = "chapter", orphanRemoval = true) //삭제를 전파
    private List<Content> contents = new ArrayList<>();

    //private int number;

}
