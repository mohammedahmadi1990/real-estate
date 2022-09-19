package com.realestate.realestatemanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "note")
@NoArgsConstructor
@Getter
@Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="note_text", nullable = false)
    private String note;

    @ManyToOne
    @JoinColumn(name="user", nullable = false)
    private User user;

    public Note(String note, User user) {
        this.note = note;
        this.user = user;
    }
}




