package topjava.graduation.web;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping ("/user")
public class UserRegistrationController {
    List<String> userList=new ArrayList<>();

    @PostMapping
    public String registrateUser(String name, String email, String password){

        if (userList.contains(name)){
            return "Sorry";
        }else userList.add(name);

        return name + "created";
    }


    @GetMapping
    @RequestMapping("/list")
    public List<String> allUsers(){
        return userList;
    }

    @GetMapping
    public String getUser(int index){
        return userList.get(index);
    }
}
