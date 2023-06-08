package diroumMap.diroumspring.web.controller.admin.user;

import diroumMap.diroumspring.web.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminController {

    private final UserService userService;

    @GetMapping("/userList")
    public String storeList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        model.addAttribute("resultMap",userService.findAll(pageable));
        return "admin/adminUserList";
    }

    /**
     * 회원 삭제
     */
    @PostMapping("/{userId}/delete")
    public String delete(@PathVariable Long userId, RedirectAttributes redirectAttributes){
        userService.deleteById(userId);

        return "redirect:/admin/users/userList";
    }
}
