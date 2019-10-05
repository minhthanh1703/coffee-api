package group3.xavalocoffee.service;

import group3.xavalocoffee.constant.Constant;
import group3.xavalocoffee.entities.Account;
import group3.xavalocoffee.repository.AccountRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private static final Logger logger = Logger.getLogger(AccountService.class.getName());

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Account> findAllAccount(){
        logger.info(Constant.BEGIN + "findAllAccount");
        try{
            return accountRepository.findAll();
        }finally {
            logger.info(Constant.END + "findAllAccount");
        }

    }

    public Account findAccountByUsername(String username){
        logger.info(Constant.BEGIN + "findAccountByUsername");
        try{
            return accountRepository.findByUsername(username);
        }finally {
            logger.info(Constant.END + "findAccountByUsername");
        }
    }

    public void createAccount(Account account) throws Exception {
        logger.info(Constant.BEGIN + "createAccount");
        try{
            if(accountRepository.findById(account.getUsername()).isPresent()){
                throw new Exception("Username is duplicate");
            }
            saveAccount(account);
        }finally {
            logger.info(Constant.END + "createAccount");
        }

    }

    public void updateAccount(Account account) throws Exception {
        logger.info(Constant.BEGIN + "updateAccount");
        try{
            if(!accountRepository.findById(account.getUsername()).isPresent()){
                throw new Exception("Account is not exist");
            }
            saveAccount(account);
        }finally {
            logger.info(Constant.END + "updateAccount");
        }
    }

    private void saveAccount(Account account) throws Exception{
        if(account.getUsername() == null ||account.getUsername().isEmpty()){
            throw new Exception("Username is empty");
        } else if(account.getPassword() == null || account.getPassword().isEmpty()){
            throw new Exception("Password is empty");
        } else if(account.getFullname() == null ||account.getFullname().isEmpty()){
            throw new Exception("Fullname is empty");
        }else{
            {
                account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
                accountRepository.save(account);
            }
        }
    }

    public void updateDisableAccount(String username, boolean disable) throws Exception{
        logger.info(Constant.BEGIN + "updateDisableAccount");
        try{
            if(username.isEmpty()){
                throw new Exception("Username is empty");
            }
            Account account = accountRepository.findById(username).orElse(null);
            if(account == null){
                throw new Exception("Account not found");
            }else{
                account.setDisable(disable);
                accountRepository.save(account);
            }
        }finally {
            logger.info(Constant.END + "updateDisableAccount");
        }
    }



}
