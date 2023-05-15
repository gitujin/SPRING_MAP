package diroumMap.diroumspring.web.controller.board;

import diroumMap.diroumspring.security.UsersAdapter;
import diroumMap.diroumspring.web.domain.Board;
import diroumMap.diroumspring.web.domain.Comment;
import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.dto.CommentDto;
import diroumMap.diroumspring.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    @Autowired private final CommentService commentService;


    @PostMapping("/board/{postId}/comment")
    public String replyWrite(@PathVariable("postId") Long postId, CommentDto commentDto,
                             @AuthenticationPrincipal UsersAdapter usersAdapter){

        Users users = usersAdapter.getUsers();

        commentService.writeComment(commentDto.getComment(), users.getId(), postId);

        return "redirect:";
    }

    @PostMapping("/board/{postId}/comment/delete")
    public String replyDelete(@ModelAttribute CommentDto commentDto,
                              @PathVariable Long postId){

        return commentService.deleteComment(postId);
    }
}
