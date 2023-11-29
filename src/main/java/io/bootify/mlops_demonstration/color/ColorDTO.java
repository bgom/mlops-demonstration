package io.bootify.mlops_demonstration.color;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ColorDTO {

    private Long id;

    @Size(max = 255)
    private String colorName;

    private Double x;

    private Double y;

}
