package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.entity.OrderDetails;
import lk.ijse.spring.entity.Orders;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.repo.OrderDetailsRepo;
import lk.ijse.spring.repo.OrdersRepo;
import lk.ijse.spring.service.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    OrdersRepo ordersRepo;

    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void purchaseOrder(OrdersDTO dto) {
        if (ordersRepo.existsById(dto.getOid())) {
            throw new RuntimeException(dto.getOid()+ " Is already available.!");
        }
        Orders order = mapper.map(dto, Orders.class);
        ordersRepo.save(order);

        for (OrderDetails orderDetail : order.getOrderDetails()) {
            Item item = itemRepo.findById(orderDetail.getItemCode()).get();
            item.setQtyOnHand(item.getQtyOnHand()-orderDetail.getQty());
            itemRepo.save(item);
        }
    }
}
