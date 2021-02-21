package com.example.demoulid;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "ecriture_journal_general")
public class EcritureJournalGeneral {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer annee;

    @Column
    private LocalDateTime date;

    @Column
    private String regie;

    @Column
    private String type;

    @Column
    private String ulid;

    @Column
    private int numeroJG;

    @Override
    public String toString() {
        return "EcritureJournalGeneral{" +
                "id=" + id +
                ", annee=" + annee +
                ", regie='" + regie + '\'' +
                ", type='" + type + '\'' +
                ", numeroJG=" + numeroJG +
                '}';
    }
}
