package com.ucb.producto.dto;

public class TarjetaDto {
    private String card_number;
   private String cvv;
   private String expiration_date;
public TarjetaDto(String card_number, String cvv,String expiration_date) {
    this.card_number = card_number;
    this.cvv = cvv;
    this.expiration_date = expiration_date;
}
public String getCardNumber() {
    return card_number;
}
public String getCvv() {
    return cvv;
}
public String getExpirationDate() {
    return expiration_date;
}
}
