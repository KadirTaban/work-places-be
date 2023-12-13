package dev.pinecone.webapp.entity;


import dev.pinecone.webapp.entity.enums.CriteriaRate;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "criteria")
    private CriteriaRate criteria;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "consumer_id")
    private Long consumerId;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "place_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK__rate_place"))
    private Place place;

    @ManyToOne
    @JoinColumn(name = "consumer_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK__rate_consumer"))
    private Consumer consumer;
}
