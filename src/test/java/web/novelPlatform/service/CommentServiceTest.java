package web.novelPlatform.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.entity.*;
import web.novelPlatform.repository.CommentRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CommentServiceTest {

    @Autowired
    memberService memberService;
    @Autowired
    NovelService novelService;
    @Autowired
    ContentService contentService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;

    @Test
    public void 댓글등록(){
        //Given
        Member member = new Member();
        member.setName("A");
        Long UserId = memberService.join(member);

        Novel novel = new Novel();
        novel.setTitle("악녀는 오래 살고 싶다.");
        novel.setGenre(Genre.RomanceFantasy);
        novel.setSerialState(SerialState.Live);
        novel.setMember(member);
        Long novelId = novelService.createNovel(novel);

        Content content = new Content();
        content.setNovel(novel);
        content.setContents("1화 내용");
        Long contentId = contentService.createContent(content);

        //when
        Comment comment = new Comment();
        comment.setMember(member);
        comment.setContent(content);
        comment.setComment("1화 댓글");
        Long commentId = commentService.createComment(comment);

        //Then
        assertEquals(comment, commentRepository.findOne(commentId));
    }


}
