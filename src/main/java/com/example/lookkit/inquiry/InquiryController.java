package com.example.lookkit.inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mypage")
public class InquiryController {
    @Autowired
    InquiryMapper dao;

    @GetMapping("/inquiry/list")
    public String inquiryList(Model model){
        model.addAttribute("inquiries", dao.getList(1));
        return "mypage/inquiryList";
    }

    @DeleteMapping("/delete/{inquiryId}")
    @Transactional
    public ResponseEntity<Void> inquiryList(@PathVariable int inquiryId){
        dao.deleteInquiry(inquiryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/inquiry/new")
    public String inquiryForm(Model model){
        model.addAttribute("user_id", 1);
        return "mypage/inquiryForm";
    }

//    @PostMapping("/inquiry/upload")



}
