package diroumMap.diroumspring.web.controller.admin.store;

import diroumMap.diroumspring.web.domain.store.Store;
import diroumMap.diroumspring.web.dto.store.StoreDto;
import diroumMap.diroumspring.web.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    @GetMapping("/store")
    public String storeList(String keyword, Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Store> list = null;

        if(keyword == null){
            list = storeService.storeList(pageable);
        } else{
            list = storeService.search(keyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1; //pageable에서 넘어온 현재페이지를 가지고 올 수있다 * 0부터 시작하니까 +1
        int startPage = Math.max(nowPage - 4, 1); //매개변수로 들어온 두 값을 비교해서 큰값을 반환
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        //StoreService에서 만들어준 boardList가 반환되는데, list라는 이름으로 받아서 넘기겠다는 뜻
        model.addAttribute("list" , list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("resultMap",storeService.findAll(pageable)); //listMap

        return "admin/adminStoreList";
    }

    /**
     * 업체 추가
     */
    @GetMapping("/storeAdd")
    public String adminAdd(@ModelAttribute StoreDto storeDto){
        return "admin/adminAdd";
    }

    @PostMapping("/storeAdd")
    public String storeAdd(@Valid @ModelAttribute StoreDto storeDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "admin/adminAdd";
        }

        storeService.storeAdd(storeDto.getCategory(), storeDto.getTitle(), storeDto.getAddress());

        return "redirect:/admin/store";
    }

    /**
     * 업체 수정
     */
    @GetMapping("/{storeId}/edit")
    public String editStore(@PathVariable Long storeId, Model model){

        Store store = storeService.findOne(storeId).orElseThrow();

        StoreDto storeDto = new StoreDto();
        storeDto.setCategory(store.getCategory());
        storeDto.setTitle(store.getTitle());
        storeDto.setAddress(store.getAddress());

        model.addAttribute("storeDto", storeDto);
        model.addAttribute("storeId", storeId);

        return "admin/adminEdit";
    }

    /**
     * 업체 수정
     */
    @PostMapping("/{storeId}/edit")
    public String edit(@PathVariable Long storeId, @Valid @ModelAttribute StoreDto storeDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "admin/editEdit";
        }

        storeService.updateStore(storeId, storeDto.getCategory(), storeDto.getTitle(), storeDto.getAddress());

        return "redirect:/admin/store";
    }

    /**
     * 업체 삭제
     */
    @PostMapping("/{storeId}/delete")
    public String delete(@PathVariable Long storeId){
        storeService.deleteById(storeId);
        return "redirect:/admin/adminList";
    }

//    /**
//     * 업체 검색
//     */
//    @GetMapping("/store/search")
//    public String search(@RequestParam(value = "keyword") String keyword, Model model,
//                         @PageableDefault(page=0, size=10, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
//        Page<Store> searchList = storeService.search(keyword,pageable);
//        model.addAttribute("resultMap", searchList);
//
//        return "admin/adminSearchList";
//    }


}
