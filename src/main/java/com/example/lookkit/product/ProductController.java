package com.example.lookkit.product;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.lookkit.cart.CartService;
import com.example.lookkit.cart.CartVO;
import com.example.lookkit.review.ReviewService;
import com.example.lookkit.review.ReviewVO;
import com.example.lookkit.order.OrderDetailVO;
import com.example.lookkit.order.OrderService;
import com.example.lookkit.order.OrderVO;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final CartService cartService;
    private final OrderService orderService;

    
    public ProductController(ProductService productService, ReviewService reviewService, CartService cartService, OrderService orderService) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/{productId}")
    public String productPage(@PathVariable("productId") int productId, Model model) {
        ProductVO product = productService.getProductById(productId);
        List<ReviewVO> reviews = reviewService.getReviewsByProductId(productId);

        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        return "product";
    }


    @GetMapping("/cart")
    public String cartPage(Model model) {
        int userId = 1; // 실제 서비스에서는 사용자 ID를 인증 정보로 가져와야 합니다.
        List<CartVO> cartItems = cartService.getCartItems(userId);
        int totalAmount = (int) cartService.calculateTotalAmount(userId);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        return "cart";
    }

    @PostMapping("/addToCart")
    @ResponseBody
    public String addToCart(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity) {
        int userId = 1; // 실제 서비스에서는 사용자 ID를 인증 정보로 가져와야 합니다.
    
        try {
            if (productService.isStockAvailable(productId, quantity)) {
                CartVO cartVO = new CartVO();
                cartVO.setUserId(userId);
                cartVO.setProductId(productId);
                cartVO.setQuantity(quantity);
                cartService.addItemToCart(cartVO);
                return "Item added to cart successfully.";
            } else {
                return "Not enough stock available.";
            }
        } catch (Exception e) {
            return "Error adding item to cart.";
        }
    }

    @PostMapping("/buyNow")
    @ResponseBody
    public String buyNow(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity) {
    int userId = 1; // 실제 서비스에서는 사용자 ID를 인증 정보로 가져와야 합니다.

    try {
        if (productService.isStockAvailable(productId, quantity)) {
            ProductVO product = productService.getProductById(productId);
            if (product == null) {
                return "Product not found.";
            }

            OrderVO orderVO = new OrderVO();
            orderVO.setUserId(userId);
            orderVO.setTotalAmount(product.getProductPrice() * quantity);
            orderVO.setOrderStatus("pending");
            orderVO.setOrderAddressee("홍길동"); // 예시 값, 실제로는 사용자 정보 사용
            orderVO.setOrderAddress("서울특별시"); // 예시 값, 실제로는 사용자 정보 사용
            orderVO.setOrderPhone("010-1234-5678"); // 예시 값, 실제로는 사용자 정보 사용

            OrderDetailVO orderDetail = OrderDetailVO.builder()
                    .productId(productId)
                    .userId(userId)
                    .quantity(quantity)
                    .productPrice(product.getProductPrice())
                    .productName(product.getProductName())
                    .productThumbnail(product.getProductThumbnail())
                    .build();

            orderService.createOrder(orderVO, Collections.singletonList(orderDetail));
            return "Order placed successfully.";
        } else {
            return "Not enough stock available.";
        }
    } catch (Exception e) {
        return "Error placing order.";
    }
}

}
