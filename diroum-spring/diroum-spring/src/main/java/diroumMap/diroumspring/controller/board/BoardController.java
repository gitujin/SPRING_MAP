package diroumMap.diroumspring.controller.board;

import diroumMap.diroumspring.controller.SessionConst;
import diroumMap.diroumspring.domain.Board;
import diroumMap.diroumspring.domain.User;
import diroumMap.diroumspring.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String postList(Model model){
        model.addAttribute("list", boardService.findAll());
        return "board/postList";
    }

    @GetMapping("/{postId}")
    public String postView(@PathVariable Long postId, Model model) {
        log.info("postView");

        Board post = boardService.findOne(postId).orElseThrow();
        model.addAttribute("post", post);

        return "board/post";
    }


    @GetMapping("/register")
    public String registerForm(@ModelAttribute PostForm postForm)  {
        return "board/registerForm";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute PostForm postForm, BindingResult bindingResult, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {

        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "board/registerForm";
        }

        boardService.register(postForm.getTitle(), postForm.getContent(), loginUser.getId());

        return "redirect:/board";
    }

}
