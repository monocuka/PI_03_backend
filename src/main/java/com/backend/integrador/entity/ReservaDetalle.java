// package com.backend.integrador.entity;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Table( name = "reserva_detalle")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class ReservaDetalle {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column( name = "resd_id" )
//     private Long id;
//     @Column( name = "resd_codigo" )
//     private String codigo;
//     @Column( name = "resd_precio_unitario" )
//     private double resd_precio_unitario;
//     @Column( name = "resd_cantidad" )
//     private int resd_cantidad;
// // 
//     @ManyToOne
//     @JoinColumn(name = "resd_idproducto", nullable = false)
//     private Producto producto;

//     @ManyToOne
//     @JoinColumn(name = "resd_idreserva", nullable = false)
//     private Reserva reserva;
// }
