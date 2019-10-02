package group3.xavalocoffee.service;

import group3.xavalocoffee.constant.Constant;
import group3.xavalocoffee.entities.tTable;
import group3.xavalocoffee.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class TableService {

    private static  final Logger logger = Logger.getLogger(TableService.class.getName());

    @Autowired
    TableRepository tableRepository;


    public List<tTable> findAllTable(){
        logger.info(Constant.BEGIN + "findAllTable");
        try {
            return tableRepository.findAll();
        }finally {
            logger.info(Constant.BEGIN + "findAllTable");
        }
    }

//    public List<tTable> searchTableLike(int tableNumber){
//        logger.info(Constant.BEGIN + "searchTableLike");
//        try {
//            String value = String.valueOf(tableNumber);
//            return tableRepository.findByTableNumberContaining(tableNumber);
//        }finally {
//            logger.info(Constant.BEGIN + "searchTableLike");
//        }
//    }

    public List<tTable> searchTableStatus(boolean status){
        logger.info(Constant.BEGIN + "searchTableStatus");
        try {
            return tableRepository.findByStatus(status);
        }finally {
            logger.info(Constant.BEGIN + "searchTableStatus");
        }
    }


    public tTable findByTableNumber(int tableNumber){
        logger.info(Constant.BEGIN + "findByTableNumber");
        try {
            return tableRepository.findByTableNumber(tableNumber);
        }finally {
            logger.info(Constant.BEGIN + "findByTableNumber");
        }
    }

    public void createTable(tTable table) throws Exception{
        logger.info(Constant.BEGIN + "createTable");
        try{
            if(tableRepository.findById(table.getTableNumber()).isPresent()){
                throw new Exception("Table is exist");
            }
            tableRepository.save(table);
        }finally {
            logger.info(Constant.END + "createTable");
        }
    }

}
