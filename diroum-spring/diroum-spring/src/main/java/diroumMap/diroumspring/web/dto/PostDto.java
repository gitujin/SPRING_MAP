package diroumMap.diroumspring.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class PostDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotNull
    private String content;
}
