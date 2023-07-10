package exercise.repository;

import exercise.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    // BEGIN
    Iterable<Comment> findCommentByPostId(Long postId);

    Comment findCommentByPostIdAndId(Long postId, Long id);
    // END
}
