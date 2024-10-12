package com.example.lookkit.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
    AddressVO getAddressById(long addressId);
    void insertAddress(AddressVO address);
    void deleteAddressById(long addressId);
}

