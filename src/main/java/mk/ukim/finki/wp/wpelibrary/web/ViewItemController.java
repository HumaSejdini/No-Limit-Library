package mk.ukim.finki.wp.wpelibrary.web;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.service.AuthorService;
import mk.ukim.finki.wp.wpelibrary.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/view-item")
public class ViewItemController {
    private final ItemService itemService;
    private final AuthorService authorService;

    public ViewItemController(ItemService itemService, AuthorService authorService) {
        this.itemService = itemService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Item> items = this.itemService.findAll();
        List<Author> authors = this.authorService.listAll();
        model.addAttribute("items", items);
        model.addAttribute("authors", authors);
        return "view-item.html";
    }

    //    @GetMapping("/{id}")
//    public String getTheSelectedItem(@PathVariable Long id, Model model){
//        Item item1=this.itemService.findById(id);
//        List<Author> authors=this.authorService.listAll();
//        model.addAttribute("item1",item1);
//        model.addAttribute("authors",authors);
//        return "view-item.html";
//    }
//da imame i edit
    @GetMapping("/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.itemService.findById(id).isPresent()) {
            Item items = this.itemService.findById(id).get();
            List<Author> authors = this.authorService.listAll();
            List<Item> itemss = this.itemService.findAll();
            model.addAttribute("items", items);
            model.addAttribute("itemss", itemss);
            model.addAttribute("authors", authors);
            return "view-item.html";
        }
        return "redirect:/item/?error=itemNotFound";
    }

}