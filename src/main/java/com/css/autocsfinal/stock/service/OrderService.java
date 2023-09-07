package com.css.autocsfinal.stock.service;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.stock.dto.OrderDTO;
import com.css.autocsfinal.stock.dto.OrderDetailDTO;
import com.css.autocsfinal.stock.dto.OrderProductDTO;
import com.css.autocsfinal.stock.entity.Order;
import com.css.autocsfinal.stock.entity.OrderDetail;
import com.css.autocsfinal.stock.entity.OrderProduct;
import com.css.autocsfinal.stock.entity.OrderProductDetail;
import com.css.autocsfinal.stock.repository.OrderDetailRepository;
import com.css.autocsfinal.stock.repository.OrderProductDetailRepository;
import com.css.autocsfinal.stock.repository.OrderProductRepository;
import com.css.autocsfinal.stock.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderProductDetailRepository orderProductDetailRepository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, OrderProductRepository orderProductRepository, OrderProductDetailRepository orderProductDetailRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderProductRepository = orderProductRepository;
        this.orderProductDetailRepository = orderProductDetailRepository;
        this.modelMapper = modelMapper;
    }

    /* 주문번호 조회 (페이지사이즈) */
    public int selectOrderAll() {

        List<OrderDetail> orderList = orderDetailRepository.findByStatus("Y");

        return orderList.size();
    }


    /* 주문번호 조회 페이징*/
    public List<OrderDetail> selectOrderListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("orderNo").descending());

        Page<OrderDetail> result =orderDetailRepository.findByStatus("Y", paging);

        List<OrderDetail> orderList = result.stream()
                .map(order -> modelMapper
                        .map(order, OrderDetail.class)).collect(Collectors.toList());
        return orderList;
    }

    /* 마지막 주문번호 조회 */
    public int selectLastOrder() {

        int lastOrderNo = orderRepository.findLastOrderNo();

        System.out.println("------------------");
        System.out.println(lastOrderNo);
        return lastOrderNo;
    }

    /* 주문물품 조회 (페이지사이즈) */
    public int selectOrderProductAll() {

        List<OrderProductDetail> orderProdoctList = orderProductDetailRepository.findAll();

        return orderProdoctList.size();
    }

    /* 주문물품 조회 - 상태,이름,날짜조회 (페이지사이즈) */
    public int orderListSize(String status,String search, Date startDate, Date endDate) {

        List<Tuple> orderList = orderProductRepository.orderListSize(status, search, startDate, endDate);

        return orderList.size();
    }


    /* 주문물품 조회 페이징*/
    public List<OrderProductDetail> selectOrderProductListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("orderProductNo").descending());

        Page<OrderProductDetail> result =orderProductDetailRepository.findAll(paging);

        List<OrderProductDetail> orderProdoctList = result.stream()
                .map(orderProduct -> modelMapper
                        .map(orderProduct, OrderProductDetail.class)).collect(Collectors.toList());
        return orderProdoctList;
    }

    /* 주문물품 조회 - 상태, 이름, 날짜 검색, 페이징 */
    public List<Tuple> orderList(Criteria cri, String status, String search, Date startDate, Date endDate) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count);

        Page<Tuple> result =orderProductRepository.orderList(status, search, startDate, endDate, paging);

        List<Tuple> orderList = result.stream()
                .map(tuple -> modelMapper
                        .map(tuple, Tuple.class)).collect(Collectors.toList());
        return orderList;
    }



    /* 주문번호 입력 */
    @Transactional
    public String insertOrder(OrderDTO orderDTO) {

        int result = 0;

        try{
            Order insertOrder = modelMapper.map(orderDTO, Order.class);
            insertOrder.setRegistDate(new Date(System.currentTimeMillis()));
            insertOrder.setStatus("N");
            orderRepository.save(insertOrder);
            result = 1;

        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return (result > 0)? "주문번호 입력 성공": "주문번호 입력 실패";
    }

    /* 주문번호 수정 */
    @Transactional
    public String updateOrder(OrderDTO orderDTO) {
        int result = 0;
        try {
            Order order = orderRepository.findById(orderDTO.getOrderNo()).get();
            order.setRefBillNo(orderDTO.getRefBillNo());
            order.setStoreInfoNo(orderDTO.getStoreInfoNo());
            order.setStatus(orderDTO.getStatus());
            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (result > 0) ? "주문번호 업데이트 성공" : "주문번호 업데이트 실패";
    }


    /* 주문물품 입력 */
    @Transactional
    public String insertOrderProduct(OrderProductDTO orderProductDTO) {

        int result = 0;

        try{
            OrderProduct insertOrderProduct = modelMapper.map(orderProductDTO, OrderProduct.class);
            insertOrderProduct.setRegistDate(new Date(System.currentTimeMillis()));
            insertOrderProduct.setStatus("WAITING");
            orderProductRepository.save(insertOrderProduct);
            result = 1;

        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return (result > 0)? "주문물품 입력 성공": "주문물품 입력 실패";
    }

    /* 주문물품 수정 */
    @Transactional
    public String updateOrderProduct(OrderProductDTO orderProductDTO) {
        int result = 0;
        try {
            OrderProduct orderProduct = orderProductRepository.findById(orderProductDTO.getOrderProductNo()).get();
            orderProduct.setStatus(orderProductDTO.getStatus());
            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (result > 0) ? "주문물품 업데이트 성공" : "주문물품 업데이트 실패";
    }


}
