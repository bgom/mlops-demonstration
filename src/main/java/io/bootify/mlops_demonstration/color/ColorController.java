package io.bootify.mlops_demonstration.color;

import io.bootify.mlops_demonstration.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/colors")
public class ColorController {

    private final ColorService colorService;

    public ColorController(final ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("colors", colorService.findAll());
        return "color/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("color") final ColorDTO colorDTO) {
        return "color/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("color") @Valid final ColorDTO colorDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "color/add";
        }
        colorService.create(colorDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("color.create.success"));
        return "redirect:/colors";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("color", colorService.get(id));
        return "color/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("color") @Valid final ColorDTO colorDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "color/edit";
        }
        colorService.update(id, colorDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("color.update.success"));
        return "redirect:/colors";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        colorService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("color.delete.success"));
        return "redirect:/colors";
    }

    @GetMapping("/data-point-chart")
    public String generateDataFragment(@RequestParam(name = "generateNewBatch", required = false) Boolean generateNewBatch,
                                       Model model) {
        if (generateNewBatch != null && generateNewBatch) {
            colorService.generate(100);
        }
        List<ColorDTO> colorDTOList = colorService.getLatestBatch();
        model.addAttribute("dataPoints", colorDTOList);
        return "color/data-point-chart";
    }
}
