package com.example.lookkit.wishlist;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WishlistVO {
    private long wishlistId;
    private long userId;
    private Long productId;
    private Long codiId;
}
