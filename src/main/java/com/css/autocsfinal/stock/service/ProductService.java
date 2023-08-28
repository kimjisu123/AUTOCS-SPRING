package com.css.autocsfinal.stock.service;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.stock.dto.CategoryDTO;
import com.css.autocsfinal.stock.dto.ProductDTO;
import com.css.autocsfinal.stock.dto.ProductDetailDTO;
import com.css.autocsfinal.stock.dto.StandardDTO;
import com.css.autocsfinal.stock.entity.Category;
import com.css.autocsfinal.stock.entity.Product;
import com.css.autocsfinal.stock.entity.ProductDetail;
import com.css.autocsfinal.stock.entity.Standard;
import com.css.autocsfinal.stock.repository.ProductDetailRepository;
import com.css.autocsfinal.stock.repository.ProductRepository;
import org.modelmapper.ModelMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ProductDetailRepository productDetailRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
        this.modelMapper = modelMapper;
    }

    /* 물품 조회 (페이지사이즈) */
    public int selectProductAll() {

        log.info("[ProductService] selectProductTotal Start ===================");

        /* 페이징 처리 결과를 Page 타입으로 반환 받는다. */
        List<ProductDetail> productList = productDetailRepository.findAll();
        log.info("[ProductService] productList.size : {}", productList.size());
        log.info("[ProductService] selectProductTotal End ===================");

        return productList.size();
    }

    /* 물품 조회 - 이름조회 (페이지사이즈) */
    public int selectProductListByName(String search) {

        /* 페이징 처리 결과를 Page 타입으로 반환 받는다. */
        List<ProductDetail> productList = productDetailRepository.findByNameContaining(search);

        return productList.size();
    }

    /* 물품 조회 페이징*/
    public List<ProductDetailDTO> selectProductListWithPaging(Criteria cri) {

        log.info("[ProductService] selectProductListWithPaging Start ===================");
        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productNo").descending());

        Page<ProductDetail> result =productDetailRepository.findAll(paging);

        List<ProductDetailDTO> productList = result.stream()
                .map(productDetail -> modelMapper
                        .map(productDetail, ProductDetailDTO.class)).collect(Collectors.toList());

        log.info("[ProductService] selectProductListWithPaging End ===================");
        return productList;
    }

    /* 물품 조회 - 이름검색*/
    public List<ProductDetailDTO> selectProductListByNameWithPaging(Criteria cri,String search) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productNo").descending());

        Page<ProductDetail> result =productDetailRepository.findByNameContaining(search,paging);

        List<ProductDetailDTO> productList = result.stream()
                .map(productDetail -> modelMapper
                        .map(productDetail, ProductDetailDTO.class)).collect(Collectors.toList());

        return productList;
    }



    /* 물품 입력 */
    @Transactional
    public String insertProduct(ProductDTO productDTO) {
        log.info(" insertProduct  ===================");

        int result = 0; // 결과에 따른 값을 구분하기위한 용도의 변수

        try{
            // 저장을 위해서 일반 DTO객체를 Entity객체로 변경
            Product insertProduct = modelMapper.map(productDTO, Product.class);

            insertProduct.setRegistDate(new Date(System.currentTimeMillis()));
            insertProduct.setStatus("Y");


            productRepository.save(insertProduct);
            result = 1;

        } catch (Exception e){
            System.out.println("check");
            throw new RuntimeException(e);
        }
        return (result > 0)? "물품 입력 성공": "물품 입력 실패";
    }


}
