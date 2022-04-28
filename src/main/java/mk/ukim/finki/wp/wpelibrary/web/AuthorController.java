package mk.ukim.finki.wp.wpelibrary.web;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.model.Publisher;
import mk.ukim.finki.wp.wpelibrary.model.enumerations.Category;
import mk.ukim.finki.wp.wpelibrary.service.AuthorService;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/author")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAuthors(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Author> authors = this.authorService.listAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "list-authors");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.authorService.deleteById(id);
        return "redirect:/author";
    }

 
    @GetMapping("/addAuthor")
    public String showCreateForm(Model model) {
        List<Author> authors = this.authorService.listAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "add-author");
        return "master-template";
    }

    @PostMapping("/add-author")
    public String createAuthor(@RequestParam(required = false)Long id,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String country) {
        if (id != null) {
            this.authorService.update(id, name, surname, country);
        }
        this.authorService.create(name, surname, country);
        return "redirect:/author";
    }

    @GetMapping("/edit-author/{id}")
    public String editItemPage(@PathVariable Long id, Model model) {
        if (this.authorService.findById(id).isPresent()) {
            Author author = this.authorService.findById(id).get();
            model.addAttribute("author", author);
            model.addAttribute("bodyContent", "add-author");
            return "master-template";
        }
        return "redirect:/author/?error=ItemNotFound";//authronotfound
    }
//    @PostMapping("/edit-authors/{id}")
//    public String update(@PathVariable Long id,
//                         @RequestParam String name,
//                         @RequestParam String surname,
//                         @RequestParam String country
//                         ) { //kto long
//        this.authorService.update(id,name,surname,country);
//        return "redirect:/author";
//    }

}
