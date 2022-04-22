package mk.ukim.finki.wp.wpelibrary.web;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.model.Publisher;
import mk.ukim.finki.wp.wpelibrary.model.enumerations.Category;
import mk.ukim.finki.wp.wpelibrary.service.AuthorService;
import mk.ukim.finki.wp.wpelibrary.service.ItemService;
import mk.ukim.finki.wp.wpelibrary.service.PublisherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    public ItemController(ItemService itemService, AuthorService authorService, PublisherService publisherService) {
        this.itemService = itemService;
        this.authorService = authorService;
       this.publisherService = publisherService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error,
                                 @RequestParam(required = false) String title,
                                 Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Item> items=this.itemService.findAll();
        List<Author> authors=this.authorService.listAll();
        if(title==null || title.isEmpty()){
            items=this.itemService.findAll();
        }else{
            items=this.itemService.filter(title);
        }
        model.addAttribute("items",items);
        model.addAttribute("authors",authors);
        model.addAttribute("bodyContent","index");
        return "master-template";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        this.itemService.deleteById(id);
        return "redirect:/item";
    }
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  String addItemPage(Model model){
//        List<Item> items=this.itemService.findAll();
//        List<Author> authors = this.authorService.listAll();
        List<Publisher> publishers=this.publisherService.findAll();
        List<Category> categories = Arrays.asList(Category.values());
//        model.addAttribute("authors",authors);
        model.addAttribute("publishers",publishers);
        model.addAttribute("categories",categories);
        model.addAttribute("bodyContent","proba-add-item");
//        model.addAttribute("items",items);
        return "master-template";
    }
    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editItemPage(@PathVariable Long id,Model model){
        if(this.itemService.findById(id).isPresent()){
            Item item = this.itemService.findById(id).get();
//            List<Author> authors = this.authorService.listAll();
            List<Publisher> publishers=this.publisherService.findAll();
            List<Category> categories = Arrays.asList(Category.values());
//            model.addAttribute("authors",authors);
            model.addAttribute("publishers",publishers);
            model.addAttribute("categories",categories);
            model.addAttribute("item",item);
            model.addAttribute("bodyContent","proba-add-item");
            return "master-template";        }
        return "redirect:/item/?error=ItemNotFound";
    }


    @PostMapping("/add")//ishte requestmapping
    public String saveProduct(@RequestParam Double price,
                              @RequestParam String title,
                              @RequestParam String description,
                              @RequestParam Integer quantity,
                              @RequestParam String imglink,
                              @RequestParam Category category,
                              @RequestParam Long publisherId){
        //Double price, String title,String description, Integer quantity, String imglink, Category category, Long publisherId
        this.itemService.save(price,title,description,quantity,imglink,category,publisherId);
        return "redirect:/item";
    }

    @PostMapping("/items/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam Double price,
                         @RequestParam String title,
                         @RequestParam String description,
                         @RequestParam Integer quantity,
                         @RequestParam String imglink,
                         @RequestParam Category category,
                         @RequestParam Publisher publisherId) { //kto long
        this.itemService.update(id, price,title,description,quantity,imglink,category,publisherId);
        return "redirect:/item";
    }
//    @GetMapping("/search")
//    public String home(Item item, Model model, String title) {
//        if(title!=null) {
//            List<Item> list = itemService.search(title);
//            model.addAttribute("list", list);
//        }else {
//            List<Item> list = itemService.findAll();
//            model.addAttribute("list", list);}
//        return "index";
//    }
}
