package com.example.lookkit.coordiset;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoordisetService {

    private final CoordisetMapper coordisetMapper;


    // 코디 세트와 상품 리스트를 함께 가져오는 메서드
    public List<CoordiProductsDTO> getOldestEightCoordiWithProducts() {
        return coordisetMapper.getAllCoordiWithProducts();
    }

    // 최신 8개의 코디 세트만 가져오는 메서드
    public List<CoordisetVO> getLatestEightCodiSets() {
        return coordisetMapper.getLatestEightCodiSets();
    }

}
