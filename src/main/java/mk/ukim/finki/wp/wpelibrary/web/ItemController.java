package mk.ukim.finki.wp.wpelibrary.web;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.service.AuthorService;
import mk.ukim.finki.wp.wpelibrary.service.ItemService;
import mk.ukim.finki.wp.wpelibrary.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final AuthorService authorService;
//    private final PublisherService publisherService;

    public ItemController(ItemService itemService, AuthorService authorService, PublisherService publisherService) {
        this.itemService = itemService;
        this.authorService = authorService;
//        this.publisherService = publisherService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Item> items=this.itemService.findAll();
        List<Author> authors=this.authorService.listAll();
        model.addAttribute("items",items);
        model.addAttribute("authors",authors);
        return "index.html";
    }

}
