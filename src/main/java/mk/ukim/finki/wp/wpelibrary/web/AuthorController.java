package mk.ukim.finki.wp.wpelibrary.web;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.model.Publisher;
import mk.ukim.finki.wp.wpelibrary.model.enumerations.Category;
import mk.ukim.finki.wp.wpelibrary.service.AuthorService;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping
    public String getAuthors(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Author> authors=this.authorService.listAll();
        model.addAttribute("authors",authors);
        model.addAttribute("bodyContent","list-authors");
        return "master-template";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        this.authorService.deleteById(id);
        return "redirect:/author";
    }
//    @GetMapping("/add-author")
//    public  String addItemPage(Model model){
//        List<Author> authors = this.authorService.listAll();
//        model.addAttribute("authors",authors);
//        return "add-author";
//    }
//    @GetMapping("/edit-author/{id}")
//    public String editItemPage(@PathVariable Long id,Model model){
//        if(this.authorService.findById(id).isPresent()){
//            Author author=this.authorService.findById(id).get();
//            model.addAttribute("author",author);
//
//            return "add-author";
//        }
//        return "redirect:/author/?error=ItemNotFound";
//    }
//    @PostMapping("/add")//ishte requestmapping
//    public String saveProduct(@RequestParam String name,
//                              @RequestParam String surname,
//                              @RequestParam String country){
//        //Double price, String title,String description, Integer quantity, String imglink, Category category, Long publisherId
//        this.authorService.create(name,surname,country);
//        return "redirect:/author";
//    }
//
//    @PostMapping("/authors/{id}")
//    public String update(@PathVariable Long id,
//                         @RequestParam String name,
//                         @RequestParam String surname,
//                         @RequestParam String country
//                         ) { //kto long
//        this.authorService.update(id,name,surname,country);
//        return "redirect:/author";
//    }
    @GetMapping("/addAuthor")
    public String showCreateForm(Model model) {
        List<Author> authors=this.authorService.listAll();
        model.addAttribute("authors",authors);
        model.addAttribute("bodyContent","add-author");
        return "master-template";
    }

    @PostMapping("/add-author")
    public String createAuthor(@RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String country) {
    this.authorService.create(name,surname,country);
        return "redirect:/author";
    }
    @GetMapping("/edit-author/{id}")
    public String editItemPage(@PathVariable Long id,Model model){
        if(this.authorService.findById(id).isPresent()){
            Author author = this.authorService.findById(id).get();
            model.addAttribute("author",author);
            model.addAttribute("bodyContent","add-author");
            return "master-template";
        }
        return "redirect:/author/?error=ItemNotFound";//authronotfound
    }
    @PostMapping("/edit-authors/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam String country
                         ) { //kto long
        this.authorService.update(id,name,surname,country);
        return "redirect:/author";
    }

}
