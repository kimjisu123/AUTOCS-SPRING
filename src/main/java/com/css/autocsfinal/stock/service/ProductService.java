package com.css.autocsfinal.stock.service;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.stock.dto.ProductDTO;
import com.css.autocsfinal.stock.entity.Product;
import com.css.autocsfinal.stock.repository.ProductRepository;
import org.modelmapper.ModelMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    /* 물품 조회 */
    public int selectProductAll() {

        log.info("[ProductService] selectProductTotal Start ===================");

        /* 페이징 처리 결과를 Page 타입으로 반환 받는다. */
//        List<Product> productList = productRepository.findByStatus("Y");
        List<Product> productList = productRepository.findAll();
        log.info("[ProductService] productList.size : {}", productList.size());
        log.info("[ProductService] selectProductTotal End ===================");

        return productList.size();
    }

    public List<ProductDTO> selectProductListWithPaging(Criteria cri) {

        log.info("[ProductService] selectProductListWithPaging Start ===================");
        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productNo").descending());

        Page<Product> result = productRepository.findAll(paging);

        List<ProductDTO> productList = result.stream()
                .map(product -> modelMapper
                        .map(product, ProductDTO.class)).collect(Collectors.toList());

        log.info("[ProductService] selectProductListWithPaging End ===================");
        return productList;
    }

//    public List<ProductDTO> selectProductListWithPaging(Criteria cri) {
//
//        log.info("[ProductService] selectProductListWithPaging Start ===================");
//        int index = cri.getPageNum() - 1;
//        int count = cri.getAmount();
//        Pageable paging = PageRequest.of(index, count, Sort.by("productNo").descending());
//
//        Page<Product> result = productRepository.findByStatus("Y", paging);
//
//        List<ProductDTO> productList = result.stream()
//                .map(product -> modelMapper
//                        .map(product, ProductDTO.class)).collect(Collectors.toList());
//
//        log.info("[ProductService] selectProductListWithPaging End ===================");
//        return productList;
//    }



}
