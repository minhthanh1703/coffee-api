package group3.xavalocoffee.controller;


import group3.xavalocoffee.constant.Constant;
import group3.xavalocoffee.dto.ServiceResponseDTO;
import group3.xavalocoffee.entities.tTable;
import group3.xavalocoffee.service.TableService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping(Constant.API)
public class TableController {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(AccountController.class.getName());

    @Autowired
    private TableService tableService;

    @GetMapping(Constant.TABLE_API)
    public ResponseEntity<ServiceResponseDTO> findAllTable(){
        logger.info(Constant.BEGIN + "findAllTable");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            List<tTable> listTable = tableService.findAllTable();
            response.setData(listTable);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END + "findAllTable");
        }
    }

//    @GetMapping(Constant.TABLE_API + "/{tableNumber}")
//    public ResponseEntity<ServiceResponseDTO> searchTableLike(@PathVariable int tableNumber){
//        logger.info(Constant.BEGIN + "searchTableLike");
//        ServiceResponseDTO response = new ServiceResponseDTO();
//        try{
//            List<tTable> listTable = tableService.searchTableLike(tableNumber);
//            response.setData(listTable);
//            return new ResponseEntity(response, HttpStatus.OK);
//        }catch (Exception ex){
//            logger.error(ex);
//            response.setMessage(ex.getMessage());
//            response.setStatus(ServiceResponseDTO.Status.FAILED);
//            return new ResponseEntity(response,HttpStatus.FAILED_DEPENDENCY);
//        }finally {
//            logger.info(Constant.END + "searchTableLike");
//        }
//    }

    @GetMapping(Constant.TABLE_API + "/getTableEmpty")
    public ResponseEntity<ServiceResponseDTO> searchTableEmpty(){
        logger.info(Constant.BEGIN + "searchTableEmpty");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            List<tTable> listTable = tableService.searchTableStatus(false);
            response.setData(listTable);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END + "searchTableEmpty");
        }
    }

    @PostMapping(Constant.TABLE_API)
    public ResponseEntity<ServiceResponseDTO> createTable(@RequestBody tTable table){
        logger.info(Constant.BEGIN + "createTable");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            tableService.createTable(table);
            response.setMessage("Created");
            return  new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END + "createTable");
        }
    }
}
