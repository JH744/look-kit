package com.example.lookkit.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddressVO {
    private long addressId;
    private long userId;
    private String addressName;
}
