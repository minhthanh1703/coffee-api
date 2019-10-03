package group3.xavalocoffee.controller;

import group3.xavalocoffee.constant.Constant;
import group3.xavalocoffee.dto.BillResponseDTO;

import group3.xavalocoffee.dto.DrinkOrderRequestDTO;
import group3.xavalocoffee.dto.ServiceResponseDTO;
import group3.xavalocoffee.entities.Bill;
import group3.xavalocoffee.security.JWTVerifier;
import group3.xavalocoffee.service.ManagerService;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constant.API)
public class ManagerController {

    private static final Logger logger = Logger.getLogger(ManagerController.class.getName());

    @Autowired
    ManagerService managerService;

    @GetMapping(Constant.MANAGER_API + "/{tableNumber}")
    public ResponseEntity<ServiceResponseDTO> getInfoTable(@PathVariable int tableNumber) {
        logger.info(Constant.BEGIN + "getInfoTable");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            BillResponseDTO dto = managerService.getInfoOfBill(tableNumber);
            response.setData(dto);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.FAILED_DEPENDENCY);
        } finally {
            logger.info(Constant.END + "getInfoTable");
        }
    }

    @PostMapping(Constant.MANAGER_API + "/addDrinkForTable")
    public ResponseEntity addDrinkForTable(@RequestBody DrinkOrderRequestDTO requestDTO) {
        logger.info(Constant.BEGIN + "addDrinkForTable");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            managerService.addDrinkForTable(requestDTO);
            response.setMessage("Added drink for table");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.FAILED_DEPENDENCY);
        } finally {
            logger.info(Constant.END + "addDrinkForTable");
        }
    }

    @PutMapping(Constant.MANAGER_API + "/addCount/{billInfoId}")
    public ResponseEntity addCount(@PathVariable int billInfoId) {
        logger.info(Constant.BEGIN + "addCount");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            managerService.addCountDrink(billInfoId);
            response.setMessage("Add count for drink");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.FAILED_DEPENDENCY);
        } finally {
            logger.info(Constant.END + "addCount");
        }
    }

    @PutMapping(Constant.MANAGER_API + "/subCount/{billInfoId}")
    public ResponseEntity subCount(@PathVariable int billInfoId) {
        logger.info(Constant.BEGIN + "subCount");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            managerService.subCountDrink(billInfoId);
            response.setMessage("Sub count for drink");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.FAILED_DEPENDENCY);
        } finally {
            logger.info(Constant.END + "subCount");
        }
    }

    @PutMapping(Constant.MANAGER_API + "/switchTables/{tableNumberFrom}/{tableNumberTo}")
    public ResponseEntity switchTables(int tableNumberFrom, int tableNumberTo) {
        logger.info(Constant.BEGIN + "switchTables");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            managerService.switchTables(tableNumberFrom, tableNumberTo);
            response.setMessage("Switched table");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.FAILED_DEPENDENCY);
        } finally {
            logger.info(Constant.END + "switchTables");
        }
    }

    @DeleteMapping(Constant.MANAGER_API + "/removeDrink/{billInfoId}")
    public ResponseEntity removeDrink(@PathVariable int billInfoId) {
        logger.info(Constant.BEGIN + "removeDrink");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            managerService.removeDrink(billInfoId);
            response.setMessage("Removed drink from table");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.FAILED_DEPENDENCY);
        } finally {
            logger.info(Constant.END + "removeDrink");
        }
    }

    @PutMapping(Constant.MANAGER_API + "/addDiscount/{billId}/{discount}")
    public ResponseEntity addDiscount(@PathVariable int billId, @PathVariable float discount) {
        logger.info(Constant.BEGIN + "addDiscount");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            managerService.addDiscount(billId, discount);
            response.setMessage("Added discount for bill");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.FAILED_DEPENDENCY);
        } finally {
            logger.info(Constant.END + "addDiscount");
        }
    }

    @PutMapping(Constant.MANAGER_API + "/payment/{billId}")
    public ResponseEntity payment(@RequestHeader String Authorization, @PathVariable int billId) {
        logger.info(Constant.BEGIN + "payment");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            String token = Authorization.substring(7);
            String username = JWTVerifier.getDecodedJWT(token).getSubject();
            managerService.payment(billId, username);
            response.setMessage("Checked out");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.FAILED_DEPENDENCY);
        } finally {
            logger.info(Constant.END + "payment");
        }
    }

    @GetMapping(Constant.MANAGER_API + "/getbill")
    public ResponseEntity statisticByUsername(@RequestHeader String Authorization) {
        logger.info(Constant.BEGIN + "statisticByUsername");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            String token = Authorization.substring(7);
            String username = JWTVerifier.getDecodedJWT(token).getSubject();
            List<Bill> billList = managerService.getBillByUsername(username);

            response.setData(billList);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.FAILED_DEPENDENCY);
        } finally {
            logger.info(Constant.END + "statisticByUsername");
        }
    }

}
