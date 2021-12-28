package com.practice.entitypractice.data.delivery;

import com.practice.entitypractice.data.inventory.Plant;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@NamedQuery(
        name = "Delivery.findByRecipientName",
        query = "Select d from Delivery d where d.recipientName = :recipientName"
)
@Entity
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "recipient_name")
    @Nationalized
    private String recipientName;

    @Column(name = "address_full", length = 500)
    private String address;

    //    private LocalDate date;
//
//    private LocalTime time;
    private LocalDateTime deliveryTime; // includes both date and time - simpler than having two separate fields
    
    @Type(type = "yes_no")
    private Boolean completed = false;

    // added CascadeType.REMOVE to automatically clear any associated plants when removed
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "delivery", cascade = {CascadeType.ALL})
    private List<Plant> plants;

    public Delivery() {
    }

    public Delivery(String recipientName, String address, LocalDateTime deliveryTime) {
        this.recipientName = recipientName;
        this.address = address;
        this.deliveryTime = deliveryTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
