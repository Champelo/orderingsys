package com.bakery.orderingsys.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name="Item")
@Table(name = "item",
        uniqueConstraints = {
        @UniqueConstraint(name = "item_name_unique",
                columnNames = "item_name")
        }
)
public class Item {
    @Id
    @SequenceGenerator(name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,
            generator = "item_sequence")
    @Column(name = "item_id", updatable = false)
    private Long itemId;

    @Column(name = "item_name",
            nullable = false,
            columnDefinition = "TEXT"
           )
    private String itemName;

    @Column(name = "item_price", nullable = false)
    private double itemPrice;

    @Column(name = "item_ingredients",
            nullable = false, columnDefinition = "TEXT")
    private String ingredients;

    @Column(name = "sold_out", nullable = false)
    private boolean soldOut;

    @Column(name = "item_quantity", nullable = false)
    private int quantity;


    public Item() {
    }

    public Item(String itemName, double itemPrice,
                String ingredients, boolean soldOut,
                int quantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.ingredients = ingredients;
        this.soldOut = soldOut;
        this.quantity = quantity;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemId='" + itemId + '\'' +
                ", itemName=" + itemName +
                ", itemPrice=" + itemPrice +
                ", ingredients='" + ingredients + '\'' +
                ", soldOut=" + soldOut +
                ", quantity=" + quantity +
                '}';
    }
}
