package hu.horvathzoltan.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class MobileDTO {


    @NotNull(message = "{hu.horvathzoltan.dto.MobileDTO.id.NotNull.message}")
    @Size(min = 36, max = 36, message = "{hu.horvathzoltan.dto.MobileDTO.id.Size.meesage}")
    private String id;

    @NotNull(message = "{hu.horvathzoltan.dto.MobileDTO.type.NotNull.message}")
    @Size(min = 3, message = "{hu.horvathzoltan.dto.MobileDTO.type.Size.messge}")
    private String type;

    @NotNull(message = "{hu.horvathzoltan.dto.MobileDTO.manufacturer.NotNull.message}")
    @Size(min = 3, message = "{hu.horvathzoltan.dto.MobileDTO.manufacturer.Size.messge }")
    private String manufacturer;

    @Min(value = 1, message = "{hu.horvathzoltan.dto.MobileDTO.price.Min.message }")
    private int price;

    @Min(value = 0, message = "hu.horvathzoltan.dto.MobileDTO.piece.Min.message}")
    private int piece;

    public MobileDTO(){
        //Default Constructor
    }
    public MobileDTO(String id, String type, String manufacturer, int price, int piece) {
        this.id = id;
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
        this.piece = piece;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }
}
