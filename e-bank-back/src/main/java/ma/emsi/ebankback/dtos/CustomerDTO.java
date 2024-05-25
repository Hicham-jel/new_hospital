package ma.emsi.ebankback.dtos;


import lombok.*;

@Data
@Getter
@Setter

public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}
