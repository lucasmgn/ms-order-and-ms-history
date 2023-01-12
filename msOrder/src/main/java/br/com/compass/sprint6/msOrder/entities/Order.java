package br.com.compass.sprint6.msOrder.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ORDER")
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CPF")
    private String cpf;

    @ManyToMany
    @JoinTable(name = "ORDER_ITEM",
            joinColumns = {@JoinColumn(name = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ITEM_ID")}
    )
    @Column(name = "ITEMS")
    private List<Item> items;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @OneToOne
    private Address address;
}
