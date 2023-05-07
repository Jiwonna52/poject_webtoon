package web.novelPlatform.entity.chapter;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Content {

    @Id
    @GeneratedValue
    @Column(name = "Content_id")
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Chapter_id")
    private Chapter chapter;
}
