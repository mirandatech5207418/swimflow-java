package com.swimflow.api;
import com.swimflow.model.*;import com.swimflow.service.*;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;import org.springframework.http.*;import java.util.*;
@RestController @RequestMapping("/api/items") public class ItemController {
 private final ItemService service;public ItemController(ItemService service){this.service=service;}
 @GetMapping public List<SwimItem> list(){return service.list();}
 @GetMapping("/{id}") public SwimItem get(@PathVariable Long id){return service.get(id);}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public SwimItem create(@Valid @RequestBody Requests.Item item){return service.create(item);}
 @PostMapping("/{id}/components/{childId}") public SwimItem add(@PathVariable Long id,@PathVariable Long childId){return service.addComponent(id,childId);}
 @GetMapping("/{id}/availability") public Map<String,Object> availability(@PathVariable Long id){return Map.of("itemId",id,"available",service.available(id));}
}
