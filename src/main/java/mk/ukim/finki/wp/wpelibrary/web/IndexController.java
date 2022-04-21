package mk.ukim.finki.wp.wpelibrary.web;

import mk.ukim.finki.wp.wpelibrary.model.Form;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/", "/index" })
public class IndexController {

    @GetMapping
    public String main(Model model) {
        model.addAttribute("form", new Form());
        return "index2";
    }

    @PostMapping
    public String save(Form form, Model model) {
        model.addAttribute("form", form);
        return "saved";
    }
}
