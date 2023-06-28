package diroumMap.diroumspring.web.controller.like;

import diroumMap.diroumspring.security.UsersAdapter;
import diroumMap.diroumspring.web.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/community/post")
@Slf4j
public class BoardLikeController {

    private final BoardService boardService;

    /** 글 좋아요 **/
    @PostMapping("/like/{postId}")
    public boolean like(@PathVariable Long postId, @AuthenticationPrincipal UsersAdapter user) throws IOException{
        Long userId = user.getUsers().getId();
        log.info("글 좋아요");

        // 좋아요하지 않은 게시물이라 좋아요 했다면 true, 좋아요 한 게시물이라 삭제했다면 false
        return boardService.saveLike(postId, userId);
    }
}
