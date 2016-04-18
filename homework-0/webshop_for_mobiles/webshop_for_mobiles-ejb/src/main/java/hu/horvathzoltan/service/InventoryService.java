package hu.horvathzoltan.service;

import hu.horvathzoltan.dto.MobileDTO;
import hu.horvathzoltan.exception.InventoryException;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.Serializable;
import java.util.*;

@Singleton
@Startup
public class InventoryService implements Serializable {

    private static final int NUMBER_OF_MOBILE_TYPES = 4;
    private final Map<String, MobileDTO> mobileDTOMap = new HashMap<>();

    @PostConstruct
    private void init() {
        String[] UUIDArray = new String[NUMBER_OF_MOBILE_TYPES];
        for (int i = 0; i < NUMBER_OF_MOBILE_TYPES; i++) {
            UUIDArray[i] = UUID.randomUUID().toString();
        }
        mobileDTOMap.put(UUIDArray[0], new MobileDTO(UUIDArray[0], "smart", "Samsung", 500, 3));
        mobileDTOMap.put(UUIDArray[1], new MobileDTO(UUIDArray[1], "dumb", "Nokia", 5500, 12));
        mobileDTOMap.put(UUIDArray[2], new MobileDTO(UUIDArray[2], "smart", "Apple", 10500, 5));
        mobileDTOMap.put(UUIDArray[3], new MobileDTO(UUIDArray[3], "smart", "Lg", 1500, 0));

    }

    public MobileDTO addMobile(MobileDTO mobileDTO) {
        mobileDTOMap.put(mobileDTO.getId(), mobileDTO);
        return mobileDTOMap.get(mobileDTO.getId());
    }

    public void buyMobile(MobileDTO mobileToBuy) {
        String mobileToBuyId = mobileToBuy.getId();
        if (mobileDTOMap.containsKey(mobileToBuyId) && mobileDTOMap.get(mobileToBuyId).getPiece() >= mobileToBuy.getPiece()) {

            MobileDTO mobileOnStock = mobileDTOMap.get(mobileToBuyId);
            int difference = mobileOnStock.getPiece() - mobileToBuy.getPiece();
            mobileOnStock.setPiece(difference);


        } else {
            throw new InventoryException("There is not enough device in stock.");
        }

    }

    public List<MobileDTO> getMobilesList() {
        return new ArrayList<>(mobileDTOMap.values());
    }

}
