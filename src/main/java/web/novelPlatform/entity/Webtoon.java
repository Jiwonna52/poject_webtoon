package web.novelPlatform.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Webtoon {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "webtoon_id")
    private Long id;

    private String title;

    private String introduce;

    @Enumerated(EnumType.STRING) //문자열로 보이도록
    private SerialState serialState;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "webtoon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Content> contents = new ArrayList<>();

}
