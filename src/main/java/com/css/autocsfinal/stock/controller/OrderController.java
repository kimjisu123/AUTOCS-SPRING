package com.css.autocsfinal.stock.controller;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.stock.dto.*;
import com.css.autocsfinal.stock.entity.OrderProduct;
import com.css.autocsfinal.stock.service.OrderService;
import com.css.autocsfinal.stock.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /* 주문번호 전체조회*/
    @GetMapping("/stock/order")
    public ResponseEntity<ResponseDTO> selectOrderListWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        int total = orderService.selectOrderAll();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(orderService.selectOrderListWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 주문물품 전체조회*/
//    @GetMapping("/stock/orderlist")
//    public ResponseEntity<ResponseDTO> selectOrderProductListWithPaging(
//            @RequestParam(name = "offset", defaultValue = "1") String offset){
//
//        int total = orderService.selectOrderProductAll();
//
//        Criteria cri = new Criteria(Integer.valueOf(offset), 10);
//
//        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
//        pagingResponseDTO.setData(orderService.selectOrderProductListWithPaging(cri));
//        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
//    }
    @GetMapping("/stock/orderlist")
    public ResponseEntity<ResponseDTO> orderListWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset,
            @RequestParam(name = "status", defaultValue = "WAITING")String stat,
            @RequestParam(name = "search", defaultValue = "")String search,
            @RequestParam(name = "startDate", defaultValue = "") Date startDate,
            @RequestParam(name = "endDate", defaultValue = "")Date endDate){

        int total = orderService.orderListSize(stat, search, startDate, endDate);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

    log.info("--------------------");
        List<Tuple> tuplePage = orderService.orderList(cri, stat, search, startDate, endDate);

        List<OrderListDTO> OrderListPage = tuplePage.stream()
                .map(tuple -> {
                    BigDecimal orderNo =  tuple.get(0, BigDecimal.class);
                    String storeInfoName = tuple.get(1, String.class);
                    String categoryName = tuple.get(2, String.class);
                    String productName = tuple.get(3, String.class);
                    String unitName = tuple.get(4, String.class);
                    String standardName = tuple.get(5, String.class);
                    BigDecimal price = tuple.get(6, BigDecimal.class);
                    BigDecimal quantity = tuple.get(7, BigDecimal.class);
                    String etc = tuple.get(8, String.class);
                    Date registDate = tuple.get(9, Date.class);
                    String status = tuple.get(10, String.class);


                    return new OrderListDTO(orderNo.intValue(), storeInfoName, categoryName, productName, unitName, standardName,
                            price.intValue(), quantity.intValue(), etc, registDate, status );
                })
                .collect(Collectors.toList());


        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(OrderListPage);
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }


    /* 마지막 주문번호 조회 */
    @GetMapping("/stock/order/findLast")
    public ResponseEntity<ResponseDTO> selectLastOrder(){
        System.out.println("====================");
        System.out.println(orderService.selectLastOrder());

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  orderService.selectLastOrder()));
    }


    /* 주문번호 입력 */
    @PostMapping("/stock/order")
    public ResponseEntity<ResponseDTO> insertOrder(@ModelAttribute OrderDTO orderDTO){

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "주문번호 입력 성공"
                        , orderService.insertOrder(orderDTO)));
    }

    /* 주문번호 수정 */
    @PutMapping("/stock/order/update")
    public ResponseEntity<ResponseDTO> updateOrder(@ModelAttribute OrderDTO orderDTO) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문번호 수정 성공",  orderService.updateOrder(orderDTO)));
    }

    /* 주문물품 입력 */
    @PostMapping("/stock/order/product")
    public ResponseEntity<ResponseDTO> insertOrderProduct(@ModelAttribute OrderProductDTO orderProductDTO){

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "주문물품 입력 성공"
                        , orderService.insertOrderProduct(orderProductDTO)));
    }

    /* 주문물품 수정 */
    @PutMapping("/stock/orderlist")
    public ResponseEntity<ResponseDTO> updateOrderProduct(@ModelAttribute OrderProductDTO orderProductDTO) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문물품 수정 성공",  orderService.updateOrderProduct(orderProductDTO)));
    }

}
