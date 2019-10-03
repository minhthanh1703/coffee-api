package group3.xavalocoffee.service;

import group3.xavalocoffee.constant.Constant;
import group3.xavalocoffee.dto.DrinkOrderDTO;
import group3.xavalocoffee.entities.Drink;
import group3.xavalocoffee.repository.DrinkRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService {

    private static final Logger logger = Logger.getLogger(DrinkService.class.getName());

    @Autowired
    DrinkRepository drinkRepository;

    public List<Drink> findAllDrink() {
        logger.info(Constant.BEGIN + "findAllDrink");
        try {
            return drinkRepository.findAll();
        } finally {
            logger.info(Constant.END + "findAllDrink");
        }
    }

    public List<Drink> findDrinkByCategory(int categoryId) {
        logger.info(Constant.BEGIN + "findAllDrinkByCategory");
        try {
            return drinkRepository.findByCategoryIdEquals(categoryId);
        } finally {
            logger.info(Constant.END + "findAllDrinkByCategory");
        }
    }

    public void createDrink(Drink drink) throws Exception {
        logger.info(Constant.BEGIN + "createDrink");
        try {
            // if(drinkRepository.findById(drink.getId()).isPresent()){
            // throw new Exception("Id is duplicate");
            // }
            drink.setId(0);
            saveDrink(drink);
        } finally {
            logger.info(Constant.END + "createDrink");
        }
    }

    public void updateDrink(Drink drink) throws Exception {
        logger.info(Constant.BEGIN + "updateDrink");
        try {
            if (!drinkRepository.findById(drink.getId()).isPresent()) {
                throw new Exception("Drink is not exist");
            }
            saveDrink(drink);
        } finally {
            logger.info(Constant.END + "updateDrink");
        }
    }

    private void saveDrink(Drink drink) throws Exception {
        if (drink.getName() == null || drink.getName().isEmpty()) {
            throw new Exception("Name is empty");
        } else if (drink.getPrice() < 0) {
            throw new Exception("Price not valid");
        } else {
            drinkRepository.save(drink);
        }
    }

    public void updateDisableDrink(int id, boolean disable) throws Exception {
        logger.info(Constant.BEGIN + "updateDisableDrink");
        try {
            Drink drink = drinkRepository.findById(id).orElse(null);
            if (drink == null) {
                throw new Exception("Drink not found");
            } else {
                drink.setDisable(disable);
                drinkRepository.save(drink);
            }
        } finally {
            logger.info(Constant.END + "updateDisableDrink");
        }
    }

    public Drink getById(int drinkId) throws Exception {
        logger.info(Constant.BEGIN + "getDrinkById");
        try {
            Drink drink = drinkRepository.findById(drinkId).orElse(null);
            if (drink == null) {
                throw new Exception("Drink not found");
            }

            return drink;
        } finally {
            logger.info(Constant.END + "getDrinkById");
        }
    }
}
