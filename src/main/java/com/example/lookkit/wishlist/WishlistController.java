package com.example.lookkit.wishlist;

import com.example.lookkit.user.UserVO;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class WishlistController {
    @Autowired
    WishlistMapper dao;

    @GetMapping("/wishlist")
    public String getWishList(Model model) {
        model.addAttribute("wishlist", dao.getWishList(1));  // "inquiries" 대신 "wishlist"로 수정
        return "mypage/wishlist";
    }
}
