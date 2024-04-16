package com.sky.controller.user;

import com.sky.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/notify")
public class PayNotifyController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/paySuccess")
    public void paySuccessNotify(){
//        orderService.paySuccess();
    }
}
