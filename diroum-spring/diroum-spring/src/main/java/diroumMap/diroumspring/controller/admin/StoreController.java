package diroumMap.diroumspring.controller.admin;

import diroumMap.diroumspring.Repository.StoreRepository;
import diroumMap.diroumspring.controller.SessionConst;
import diroumMap.diroumspring.domain.Store;
import diroumMap.diroumspring.domain.User;
import diroumMap.diroumspring.service.StoreService;
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
@RequestMapping("/admin")
public class StoreController {

    private final StoreService storeService;

    /**
     * 업체 리스트
     */
    @GetMapping("/adminList")
    public String storeList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        model.addAttribute("resultMap",storeService.findAll(pageable));
        return "admin/adminList";
    }

    /**
     * 업체 추가
     */
    @GetMapping("/storeAdd")
    public String adminAdd(@ModelAttribute StoreForm storeForm){
        return "admin/adminAdd";
    }

    @PostMapping("/storeAdd")
    public String storeAdd(@Valid @ModelAttribute StoreForm storeForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "admin/adminAdd";
        }

        storeService.storeAdd(storeForm.getCategory(), storeForm.getTitle(), storeForm.getAddress());

        return "redirect:/admin/adminList";
    }

    /**
     * 업체 수정
     */
    @GetMapping("/{storeId}/edit")
    public String editStore(@PathVariable Long storeId, Model model){

        Store store = storeService.findOne(storeId).orElseThrow();

        StoreForm storeForm = new StoreForm();
        storeForm.setCategory(store.getCategory());
        storeForm.setTitle(store.getTitle());
        storeForm.setAddress(store.getAddress());

        model.addAttribute("storeForm", storeForm);
        model.addAttribute("storeId", storeId);

        return "admin/adminEdit";
    }

    @PostMapping("/{storeId}/edit")
    public String edit(@PathVariable Long storeId, @Valid @ModelAttribute StoreForm storeForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "admin/editEdit";
        }

        storeService.updateStore(storeId, storeForm.getCategory(), storeForm.getTitle(), storeForm.getAddress());

        return "redirect:/admin/{storeId}";
    }

    @PostMapping("/{storeId}/delete")
    public String delete(@PathVariable Long storeId){
        storeService.deleteById(storeId);
        return "redirect:/admin/adminList";
    }
}
