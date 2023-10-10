package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ItemRepo repo;


    @Override
    public void addItem(ItemDTO dto) {
        if (repo.existsById(dto.getCode()))
            throw new RuntimeException("Error, " + dto.getCode() + " is already added!");
        repo.save(mapper.map(dto, Item.class));
    }

    @Override
    public void updateItem(ItemDTO dto) {
        if (!repo.existsById(dto.getCode())) throw new RuntimeException("Error, item is not exists!");
        repo.save(mapper.map(dto, Item.class));
    }

    @Override
    public void deleteItem(String code) {
        if (!repo.existsById(code)) throw new RuntimeException("Error, item is not exists!");
        repo.deleteById(code);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<ItemDTO>>() {
        }.getType());
    }

    @Override
    public ItemDTO findItem(String code) {
        if (!repo.existsById(code)) throw new RuntimeException("Error, item is not exists!");
        return mapper.map(repo.findById(code), ItemDTO.class);
    }
}
