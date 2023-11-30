package io.bootify.mlops_demonstration.color.generator;

import io.bootify.mlops_demonstration.color.ColorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColorGeneratorTest {

    ColorGenerator colorGenerator;

    @BeforeEach
    public void setup() {
        colorGenerator = new ColorGenerator();
        colorGenerator.init();
    }

    @Test
    public void testColorGeneration() {
        List<ColorDTO> colorDTOList = colorGenerator.generateDataPoints(100);
        for (ColorDTO colorDTO : colorDTOList) {
            System.out.println(colorDTO.toString());
        }
    }
}