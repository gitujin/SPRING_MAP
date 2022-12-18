package diroumMap.diroumspring.controller.admin;

import diroumMap.diroumspring.Repository.StoreRepository;
import diroumMap.diroumspring.controller.SessionConst;
import diroumMap.diroumspring.domain.User;
import diroumMap.diroumspring.service.StoreService;
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
public class StoreController {

    private final StoreService storeService;

    /**
     * 리스트
     */
    @GetMapping("/admin/userList")
    public String storeList(Model model){
        model.addAttribute("list",storeService.findAll());
        return "admin/adminUserManagement";
    }

    /**
     * 업체 추가
     */
    @GetMapping("/storeAdd")
    public String adminAdd(@ModelAttribute StoreForm storeForm){
        return "admin/adminAdd";
    }

    @PostMapping("/storeAdd")
    public String storeAdd(@Valid @ModelAttribute StoreForm storeForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "admin/adminAdd";
        }

        storeService.storeAdd(storeForm.getCategory(), storeForm.getName(), storeForm.getAddress());

        return "redirect:/admin";
    }
}
