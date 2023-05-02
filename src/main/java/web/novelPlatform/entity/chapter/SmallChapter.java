package web.novelPlatform.entity.chapter;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class SmallChapter {

    @Id
    @GeneratedValue
    @Column(name = "smallChapter_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bigChapter_id")
    private BigChapter bigChapter;
}
