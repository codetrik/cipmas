package ng.com.codetrik.cipmas.user.controller;
/*
 @Author Hamzat Habibllahi
*/

import ng.com.codetrik.cipmas.user.dto.UserUpdate;
import ng.com.codetrik.cipmas.user.entity.User;
import ng.com.codetrik.cipmas.user.service.CipmasUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    CipmasUserService cipmasUserService;

    @PostMapping()
    public String createUser(@RequestBody @Valid User user, BindingResult validationResult){
        if(!validationResult.hasErrors()){
           cipmasUserService.createUser(user);
           return "created";
        }else{
            throw new ValidationException("One or more of the parameter is not valid");
        }
    }

    @PutMapping()
    public String updateUser(@RequestBody @Valid UserUpdate userDto, BindingResult validationResult){
        if(!validationResult.hasErrors()){
            cipmasUserService.updateUser(userDto);
            return "updated";
        }else{
            throw new ValidationException("One or more of the parameter is not valid");
        }
    }
}
