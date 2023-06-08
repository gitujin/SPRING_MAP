package diroumMap.diroumspring.web.controller.board;

import diroumMap.diroumspring.security.UsersAdapter;
import diroumMap.diroumspring.web.domain.board.Board;
import diroumMap.diroumspring.web.domain.comment.Comment;
import diroumMap.diroumspring.web.domain.users.Users;
import diroumMap.diroumspring.web.dto.board.BoardDto;
import diroumMap.diroumspring.web.dto.comment.CommentDto;
import diroumMap.diroumspring.web.service.board.BoardService;
import diroumMap.diroumspring.web.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    /** 글 전체 조회 **/
    @GetMapping
    public String postList(String keyword, Model model,
                           @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> list = null;

        if(keyword == null) {
            list = boardService.boardList(pageable);
        } else{
            list = boardService.search(keyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1; //pageable에서 넘어온 현재페이지를 가지고올수있다 * 0부터시작하니까 +1
        int startPage = Math.max(nowPage - 4, 1); //매개변수로 들어온 두 값을 비교해서 큰값을 반환
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        //BoardService에서 만들어준 boardList가 반환되는데, list라는 이름으로 받아서 넘기겠다는 뜻
        model.addAttribute("list" , list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("resultMap", boardService.findAll(pageable));

        return "board/postList";
    }

    /** 글 상세 조회 페이지 **/
    @GetMapping("/{postId}")
    public String postView(@PathVariable("postId") Long postId,
                           @AuthenticationPrincipal UsersAdapter user,
                           Model model) {
        log.info("postView");

        // 조회수
        Board board = boardService.selectBoardDetail(postId);
        model.addAttribute("board", board);

        Board post = boardService.findOne(postId).orElseThrow();
        model.addAttribute("post", post);
        model.addAttribute("comments", new CommentDto());

        // 댓글 갯수
        List<Comment> comments = commentService.commentsList(postId);
        model.addAttribute("commentList", comments);

        /* 게시물 좋아요 */
        boolean likes = false;

        if(user != null){
            //로그인 한 사용자라면
            Long userId = user.getUsers().getId();
            model.addAttribute("loginId", userId);

            likes = boardService.findLike(postId, userId);
        }

        model.addAttribute("like", likes);

        return "board/post";
    }

    /** 글쓰기 **/
    @GetMapping("/register")
    public String registerForm(@ModelAttribute BoardDto boardDto)  {
        return "board/registerForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute BoardDto boardDto, BindingResult bindingResult,
                           @AuthenticationPrincipal UsersAdapter usersAdapter,
                           RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "board/registerForm";
        }

        Users users = usersAdapter.getUsers();

        Long registerId = boardService.register(boardDto.getTitle(), boardDto.getContent(), users.getId());
        redirectAttributes.addAttribute("postId", registerId);

        return "redirect:/board";
    }

    /** 글 수정 하기 **/
    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model){

        Board post = boardService.findOne(postId).orElseThrow();
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle(post.getTitle());
        boardDto.setContent(post.getContent());

        model.addAttribute("boardDto", boardDto);
        model.addAttribute("postId", postId);

        return "board/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId,
                       @Valid @ModelAttribute BoardDto boardDto,
                       BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "board/editForm";
        }

        boardService.updateBoard(postId, boardDto.getTitle(), boardDto.getContent());

        return "redirect:/board/{postId}";
    }

    /** 글 삭제하기 **/
    @PostMapping("/{postId}/delete")
    public String delete(@PathVariable Long postId){
        boardService.deleteById(postId);

        return "redirect:/board";
    }
}
