package com.example.lookkit.inquiry;

import com.example.lookkit.common.dto.InquiryImagesDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/mypage/inquiry")
public class InquiryController {
    @Autowired
    InquiryService service;

    @GetMapping("/list")
    public String getInquiryList(Model model, HttpSession session){
        model.addAttribute("inquiries", service.getInquiryList((long)session.getAttribute("userid")));
        return "mypage/inquiryList";
    }

    @DeleteMapping("/delete/{inquiryId}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable int inquiryId){
        service.deleteInquiry(inquiryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/new")
    public String inquiryForm(Model model, HttpSession session){
        model.addAttribute("user_id", (long) session.getAttribute("userid"));
        return "mypage/inquiryForm";
    }

    @PostMapping("/create")
    public ResponseEntity<String> uploadInquiry(
            @RequestParam("title") String inquiryTitle,
            @RequestParam("content") String inquiryContents,
            @RequestParam(value = "files", required = false) List<MultipartFile> files,
            HttpSession session){
        long userId = (long) session.getAttribute("userid");

        try {
            service.uploadInquiry(userId, inquiryTitle, inquiryContents, files);
            return ResponseEntity.ok("문의가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("문의 등록 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/view/{inquiryId}")
    public ModelAndView getInquiry(@PathVariable long inquiryId){
        ModelAndView mv = new ModelAndView("mypage/inquiry");
        InquiryImagesDTO dto = service.getInquiry(inquiryId);
        mv.addObject("inquiry", dto);
        return mv;
    }



}
