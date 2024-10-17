package com.example.lookkit.inquiry;

import com.example.lookkit.common.dto.InquiryImagesDTO;
import com.example.lookkit.common.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class InquiryService {
    @Autowired
    InquiryMapper dao;

    public List<InquiryVO> getInquiryList(long userId){
        return  dao.getList(userId);
    }

    @Transactional
    public void deleteInquiry(long inquiryId){
        dao.deleteInquiry(inquiryId);
    }

    @Transactional
    public void uploadInquiry(long userId, String inquiryTitle, String inquiryContents, List<MultipartFile> files) throws IOException {
        // 문의 정보 저장
        InquiryVO vo = InquiryVO.builder()
                .userId(userId)
                .inquiryTitle(inquiryTitle)
                .inquiryContents(inquiryContents)
                .build();

        dao.uploadInquiry(vo);

        // 이미지가 있을 경우 처리
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                String savedFileName = FileUtil.saveFile(file);
                String imagePath = "/upload/inquiry/" + savedFileName;

                // 이미지 정보 DB 저장
                InquiryImageVO ivo = InquiryImageVO.builder()
                        .inquiryId(vo.getInquiryId())  // 문의 ID 사용
                        .imagePath(imagePath)
                        .build();
                dao.uploadInquiryImage(ivo);
            }
        }
    }

    public InquiryImagesDTO getInquiry(long inquiryId){
        return dao.getInquiryWithImages(inquiryId);
    }
}
