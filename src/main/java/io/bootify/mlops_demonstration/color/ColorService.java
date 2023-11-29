package io.bootify.mlops_demonstration.color;

import io.bootify.mlops_demonstration.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ColorService {

    private final ColorRepository colorRepository;

    public ColorService(final ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<ColorDTO> findAll() {
        final List<Color> colors = colorRepository.findAll(Sort.by("id"));
        return colors.stream()
                .map(color -> mapToDTO(color, new ColorDTO()))
                .toList();
    }

    public ColorDTO get(final Long id) {
        return colorRepository.findById(id)
                .map(color -> mapToDTO(color, new ColorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ColorDTO colorDTO) {
        final Color color = new Color();
        mapToEntity(colorDTO, color);
        return colorRepository.save(color).getId();
    }

    public void update(final Long id, final ColorDTO colorDTO) {
        final Color color = colorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(colorDTO, color);
        colorRepository.save(color);
    }

    public void delete(final Long id) {
        colorRepository.deleteById(id);
    }

    private ColorDTO mapToDTO(final Color color, final ColorDTO colorDTO) {
        colorDTO.setId(color.getId());
        colorDTO.setColorName(color.getColorName());
        colorDTO.setX(color.getX());
        colorDTO.setY(color.getY());
        return colorDTO;
    }

    private Color mapToEntity(final ColorDTO colorDTO, final Color color) {
        color.setColorName(colorDTO.getColorName());
        color.setX(colorDTO.getX());
        color.setY(colorDTO.getY());
        return color;
    }

}
