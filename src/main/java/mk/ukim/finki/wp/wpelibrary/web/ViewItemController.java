package mk.ukim.finki.wp.wpelibrary.web;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.service.AuthorService;
import mk.ukim.finki.wp.wpelibrary.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("form", new Item());
        model.addAttribute("bodyContent","view-item");
        return "master-template";
    }

  
    @GetMapping("/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.itemService.findById(id).isPresent()) {
            Item items = this.itemService.findById(id).get();
            List<Author> authors = this.authorService.listAll();
            List<Item> itemss = this.itemService.findAll();
            model.addAttribute("items", items);
            model.addAttribute("itemss", itemss);
            model.addAttribute("authors", authors);
            model.addAttribute("bodyContent","view-item");
            return "master-template";
        }
        return "redirect:/item/?error=itemNotFound";
    }
    @PostMapping
    public String save(Item form, Model model) {
        model.addAttribute("form", form);
        model.addAttribute("bodyContent","proba-shopping-cart");
        return "master-template";
    }
}
