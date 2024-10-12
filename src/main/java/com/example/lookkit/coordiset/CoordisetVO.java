package com.example.lookkit.coordiset;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoordisetVO {
    private long codiId;
    private String codiName;
    private String codiDescription;
    private String codiThumbnail;
    private double codiPrice;
}
