package dev.pinecone.webapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "consumer_id")
    private Long consumerId;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "consumer_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK__place_consumer"))
    private Consumer consumer;
}
