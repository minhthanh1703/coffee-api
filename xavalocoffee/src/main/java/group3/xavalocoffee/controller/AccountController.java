package group3.xavalocoffee.controller;

import group3.xavalocoffee.constant.Constant;

import group3.xavalocoffee.dto.ServiceResponseDTO;
import group3.xavalocoffee.entities.Account;
import group3.xavalocoffee.service.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(Constant.API)
public class AccountController {

    private static final Logger logger = Logger.getLogger(AccountController.class.getName());

    @Autowired
    private AccountService accountService;

    @GetMapping(Constant.ACCOUNT_API)
    public ResponseEntity<ServiceResponseDTO> findAllAccount(){
        logger.info(Constant.BEGIN + "findAllAccount");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            List<Account> listAccount = accountService.findAllAccount();
            response.setData(listAccount);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.FAILED_DEPENDENCY);
        }finally {
            logger.info(Constant.END + "findAllAccount");
        }
    }

    @PostMapping(Constant.ACCOUNT_API)
    public ResponseEntity<ServiceResponseDTO> createAccount(@RequestBody Account account){
        logger.info(Constant.BEGIN + "createAccount");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            accountService.createAccount(account);
            response.setMessage("Created");
            return  new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.FAILED_DEPENDENCY);
        }finally {
            logger.info(Constant.END + "createAccount");
        }
    }

    @PutMapping(Constant.ACCOUNT_API)
    public ResponseEntity updateAccount(@RequestBody Account account){
        logger.info(Constant.BEGIN + "updateAccount");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            accountService.updateAccount(account);
            response.setMessage("Updated");
            return  new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.FAILED_DEPENDENCY);
        }finally {
            logger.info(Constant.END + "updateAccount");
        }
    }

    @DeleteMapping(Constant.ACCOUNT_API + "/{username}")
    public ResponseEntity deleteAccount(@PathVariable String username){
        logger.info(Constant.BEGIN + "deleteAccount");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            accountService.updateDisableAccount(username, true);
            response.setMessage("Deleted");
            return  new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.FAILED_DEPENDENCY);
        }finally {
            logger.info(Constant.END + "deleteAccount");
        }
    }

    @DeleteMapping(Constant.ACCOUNT_API + "/undelete/{username}")
    public ResponseEntity unDeleteAccount(@PathVariable String username){
        logger.info(Constant.BEGIN + "unDeleteAccount");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            accountService.updateDisableAccount(username, false);
            response.setMessage("Undeleted");
            return  new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.FAILED_DEPENDENCY);
        }finally {
            logger.info(Constant.END + "unDeleteAccount");
        }
    }


}
