package io.bootify.mlops_demonstration.color;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ColorDTO {

    private Long id;

    private Integer batch;

    @Size(max = 255)
    private String colorName;

    private Double x;

    private Double y;

}
