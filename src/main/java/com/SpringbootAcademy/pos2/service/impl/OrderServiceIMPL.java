package com.SpringbootAcademy.pos2.service.impl;

import com.SpringbootAcademy.pos2.Entity.Order;
import com.SpringbootAcademy.pos2.Entity.OrderDetails;
import com.SpringbootAcademy.pos2.dto.paginated.PaginatedResponseOrderDetailsDTO;
import com.SpringbootAcademy.pos2.dto.queryInterface.OrderDetailInterface;
import com.SpringbootAcademy.pos2.dto.request.RequestOrderDetailSaveDTO;
import com.SpringbootAcademy.pos2.dto.request.RequestOrderSaveDTO;
import com.SpringbootAcademy.pos2.dto.response.ResponseOrderDetailsDTO;
import com.SpringbootAcademy.pos2.repo.CustomerRepo;
import com.SpringbootAcademy.pos2.repo.ItemRepo;
import com.SpringbootAcademy.pos2.repo.OrderDetailRepo;
import com.SpringbootAcademy.pos2.repo.OrderRepo;
import com.SpringbootAcademy.pos2.service.OrderService;
import com.SpringbootAcademy.pos2.util.mappers.OrderMapper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional
    public String saveOrder(RequestOrderSaveDTO requestOrderSaveDTO) {

        Order order = new Order(
                customerRepo.getById(requestOrderSaveDTO.getCustomer()),
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal()

        );

        orderRepo.save(order);

        if (orderRepo.existsById(order.getOrderId())) {
//            Set<OrderDetails> orderDetailsList = orderMapper.DTOToOrderDetailsSet(requestOrderSaveDTO.getOrderDetails());
//
//
//
//            // Convert Set to List
//            List<OrderDetails> list = new ArrayList<>(orderDetailsList);
//            List<RequestOrderDetailSaveDTO> requestOrderDetails = new ArrayList<>(requestOrderSaveDTO.getOrderDetails());
//
//            for (int i = 0; i < list.size(); i++) {
//                list.get(i).setOrders(order);
//                list.get(i).setItems(itemRepo.getById(requestOrderDetails.get(i).getItems())); // Fixing Set access issue
//            }
//
//            if(orderDetailsList.size() > 0){
//                orderDetailRepo.saveAll(orderDetailsList);
//
//            }
//            return "saved";
//
//        }
//
//        throw  new RuntimeException("Order could not be saved");


            List<OrderDetails> orderDetailsList = modelMapper.map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>(){}
                    .getType());

            for(int i = 0; i<orderDetailsList.size(); i++){

                orderDetailsList.get(i).setOrders(order);
                orderDetailsList.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));

            }

            if (orderDetailsList.size() > 0) {
                orderDetailRepo.saveAll(orderDetailsList);
            }
            return  "saved";

        }

        return null;
    }

    @Override
    public PaginatedResponseOrderDetailsDTO getOrderDetails(boolean status, int page, int size) {

        List<OrderDetailInterface> responseOrderDetailsDTOList = orderRepo.getAllOrderDetails(status, PageRequest.of(page,size));
       // System.out.println("come "+responseOrderDetailsDTOList.get(0).getCustomerName());

        List<ResponseOrderDetailsDTO> list = new ArrayList<>();

        for(OrderDetailInterface orderDetail : responseOrderDetailsDTOList){
            ResponseOrderDetailsDTO responseOrderDetailsDTO = new ResponseOrderDetailsDTO(
                    orderDetail.getCustomerName(),
                    orderDetail.getCustomerAddress(),
                    orderDetail.getContactNumber(),
                    orderDetail.getDate(),
                    orderDetail.getTotal()
            );

            list.add(responseOrderDetailsDTO);

        }

        PaginatedResponseOrderDetailsDTO paginatedList = new PaginatedResponseOrderDetailsDTO(
                list,
                orderRepo.countAllOrderDetails(status)
        );



        return paginatedList;
    }


}
