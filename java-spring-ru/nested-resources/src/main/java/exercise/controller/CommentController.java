package exercise.controller;

import exercise.ResourceNotFoundException;
import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getGetAllCommentsForPost(@PathVariable long postId) {
        return commentRepository.findCommentByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getGetCommentForPost(@PathVariable long postId, @PathVariable long commentId) {
        Comment comment = commentRepository.findCommentByPostIdAndId(postId, commentId);

        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found by postId and commentId");
        }

        return comment;
    }

    @PostMapping(path = "/{postId}/comments")
    public Comment createCommentForPost(@PathVariable long postId, @RequestBody CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found by postId"));
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setContent(commentDto.content());

        return commentRepository.save(comment);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateComment(
            @PathVariable long postId,
            @PathVariable long commentId,
            @RequestBody CommentDto commentDto) {

        Comment comment = commentRepository.findCommentByPostIdAndId(postId, commentId);

        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found by postId and commentId");
        }

        comment.setContent(commentDto.content());

        return commentRepository.save(comment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        Comment comment = commentRepository.findCommentByPostIdAndId(postId, commentId);

        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found by postId and commentId");
        }

        commentRepository.delete(comment);
    }
    // END
}
