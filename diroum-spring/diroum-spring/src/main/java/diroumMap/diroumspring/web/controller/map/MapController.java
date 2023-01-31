package diroumMap.diroumspring.web.controller.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import diroumMap.diroumspring.web.repository.StoreInterface;
import diroumMap.diroumspring.web.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MapController {

    @Autowired
    private final StoreService storeService;

    @GetMapping("/Dairoum")
    @ResponseBody
    public String selectCategorized(@RequestParam("category") String category) throws JsonProcessingException { //카테고리 값 받아옴.

        ObjectMapper objectMapper = new ObjectMapper();

        List<StoreInterface> categorized = storeService.findCategorized(category);

        String categoryJson = objectMapper.writeValueAsString(categorized);

        return categoryJson;
    }

}
