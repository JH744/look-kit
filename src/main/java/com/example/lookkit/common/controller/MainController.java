package com.example.lookkit.common.controller;

import com.example.lookkit.coordiset.CoordiProductsDTO;
import com.example.lookkit.coordiset.CoordisetService;
import com.example.lookkit.product.ProductService;
import com.example.lookkit.product.ProductVO;
import com.example.lookkit.user.CustomUser;
import com.example.lookkit.wishlist.WishlistService;
import com.example.lookkit.wishlist.WishlistVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final CoordisetService coordisetService;
    private final ProductService productService;
    private final WishlistService wishlistService;

    @GetMapping("/main")
    public String mainPage(Authentication auth, HttpSession session, Model model){
        // auth가 빈값이 아닐 때 세션 저장
        if(auth != null){
            System.out.println(auth);
            CustomUser user = (CustomUser) auth.getPrincipal();
            session.setAttribute("userid",user.getUserId());
            System.out.println("저장된 유저 유저아이디 : "+user.getUserId());
            // Session의 유효 시간 설정 (3600초 = 60분)
             session.setMaxInactiveInterval(3600);
//            System.out.println(user.getUserId());
//            System.out.println(auth.getName());
//            System.out.println(auth.getAuthorities());
//            System.out.println(auth.isAuthenticated());
        }
        List<CoordiProductsDTO> coordiList = coordisetService.getOldestEightCoordiWithProducts();
        model.addAttribute("coordiList", coordiList);
        return "/home/home.html";
    }


    @GetMapping("/main/category")
    public String mainCategoryPage(Model model, @RequestParam String type){
        System.out.println("카테고리: "+type);
        List<ProductVO> productsList= productService.getProductsByCategory(type);
        model.addAttribute("type", type);
        model.addAttribute("productsList", productsList);
        return "/home/category";
    }



    @GetMapping("/main/search")
    public String mainSearchPage(Model model, @RequestParam String keyword){
        System.out.println("키워드: "+keyword);
        List<ProductVO> productsList= productService.searchProductsByKeyword(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("productsList", productsList);
        return "/home/search";
    }



    // 좋아요 추가 및 삭제
    @GetMapping("/wishlist/item")
    @ResponseBody
    public Map<String, Object> addWishList(@RequestParam long productId, HttpSession session){
        Map<String, Object> response = new HashMap<>();
        long userId = (long) session.getAttribute("userid");
        System.out.println("위시리스트컨트롤러 동작");
        WishlistVO wishlistVO = new WishlistVO();
        wishlistVO.setUserId(userId);
        wishlistVO.setProductId(productId);
        wishlistVO.setCodiId(null);
         String result=wishlistService.addWishlistItem(wishlistVO);

        if ("addOK".equals(result)) {
            response.put("status", "success");
            response.put("message", "상품추가");
        } else if ("deleteOK".equals(result)) {
            response.put("status", "success");
            response.put("message", "상품삭제");
        } else {
            response.put("status", "error");
            response.put("message", "오류발생");
        }
        return response;
    }

    // 좋아요된 상품확인
    @GetMapping("/wishlist/check")
    @ResponseBody
    public Map<String, Object> checkWishItem(@RequestParam("itemId") long productId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        WishlistVO wishlistVO = new WishlistVO();
        long userId = (long) session.getAttribute("userid");
        wishlistVO.setUserId(userId);
        wishlistVO.setProductId(productId);
        response.put("message", wishlistService.addWishlistItem(wishlistVO));
        return response;
    }
}
