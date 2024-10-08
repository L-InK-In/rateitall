package com.linkin.review.openfeign;



import com.linkin.review.openfeign.fallback.UserOpenFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-service", path = "/users", fallback = UserOpenFeignFallback.class)
public interface UserOpenFeign {

    /**
     * 查询用户是否存在
     * @param id
     * @return
     */
    @RequestMapping(value = "/check/{id}", method=RequestMethod.GET)
    boolean UserIsExisted(@PathVariable("id") long id);
}
