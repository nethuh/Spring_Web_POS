package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.service.ItemService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService service;

    @PostMapping
    public ResponseUtil addItem(ItemDTO dto) {
        service.addItem(dto);
        return new ResponseUtil("Ok", "successfully added", dto);
    }

    @PutMapping
    public ResponseUtil updateItem(@RequestBody ItemDTO dto) {
        service.updateItem(dto);
        return new ResponseUtil("Ok", "successfully updated", dto);
    }

    @DeleteMapping
    public ResponseUtil deleteItem(String code) {
        service.deleteItem(code);
        return new ResponseUtil("Ok", "successfully deleted", code);
    }

    @GetMapping
    public ResponseUtil getAllItems() {
        return new ResponseUtil("Ok", "successfully loaded", service.getAllItems());
    }

    @GetMapping(params = {"code"})
    public ResponseUtil findItem(String code) {
        return new ResponseUtil("Ok", "successfully searched", service.findItem(code));
    }
}