package web.novelPlatform.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Content {

    @Id
    @GeneratedValue
    @Column(name = "content_id")
    private Long id;
    private String title;
    private String path;

    @Column(length = 5000)
    String contents;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id") // one의 외부에서 보여지는 id와 연결을 해줘야 한다.
    private Webtoon webtoon;


}
