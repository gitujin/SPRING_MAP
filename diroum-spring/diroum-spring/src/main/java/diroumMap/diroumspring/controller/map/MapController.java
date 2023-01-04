package diroumMap.diroumspring.controller.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import diroumMap.diroumspring.Repository.StoreInterface;
import diroumMap.diroumspring.domain.Store;
import diroumMap.diroumspring.service.BoardService;
import diroumMap.diroumspring.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

        System.out.println("categoryJson = " + categoryJson);

        return categoryJson;
    }

}
