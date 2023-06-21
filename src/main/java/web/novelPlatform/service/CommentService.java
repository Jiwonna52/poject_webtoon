package web.novelPlatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.entity.Comment;
import web.novelPlatform.repository.CommentRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    //댓글 추가
    @Transactional
    public Long createComment(Comment comment){
        commentRepository.create(comment);

        return comment.getId();
    }

    //내용 삭제
    @Transactional
    public void deleteComment(Long id){
        commentRepository.delete(id);
    }

    //내용 바꾸기
    public void updateComment(Long id, String comments){
        Comment comment = findOne(id);
        comment.setId(id);
        comment.setComment(comments);
    }

    //하나 찾기
    private Comment findOne(Long id){
        return commentRepository.findOne(id);
    }

}
