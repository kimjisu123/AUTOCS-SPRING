package com.css.autocsfinal.stock.service;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.stock.dto.OrderDTO;
import com.css.autocsfinal.stock.entity.Order;
import com.css.autocsfinal.stock.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    /* 주문번호 조회 (페이지사이즈) */
    public int selectOrderAll() {

        List<Order> orderList = orderRepository.findAll();

        return orderList.size();
    }


    /* 주문번호 조회 페이징*/
    public List<OrderDTO> selectOrderListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("orderNo").descending());

        Page<Order> result =orderRepository.findAll(paging);

        List<OrderDTO> orderList = result.stream()
                .map(order -> modelMapper
                        .map(order, OrderDTO.class)).collect(Collectors.toList());
        return orderList;
    }

    /* 마지막 주문번호 조회 */
    public int selectLastOrder() {

        int lastOrderNo = orderRepository.findLastOrderNo();

        System.out.println("------------------");
        System.out.println(lastOrderNo);
        return lastOrderNo;
    }



    /* 주문번호 입력 */
    @Transactional
    public String insertOrder(OrderDTO orderDTO) {

        int result = 0;

        try{
            Order insertOrder = modelMapper.map(orderDTO, Order.class);
            insertOrder.setRegistDate(new Date(System.currentTimeMillis()));
            orderRepository.save(insertOrder);
            result = 1;

        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return (result > 0)? "주문번호 입력 성공": "주문번호 입력 실패";
    }

    /* 주문번호 삭제 */
//    @Transactional
//    public String deleteOrder(int orderNo) {
//
//        int result = 0;
//
//        try{
//            Optional<Order> orderOptional = orderRepository.findById(orderNo);
//            Order deleteOrder = modelMapper.map(orderNo, Order.class);
//            orderRepository.delete(deleteOrder);
//            result = 1;
//        } catch (Exception e){
//            throw new RuntimeException(e);
//        }
//        return (result > 0)? "주문번호 삭제 성공": "주문번호 삭제 실패";
//    }

}
