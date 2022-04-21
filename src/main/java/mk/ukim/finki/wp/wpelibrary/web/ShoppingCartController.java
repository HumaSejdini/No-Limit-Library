package mk.ukim.finki.wp.wpelibrary.web;


import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.model.ShoppingCart;
import mk.ukim.finki.wp.wpelibrary.model.User;
import mk.ukim.finki.wp.wpelibrary.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam (required = false) String error, HttpServletRequest req, Model model){
        if(error !=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
//        User user = (User) req.getSession().getAttribute("user");
        String username=(String) req.getRemoteUser();
        ShoppingCart shoppingCart=this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("items",this.shoppingCartService.listAllItemsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent","proba-shopping-cart");
        return "master-template";
    }
    @PostMapping("/add-item/{id}")
    public String addProductToShoppingCart(@PathVariable Long id,HttpServletRequest req,Model model,@ModelAttribute("itemQ") Item item){
        try{
//            User user=(User) req.getSession().getAttribute("user");
            String username=req.getRemoteUser();
            ShoppingCart shoppingCart=this.shoppingCartService.addItemToShoppingCart(username, id);
            model.addAttribute("quantity",item.getQuantity());
            return "redirect:/shopping-cart";

        }catch (RuntimeException exception){
            return "redirect:/shopping-cart?error=" +exception.getMessage();
        }
    }
    @ModelAttribute("itemQ")
    public Item item(){
    return new Item();
    }
}
