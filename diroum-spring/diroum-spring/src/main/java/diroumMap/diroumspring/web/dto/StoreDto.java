package diroumMap.diroumspring.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class StoreDto {

    @NotNull
    private String category;

    @NotNull
    private String title;

    @NotNull
    private String address;

}
