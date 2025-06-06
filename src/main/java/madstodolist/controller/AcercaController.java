package madstodolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AcercaController {
    
    @GetMapping("/nosotros")
    public String acercaNosotros(Model model) {
        return "acercaDeNosotros";
    }
    
}
