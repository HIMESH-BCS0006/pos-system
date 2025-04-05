package com.SpringbootAcademy.pos2.dto.queryInterface;

import java.util.Date;
import java.util.List;

public interface OrderDetailInterface {


    String getCustomerName();
    String getCustomerAddress();
    List<String> getContactNumber();
    Date getDate();
    double getTotal();

}




