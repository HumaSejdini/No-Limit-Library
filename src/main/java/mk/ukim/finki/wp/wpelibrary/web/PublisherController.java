package mk.ukim.finki.wp.wpelibrary.web;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import mk.ukim.finki.wp.wpelibrary.model.Publisher;
import mk.ukim.finki.wp.wpelibrary.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }
    @GetMapping
    public String getPublishers(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Publisher> publishers = this.publisherService.findAll();
        model.addAttribute("publishers", publishers);
        model.addAttribute("bodyContent", "list-publishers");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.publisherService.delete(id);
        return "redirect:/publisher";
    }

    @GetMapping("/addPublisher")
    public String showCreateForm(Model model) {
        List<Publisher> publishers = this.publisherService.findAll();
        model.addAttribute("publishers", publishers);
        model.addAttribute("bodyContent", "add-publisher");
        return "master-template";
    }

    @PostMapping("/add-publisher")
    public String createAuthor(@RequestParam(required = false)Long id,
                               @RequestParam String name,
                               @RequestParam String contact,
                               @RequestParam String address) {
        if (id != null) {
            this.publisherService.update(id, name, contact, address);
        }
        this.publisherService.create(name, contact, address);
        return "redirect:/publisher";
    }

    @GetMapping("/edit-publisher/{id}")
    public String editItemPage(@PathVariable Long id, Model model) {
        if (this.publisherService.findById(id).isPresent()) {
            Publisher publisher = this.publisherService.findById(id).get();
            model.addAttribute("publisher", publisher);
            model.addAttribute("bodyContent", "add-publisher");
            return "master-template";
        }
        return "redirect:/publisher/?error=PublisherNotFound";
    }
}
