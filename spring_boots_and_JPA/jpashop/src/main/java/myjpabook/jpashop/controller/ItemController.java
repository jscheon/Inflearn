package myjpabook.jpashop.controller;

import lombok.RequiredArgsConstructor;
import myjpabook.jpashop.domain.item.Book;
import myjpabook.jpashop.domain.item.Item;
import myjpabook.jpashop.service.ItemService;
import myjpabook.jpashop.web.BookForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());

        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        Book book = new Book();

        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);

        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();

        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book book = (Book) itemService.findOne(itemId);
        BookForm form = new BookForm();

        form.setAuthor(book.getAuthor());
        form.setIsbn(book.getIsbn());
        form.setPrice(book.getPrice());
        form.setStockQuantity(book.getStockQuantity());
        form.setName(book.getName());
        form.setId(book.getId());

        model.addAttribute("form", form);

        return "items/updateItemForm";
    }


    @PostMapping("items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") BookForm form){
        itemService.updateItem(form.getId(), form.getName() , form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }
}

