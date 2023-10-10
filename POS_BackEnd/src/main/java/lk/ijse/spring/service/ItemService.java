package lk.ijse.spring.service;

import lk.ijse.spring.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void addItem(ItemDTO dto);

    void updateItem(ItemDTO dto);

    void deleteItem(String code);

    List<ItemDTO> getAllItems();

    ItemDTO findItem(String code);
}
