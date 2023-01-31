package diroumMap.diroumspring.web.controller.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class StoreForm {

    @NotNull
    private String category;

    @NotNull
    private String title;

    @NotNull
    private String address;

}
