package io.bootify.mlops_demonstration.color;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/colors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ColorResource {

    private final ColorService colorService;

    public ColorResource(final ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public ResponseEntity<List<ColorDTO>> getAllColors() {
        return ResponseEntity.ok(colorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColorDTO> getColor(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(colorService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createColor(@RequestBody @Valid final ColorDTO colorDTO) {
        final Long createdId = colorService.create(colorDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateColor(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ColorDTO colorDTO) {
        colorService.update(id, colorDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable(name = "id") final Long id) {
        colorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
