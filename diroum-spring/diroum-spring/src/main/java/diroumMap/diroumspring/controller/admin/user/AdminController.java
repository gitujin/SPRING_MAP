package diroumMap.diroumspring.controller.admin.user;

import diroumMap.diroumspring.service.StoreService;
import diroumMap.diroumspring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminController {

    private final UserService userService;

    @GetMapping("/userList")
    public String storeList(Model model){
        model.addAttribute("userList",userService.findAll());
        return "admin/adminUserList";
    }

    /**
     * 회원 삭제
     */
//    @PostMapping("/{userId}/delete")
//    public String delete(@PathVariable Long id){
//        userService.delete(id);
//        return "redirect:/admin";
//    }
}
