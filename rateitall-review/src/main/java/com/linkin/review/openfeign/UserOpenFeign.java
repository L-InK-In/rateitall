package com.linkin.review.openfeign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-service", path = "/users")
public interface UserOpenFeign {

    @RequestMapping(value = "/check/{id}", method=RequestMethod.GET)
    public boolean UserIsExisted(@PathVariable("id") long id);

}
