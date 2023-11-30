package io.bootify.mlops_demonstration.color.generator;

import io.bootify.mlops_demonstration.color.ColorDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ColorGenerator {

    // Logic for generating colors with a pattern
    private static final List<String> COLORS = Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple");
    private static final List<String> RANGES = Arrays.asList("LowerLeft", "UpperLeft", "LowerRight", "UpperRight", "Default");

    private Random random = new Random();
    private Map<String, String> colorRanges = new HashMap<>();

    @PostConstruct
    public void init() {
        initColorRanges();
    }
    public List<ColorDTO> generateDataPoints(int numberOfPoints) {
        initColorRanges();
        List<ColorDTO> dataPoints = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
            double x = random.nextDouble() * 100; // Random x-value between 0 and 100
            double y = random.nextDouble() * 100; // Random y-value between 0 and 100
            String color = determineColor(x, y);

            ColorDTO colorDTO = new ColorDTO();
            colorDTO.setX(x);
            colorDTO.setY(y);
            colorDTO.setColorName(color);

            dataPoints.add(colorDTO);
        }
        return dataPoints;
    }

    private void initColorRanges() {
        ArrayList<String> colors = new ArrayList<>();
        colors.addAll(COLORS);
        for (String range : RANGES) {
            colorRanges.put(range, getRandomColor(colors));
        }
    }

    private String getRandomColor(List<String> colors) {
        int index = random.nextInt(colors.size());
        String color = colors.get(index);
        colors.remove(index);
        return color;
    }
    private String determineColor(double x, double y) {
        // Introduce some randomness
        if (random.nextFloat() < 0.1) { // 10% chance for a random color
            return randomColor();
        }
        // Example logic for determining color
        if (x < 50 && y < 50) return colorRanges.get("LowerLeft");
        else if (x >= 50 && y < 50) return colorRanges.get("UpperLeft");
        else if (x < 50 && y >= 50) return colorRanges.get("LowerRight");
        else if (x >= 50 && y >= 50) return colorRanges.get("UpperRight");

        return colorRanges.get("Default"); // Default case
    }

    private String randomColor() {;
        return COLORS.get(random.nextInt(COLORS.size()));
    }
}