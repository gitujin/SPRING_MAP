package diroumMap.diroumspring.web.controller.board;

import diroumMap.diroumspring.web.controller.SessionConst;
import diroumMap.diroumspring.web.domain.Board;
import diroumMap.diroumspring.web.domain.users.User;
import diroumMap.diroumspring.web.dto.PostDto;
import diroumMap.diroumspring.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String postList(Model model,
                           @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("resultMap", boardService.findAll(pageable));

        return "board/postList";
    }

    @GetMapping("/{postId}")
    public String postView(@PathVariable Long postId, Model model) {
        log.info("postView");

        Board board = boardService.selectBoardDetail(postId);
        model.addAttribute("board", board);

        Board post = boardService.findOne(postId).orElseThrow();
        model.addAttribute("post", post);

        return "board/post";
    }


    @GetMapping("/register")
    public String registerForm(@ModelAttribute PostDto postDto)  {
        return "board/registerForm";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute PostDto postDto, BindingResult bindingResult, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "board/registerForm";
        }

        Long registerId = boardService.register(postDto.getTitle(), postDto.getContent(), loginUser.getId());
        redirectAttributes.addAttribute("postId", registerId);

        return "redirect:/board";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model){

        Board post = boardService.findOne(postId).orElseThrow();

        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());

        model.addAttribute("postDto", postDto);
        model.addAttribute("postId", postId);

        return "board/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, @Valid @ModelAttribute PostDto postDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "board/editForm";
        }

        boardService.updateBoard(postId, postDto.getTitle(), postDto.getContent());

        return "redirect:/board/{postId}";
    }

    @PostMapping("/{postId}/delete")
    public String delete(@PathVariable Long postId){
        boardService.deleteById(postId);

        return "redirect:/board";
    }
}
