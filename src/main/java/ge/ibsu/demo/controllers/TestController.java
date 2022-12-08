package ge.ibsu.demo.controllers;

import ge.ibsu.demo.DTO.TestClass;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/test")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/call", method = RequestMethod.GET, produces = {"application/json"})
    public String test(){
        return "hello";
    }
    @ResponseBody
    @RequestMapping(value = "/post", method = RequestMethod.POST, produces = {"application/json"})
    public TestClass testPost(@RequestBody TestClass rd){
        rd.setLastName(rd.getLastName() + "Response");
        return rd;
    }
}
