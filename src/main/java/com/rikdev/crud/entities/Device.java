package com.rikdev.crud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity

public class Device {

    @Id            //indica que es clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrementable
    private Long id_device;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String price;

    @NotNull
    private String weight;

    @NotNull
    private String image;

    @NotNull
    private Double discount;

    @NotNull
    private Integer stock;

    @NotNull
    private String sku;

    @NotNull
    private String created_at;
}
