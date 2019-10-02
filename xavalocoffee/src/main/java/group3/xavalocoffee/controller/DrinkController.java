package group3.xavalocoffee.controller;

import group3.xavalocoffee.constant.Constant;
import group3.xavalocoffee.dto.ServiceResponseDTO;
import group3.xavalocoffee.entities.Drink;
import group3.xavalocoffee.service.DrinkService;
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
public class DrinkController {

    private static final Logger logger = Logger.getLogger(DrinkController.class.getName());

    @Autowired
    private DrinkService drinkService;

    @GetMapping(Constant.DRINK_API)
    public ResponseEntity<ServiceResponseDTO> findAllDrink(){
        logger.info(Constant.BEGIN + "findAllDrink");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            List<Drink> listDrink = drinkService.findAllDrink();
            response.setData(listDrink);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.FAILED_DEPENDENCY);
        }finally {
            logger.info(Constant.END + "findAllDrink");
        }
    }

    @GetMapping(Constant.DRINK_API+"/category/{categoryId}")
    public ResponseEntity<ServiceResponseDTO> findDrinkByCategory(@PathVariable int categoryId){
        logger.info(Constant.BEGIN + "findDrinkByCategory");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            List<Drink> listDrink = drinkService.findDrinkByCategory(categoryId);
            response.setData(listDrink);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.FAILED_DEPENDENCY);
        }finally {
            logger.info(Constant.END + "findDrinkByCategory");
        }
    }


    @PostMapping(Constant.DRINK_API)
    public ResponseEntity<ServiceResponseDTO> createDrink(@RequestBody Drink drink){
        logger.info(Constant.BEGIN + "createDrink");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            drinkService.createDrink(drink);
            response.setMessage("Created");
            return  new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.FAILED_DEPENDENCY);
        }finally {
            logger.info(Constant.END + "createDrink");
        }
    }

    @PutMapping(Constant.DRINK_API)
    public ResponseEntity<ServiceResponseDTO> updateDrink(@RequestBody Drink drink){
        logger.info(Constant.BEGIN + "updateDrink");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            drinkService.updateDrink(drink);
            response.setMessage("Updated");
            return  new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.FAILED_DEPENDENCY);
        }finally {
            logger.info(Constant.END + "updateDrink");
        }
    }

    @DeleteMapping(Constant.DRINK_API + "/{id}")
    public ResponseEntity<ServiceResponseDTO> deleteDrink(@PathVariable int id){
        logger.info(Constant.BEGIN + "deleteDrink");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            drinkService.updateDisableDrink(id, true);
            response.setMessage("Deleted");
            return  new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.FAILED_DEPENDENCY);
        }finally {
            logger.info(Constant.END + "deleteDrink");
        }
    }

    @DeleteMapping(Constant.DRINK_API + "/undelete/{id}")
    public ResponseEntity<ServiceResponseDTO> unDeleteDrink(@PathVariable int id){
        logger.info(Constant.BEGIN + "unDeleteDrink");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            drinkService.updateDisableDrink(id, false);
            response.setMessage("Undeleted");
            return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.FAILED_DEPENDENCY);
        }finally {
            logger.info(Constant.END + "unDeleteDrink");
        }
    }

}
