package com.rikdev.crud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data       //INCLUY GETERS Y SETERS PARA ESTA ENTIDAD
@NoArgsConstructor
@Entity
public class Product {

    @Id            //indica que es clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrementable
    private Long id_product;

    @NotBlank //no debe ser vacio
    private String name;

    @NotBlank
    private String descripcion;

    @NotNull
    private Double precio;

    @NotNull
    private Double peso;

/*    @NotNull
    private String image;*/

    @NotNull
    private Double descuento;

    @NotNull
    private Integer stock;

    @NotNull
    private String sku;

/*    @NotNull
    private String created_at;*/
}
