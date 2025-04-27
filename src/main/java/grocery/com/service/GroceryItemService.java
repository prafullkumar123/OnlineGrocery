package grocery.com.service;

import grocery.com.model.GroceryItem;
import grocery.com.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemService {
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public GroceryItem createGroceryItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    public List<GroceryItem> getAllItems() {
        return groceryItemRepository.findAll();
    }

    public GroceryItem getItemById(Long id) {
        return groceryItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public GroceryItem updateItem(Long id, GroceryItem itemDetails) {
        GroceryItem groceryItem = getItemById(id);
        groceryItem.setName(itemDetails.getName());
        groceryItem.setCategory(itemDetails.getCategory());
        groceryItem.setPrice(itemDetails.getPrice());
        groceryItem.setQuantity(itemDetails.getQuantity());
        return groceryItemRepository.save(groceryItem);
    }

    public void deleteItem(Long id) {
        groceryItemRepository.deleteById(id);
    }
}
