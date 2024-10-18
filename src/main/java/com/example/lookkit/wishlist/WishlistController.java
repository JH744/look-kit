package com.example.lookkit.wishlist;

import jakarta.servlet.http.HttpSession;
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
    public String getWishList(Model model, HttpSession session) {
        long userId = (long) session.getAttribute("userid");
        model.addAttribute("wishlist", dao.getWishList(userId));  // "inquiries" 대신 "wishlist"로 수정
        return "mypage/wishlist";
    }
}
