package diroumMap.diroumspring.web.controller.comment;

import diroumMap.diroumspring.security.UsersAdapter;
import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.dto.board.BoardDto;
import diroumMap.diroumspring.web.dto.comment.CommentDto;
import diroumMap.diroumspring.web.service.board.BoardService;
import diroumMap.diroumspring.web.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class CommentController {

    private final CommentService commentService;
    
    /**
     * 댓글 작성
     */
    @PostMapping("/{postId}/comment")
    public String replyWrite(@PathVariable("postId") Long postId,
                             @ModelAttribute("commentDto") CommentDto commentDto,
                             @AuthenticationPrincipal UsersAdapter usersAdapter){

        Users users = usersAdapter.getUsers();
        commentService.writeComment(commentDto.getContents(), users.getId(), postId);

        return "redirect:";
    }

    /**
     * 댓글 삭제
     */
    @PostMapping("/{postId}/comment/delete")
    public String replyDelete(@PathVariable Long postId,
                              @RequestParam("commentId") Long commentId,
                              RedirectAttributes redirectAttributes){
        log.info("댓글 삭제");
        commentService.deleteComment(commentId);
        redirectAttributes.addAttribute("postId", postId);

        return "redirect:/board/{postId}";
    }
}
