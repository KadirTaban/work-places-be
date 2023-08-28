package dev.pinecone.webapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "location_url", nullable = false, unique = true)
    private String locationUrl;
    @Column(name = "has_ac")
    private boolean hasAC;
    @Column(name = "has_ethernet")
    private boolean hasEthernet;
    @Column(name = "has_electricity")
    private boolean hasElectricity;
    @Column(name = "district")
    private String district;
    @Column(name = "city")
    private String city;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
