package grocery.com.controller;

import grocery.com.model.GroceryItem;
import grocery.com.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grocery-items")
public class GroceryItemController {
    @Autowired
    private GroceryItemService groceryItemService;

    @PostMapping
    public ResponseEntity<GroceryItem> createItem(@RequestBody GroceryItem groceryItem) {
        return new ResponseEntity<>(groceryItemService.createGroceryItem(groceryItem), HttpStatus.CREATED);
    }

    @GetMapping
    public List<GroceryItem> getAllItems() {
        return groceryItemService.getAllItems();
    }

    @GetMapping("/{id}")
    public GroceryItem getItemById(@PathVariable Long id) {
        return groceryItemService.getItemById(id);
    }

    @PutMapping("/{id}")
    public GroceryItem updateItem(@PathVariable Long id, @RequestBody GroceryItem itemDetails) {
        return groceryItemService.updateItem(id, itemDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        groceryItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
