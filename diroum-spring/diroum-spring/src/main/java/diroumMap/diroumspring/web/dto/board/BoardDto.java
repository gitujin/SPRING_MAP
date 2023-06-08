package diroumMap.diroumspring.web.dto.board;

import diroumMap.diroumspring.web.domain.board.Board;
import diroumMap.diroumspring.web.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class BoardDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotNull
    private String content;

    public Board toEntity(Users users){
        return Board.builder()
                .title(title)
                .content(content)
                .users(users)
                .build();
    }

}

