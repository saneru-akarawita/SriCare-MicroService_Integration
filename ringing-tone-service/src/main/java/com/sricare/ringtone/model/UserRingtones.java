package com.sricare.ringtone.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class UserRingtones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private long userId;

    @ManyToOne
    @JoinColumn(name = "ringingTone_id")
    private RingingTone ringingTone;
}
