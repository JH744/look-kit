package com.example.lookkit.wishlist;

import com.example.lookkit.common.dto.ProductWishlistDTO;
import com.example.lookkit.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mypage")
public class WishlistController {
    @Autowired
    WishlistMapper dao;
    @Autowired
    UserService userService;

    @GetMapping("/wishlist")
    public String getWishList(Model model, HttpSession session) {
        long userId = (long) session.getAttribute("userid");
        List<ProductWishlistDTO> wishlist = dao.getWishList(userId);
        model.addAttribute("wishlist", !wishlist.isEmpty() ? wishlist : new ArrayList<>());
        model.addAttribute("username", userService.searchUserName(userId));
        return "mypage/wishlist";
    }
}
