package byAJ;

import byAJ.model.Chef;
import byAJ.model.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    ChefRepository chefRepository;

    @RequestMapping("/")
    public String homepage(Model model){

        model.addAttribute("message", "Welcome to the Application");
        return "index";
    }

    @GetMapping("/add")
    public String getDish(Model model){
        model.addAttribute(new Chef());
        model.addAttribute("items", chefRepository.findAll());
        return "potlucklist";
    }

    @PostMapping("/add")
    public String processDish(@Valid Chef chef, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "potlucklist";
        }
        chefRepository.save(chef);
        return "redirect:/add";
    }

    @RequestMapping("/all")
    public String getAllChefs(Model model)
    {
        model.addAttribute("items", chefRepository.findAll());
        return "potlucklistonly";
    }
}
