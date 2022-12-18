package diroumMap.diroumspring.controller.admin.user;

import diroumMap.diroumspring.service.StoreService;
import diroumMap.diroumspring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/userList")
    public String storeList(Model model){
        model.addAttribute("list",userService.findAll());
        return "admin/adminUserList";
    }
}
