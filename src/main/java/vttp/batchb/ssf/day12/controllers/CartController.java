package vttp.batchb.ssf.day12.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.batchb.ssf.day12.models.Constants;
import vttp.batchb.ssf.day12.models.LineItem;

@Controller
@RequestMapping("/cart")
public class CartController {

    // GET /cart
    @GetMapping
    public String getCart(
        // explicitly provide the parameter name
        // @RequestParam(name="customer_name", required = true) String name,
        // @RequestParam(name="number_of_items") int numItems,

        // parameter name is inferred from the name of the formal parameter
        @RequestParam(required=false) String name,

        // default value must always be string
        // has a default value if it is not set
        // @RequestParam(defaultValue = "4") int count,

        @RequestParam(required=false) String count,
        Model model) {
        
            int itemCount = 3;
            try{
                itemCount = Integer.parseInt(count);
            } catch (Exception ex){ }

            List<LineItem> items = Constants.generateLineItems(itemCount);

            model.addAttribute("name", name.toUpperCase());
            model.addAttribute("items", items);
            model.addAttribute("count", itemCount);

            return "cart";
    }

    // get all query strings in a multivalue map
    @GetMapping("/map")
    public String getCart(
        @RequestParam MultiValueMap<String, String> form,
        Model model){

            String name = form.getFirst("name");
            String count = form.getFirst("count");

            System.out.printf(">>>>> get first: %s\n", form.getFirst("delivery"));
            System.out.printf(">>>>> get: %s\n", form.get("delivery"));

            int itemCount = 3;
            try{
                itemCount = Integer.parseInt(count);
            } catch (Exception ex){ }

            List<LineItem> items = Constants.generateLineItems(itemCount);

            model.addAttribute("name", name.toUpperCase());
            model.addAttribute("items", items);
            model.addAttribute("count", itemCount);

            return "cart";

    }
    
}
