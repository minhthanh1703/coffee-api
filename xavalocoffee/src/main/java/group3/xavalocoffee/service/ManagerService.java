package group3.xavalocoffee.service;

import group3.xavalocoffee.constant.Constant;
import group3.xavalocoffee.dto.BillInfoDTO;
import group3.xavalocoffee.dto.BillResponseDTO;
import group3.xavalocoffee.dto.DrinkOrderDTO;
import group3.xavalocoffee.dto.DrinkOrderRequestDTO;
import group3.xavalocoffee.entities.Bill;
import group3.xavalocoffee.entities.BillInfo;
import group3.xavalocoffee.entities.Drink;
import group3.xavalocoffee.entities.tTable;
import group3.xavalocoffee.repository.BillInfoRepository;
import group3.xavalocoffee.repository.BillRepository;
import group3.xavalocoffee.repository.DrinkRepository;
import group3.xavalocoffee.repository.TableRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerService {

    private static final Logger logger = Logger.getLogger(ManagerService.class.getName());

    @Autowired
    BillRepository billRepository;

    @Autowired
    TableRepository tableRepository;

    @Autowired
    BillInfoRepository billInfoRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    TableService tableService;

    public BillResponseDTO getInfoOfTable(int tableNumber) throws Exception{
        logger.info(Constant.BEGIN + "getInfoOfTable");
        try {
            tTable table = tableService.findByTableNumber(tableNumber);
            if(table.isStatus() == false){
                throw new Exception("This table is currently empty");
            }
            Bill bill = billRepository.findByTableNumberAndPaided(tableNumber, false);
            if(bill == null){
                throw new Exception("Bill is null");
            }
            List<BillInfo> infoList = billInfoRepository.findByBillIdAndCountGreaterThan(bill.getId(), 0);
            List<BillInfoDTO> infoDTOList = new ArrayList<>();
            float totalPrice = 0;

            for(BillInfo info : infoList){
                Drink drink = drinkRepository.findById(info.getDrinkId()).orElse(null);
                if(drink.equals(null)){
                    throw new Exception("DrinkID not found");
                }
                float subPrice = drink.getPrice() *  info.getCount();
                totalPrice = totalPrice + subPrice;
                BillInfoDTO billInfoDTO = new BillInfoDTO(info.getId() ,info.getBillId(), info.getDrinkId(), info.getCount(), subPrice, drink.getImage(), drink.getName());
                infoDTOList.add(billInfoDTO);
            }
            //tong tien da tru giam gia
            totalPrice = totalPrice *(1- (bill.getDiscount()/100));

            BillResponseDTO dto = new BillResponseDTO(bill.getId() ,tableNumber, bill.isPaided(), totalPrice, bill.getDiscount(),
                    bill.getUsernameSatff(), bill.getCreateAt(), infoDTOList);
            return dto;
        }finally {
            logger.info(Constant.END + "getInfoOfTable");
        }
    }

    public List<BillInfoDTO> getInfoOfBill(int billId) throws Exception{
        logger.info(Constant.BEGIN + "getInfoOfBill");
        try {
            List<BillInfo> infoList = billInfoRepository.findByBillIdAndCountGreaterThan(billId, 0);
            List<BillInfoDTO> infoDTOList = new ArrayList<>();
            for(BillInfo info : infoList){
                Drink drink = drinkRepository.findById(info.getDrinkId()).orElse(null);
                if(drink.equals(null)){
                    throw new Exception("DrinkID not found");
                }
                float subPrice = drink.getPrice() *  info.getCount();
                BillInfoDTO billInfoDTO = new BillInfoDTO(info.getId() ,info.getBillId(), info.getDrinkId(), info.getCount(), subPrice, drink.getName());
                infoDTOList.add(billInfoDTO);
            }
            return infoDTOList;
        }finally {
            logger.info(Constant.END + "getInfoOfBill");
        }
    }


    public void createNewBillWithTable(tTable table) throws Exception{
        logger.info(Constant.BEGIN + "createNewBillWithTable");
        try {
            logger.info(Constant.BEGIN + "update table status");
            table.setStatus(true);
            tableRepository.save(table);
            logger.info(Constant.END + "update table status");
            logger.info(Constant.BEGIN + "insert new recode to bill");
            Bill bill = new Bill(table.getTableNumber(), false, 0, 0, null, null);
            billRepository.save(bill);
            logger.info(Constant.END + "insert new recode to bill");
        }finally {
            logger.info(Constant.END + "createNewBillWithTable");
        }
    }

    public void addDrinkForTable(DrinkOrderRequestDTO requestDTO) throws Exception{
        logger.info(Constant.BEGIN + "createDrinkForTable");
        try {
            int tableNumber = requestDTO.getTableNumber();
            List<DrinkOrderDTO> listDrinkOrder = requestDTO.getListDrink();
            //check table co dang trong hay khong
            tTable table = tableService.findByTableNumber(tableNumber);
            if(table == null){
                throw new Exception("Table is not available");
            }
            if(table.isStatus() == false){
                createNewBillWithTable(table);
            }
            logger.info(Constant.BEGIN + "get billId");
            Bill bill = billRepository.findByTableNumberAndPaided(tableNumber, false);
            if(bill == null){
                throw new Exception("bill is null");
            }
            int billId = bill.getId();
            logger.info(Constant.END + "get billId");

            //infoList la danh sach da co cua ban do
            List<BillInfo> infoList = billInfoRepository.findByBillId(billId);
            int exist = 0;

            logger.info(Constant.BEGIN + "insert drink and quantity to bill_info");
            if(infoList.size() == 0){
                for(DrinkOrderDTO dto : listDrinkOrder){
                    BillInfo billInfo = new BillInfo(billId, dto.getDrinkId(), dto.getCount());
                    billInfoRepository.save(billInfo);
                }
            }else{
                for(DrinkOrderDTO dto : listDrinkOrder){
                    exist = 0;
                    for(BillInfo info : infoList){
                        if(dto.getDrinkId() ==  info.getDrinkId()){
                            info.setCount(info.getCount() + dto.getCount());
                            billInfoRepository.save(info);
                            exist++;
                            break;
                        }
                    }
                    if (exist==0){
                        BillInfo billInfo = new BillInfo(billId, dto.getDrinkId(), dto.getCount());
                        billInfoRepository.save(billInfo);
                    }
                }
            }

            logger.info(Constant.END + "insert drink and quantity to bill_info");
        }finally {
            logger.info(Constant.END + "createDrinkForTable");
        }
    }

    public void addCountDrink(int billInfoId)throws Exception{
        logger.info(Constant.BEGIN + "addCountDrink");
        try {
            BillInfo billInfo = billInfoRepository.findById(billInfoId).orElse(null);
            if(billInfo == null){
                throw new Exception("bill info is null");
            }
            billInfo.setCount(billInfo.getCount() + 1);
            billInfoRepository.save(billInfo);
        }finally {
            logger.info(Constant.END + "addCountDrink");
        }
    }

    public void subCountDrink(int billInfoId)throws Exception{
        logger.info(Constant.BEGIN + "subCountDrink");
        try {
            BillInfo billInfo = billInfoRepository.findById(billInfoId).orElse(null);
            if(billInfo == null){
                throw new Exception("bill info is null");
            }
            if(billInfo.getCount() == 0){
                throw new Exception("Quantity must be greater than zero");
            }
            billInfo.setCount(billInfo.getCount() - 1);
            billInfoRepository.save(billInfo);
        }finally {
            logger.info(Constant.END + "subCountDrink");
        }
    }

    public void switchTables(int tableNumberFrom, int tableNumberTo) throws Exception{
        logger.info(Constant.BEGIN + "switchTables");
        try {
            tTable tableFrom = tableService.findByTableNumber(tableNumberFrom);
            tTable tableTo = tableService.findByTableNumber(tableNumberTo);
            if(tableFrom == null || tableFrom.isStatus() == false){
                throw new Exception("TableFrom status must be true");
            }
            if(tableTo == null || tableTo.isStatus() == true){
                throw new Exception("TableTo status must be false");
            }
            Bill bill = billRepository.findByTableNumberAndPaided(tableNumberFrom, false);
            if(bill == null){
                throw new Exception("Bill is null");
            }
            tableFrom.setStatus(false);
            tableRepository.save(tableFrom);
            tableTo.setStatus(true);
            tableRepository.save(tableTo);
            bill.setTableNumber(tableNumberTo);
            billRepository.save(bill);
        }finally {
            logger.info(Constant.END + "switchTables");
        }
    }

    public void removeDrink(int billInfoId) throws Exception{
        logger.info(Constant.BEGIN + "removeDrink");
        try {
            BillInfo billInfo = billInfoRepository.findById(billInfoId).orElse(null);
            if(billInfo == null){
                throw new Exception("Bill Info is null");
            }
            billInfo.setCount(0);
            billInfoRepository.save(billInfo);
        }finally {
            logger.info(Constant.END + "removeDrink");
        }
    }

    public void addDiscount(int billId, float discount) throws Exception{
        logger.info(Constant.BEGIN + "addDiscount");
        try {
            Bill bill = billRepository.findById(billId).orElse(null);
            if(bill==null){
                throw new Exception("Bill is null");
            }
            bill.setDiscount(discount);
            billRepository.save(bill);
        }finally {
            logger.info(Constant.END + "addDiscount");
        }
    }

    public void payment(int billId, String username) throws Exception{
        logger.info(Constant.BEGIN + "payment");
        try {
            Bill bill = billRepository.findById(billId).orElse(null);
            if(bill == null){
                throw new Exception("Bill is null");
            }
            if(bill.isPaided() == true){
                throw new Exception("Bill paid");
            }
            bill.setUsernameSatff(username);
            Date currentDate = new Date();
            bill.setCreateAt(currentDate);

            float totalPrice = 0;
            List<BillInfo> infoList = billInfoRepository.findByBillIdAndCountGreaterThan(bill.getId(), 0);
            for (BillInfo info: infoList){
                Drink drink = drinkRepository.findById(info.getDrinkId()).orElse(null);
                if(drink == null){
                    throw new Exception("Not found drink by id = " +drink.getId());
                }
                totalPrice = totalPrice + (info.getCount()*drink.getPrice());
            }
            totalPrice = totalPrice*(1 - bill.getDiscount()/100);
            bill.setTotalPrice(totalPrice);
            bill.setPaided(true);

            tTable table = tableRepository.findByTableNumber(bill.getTableNumber());
            table.setStatus(false);
            billRepository.save(bill);
            tableRepository.save(table);
        }finally {
            logger.info(Constant.END + "payment");
        }
    }

    public List<Bill> getBillByUsername(String username){
        logger.info(Constant.BEGIN + "getBillByUsername");
        try {
            List<Bill> listBill = billRepository.findByUsernameSatff(username);
            return listBill;
        }finally {
            logger.info(Constant.END + "getBillByUsername");
        }
    }
}
