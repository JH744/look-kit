package com.example.lookkit.wishlist;

import com.example.lookkit.common.dto.ProductWishlistDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistMapper wishlistMapper;

    // 위시리스트 추가&삭제
    public String addWishlistItem(WishlistVO wishlistVO){
        // 해당 상품이 찜 목록에 있는지 확인
        ProductWishlistDTO wishlistItem = wishlistMapper.getWishlistItem(wishlistVO);
//         new WishlistVO(userId,productId,);
        if (wishlistItem != null) {
            // 찜 목록 있다면 삭제
           wishlistMapper.removeWishlistItem(wishlistVO);
            return "deleteOK";
        } else {
            // 찜 목록 없을시 추가
            wishlistMapper.addWishlistItem(wishlistVO);
            return "addOK";
        }
    }


    // 위시리스트 삭제만 구현
    public int removeWishlistItem(WishlistVO wishlistVO){
        return wishlistMapper.removeWishlistItem(wishlistVO);
    };
}
