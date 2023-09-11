package com.css.autocsfinal.stock.service;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.stock.dto.BillDTO;
import com.css.autocsfinal.stock.dto.BillDetailDTO;
import com.css.autocsfinal.stock.entity.Bill;
import com.css.autocsfinal.stock.entity.BillDetail;
import com.css.autocsfinal.stock.entity.OrderProductDetail;
import com.css.autocsfinal.stock.repository.BillDetailRepository;
import com.css.autocsfinal.stock.repository.BillRepository;
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
public class BillService {

    private final BillRepository billRepository;
    private final BillDetailRepository billDetailRepository;

    private final ModelMapper modelMapper;

    public BillService(BillRepository billRepository, BillDetailRepository billDetailRepository, ModelMapper modelMapper) {
        this.billRepository = billRepository;
        this.billDetailRepository = billDetailRepository;
        this.modelMapper = modelMapper;
    }


    /* 계산서 입력 */
    @Transactional
    public String insertBill(BillDTO billDTO) {

        int result = 0;

        try{
            Bill insertBill = modelMapper.map(billDTO, Bill.class);

            insertBill.setRegistDate(new Date(System.currentTimeMillis()));
            insertBill.setRefCompanyNo(0);

            billRepository.save(insertBill);
            result = 1;

        } catch (Exception e){
            System.out.println("check");
            throw new RuntimeException(e);
        }

        return (result > 0)? "계산서 입력 성공": "계산서 입력 실패";
    }

    /* 계산서 조회 - 페이지사이즈 */
    public int selectBillAll() {

        List<BillDetail> billList = billDetailRepository.findAll();
        return billList.size();
    }

    /* 계산서 조회 - 이름,날짜조회 (페이지사이즈) */
    public int billListSize(String store, Date startDate, Date endDate) {
        List<Tuple> billList = billRepository.billListSize(store, startDate, endDate);
        return billList.size();
    }

    /* 계산서 조회 - 영업점,날짜조회 (페이지사이즈) */
    public int myBillListSize(int store, Date startDate, Date endDate) {
        List<Tuple> billList = billRepository.myBillListSize(store, startDate, endDate);
        return billList.size();
    }

    /* 영업점별 계산서 조회 */
//    public List<BillDTO> selectBillList(int storeNo) {
//
//        List<Bill> result = billRepository.findByRefStoreInfoNo(storeNo);
//
//        List<BillDTO> billList = result.stream()
//                .map(bill -> modelMapper
//                        .map(bill, BillDTO.class)).collect(Collectors.toList());
//        return billList;
//    }

    /* 계산서 조회 페이징 */
    public List<BillDetailDTO> selectBillListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("billNo").descending());

        Page<BillDetail> result = billDetailRepository.findAll(paging);

        List<BillDetailDTO> billList = result.stream()
                .map(bill -> modelMapper
                        .map(bill, BillDetailDTO.class)).collect(Collectors.toList());
        return billList;
    }

    /* 주문물품 조회 - 이름, 날짜 검색, 페이징 */
    public List<Tuple> billList(Criteria cri, String store, Date startDate, Date endDate) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count);

        Page<Tuple> result =billRepository.billList(store, startDate, endDate, paging);

        List<Tuple> billList = result.stream()
                .map(tuple -> modelMapper
                        .map(tuple, Tuple.class)).collect(Collectors.toList());
        return billList;
    }

    /* 주문물품 조회 - 영업점, 날짜 검색, 페이징 */
    public List<Tuple> myBillList(Criteria cri, int store, Date startDate, Date endDate) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count);

        Page<Tuple> result =billRepository.myBillList(store, startDate, endDate, paging);

        List<Tuple> billList = result.stream()
                .map(tuple -> modelMapper
                        .map(tuple, Tuple.class)).collect(Collectors.toList());
        return billList;
    }

    /* 계산서 조회 - 주문번호별 계산서 조회*/
    public BillDetail selectBill(int orderNo) {

        BillDetail result = billDetailRepository.findByRefOrderNoOrderNo(orderNo);

        return result;
    }


}
