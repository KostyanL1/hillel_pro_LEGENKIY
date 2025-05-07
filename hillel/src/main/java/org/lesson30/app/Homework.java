package org.lesson30.app;


import jakarta.persistence.*;
import jdk.jfr.StackTrace;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "homework")
@Getter
@Setter
@ToString
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "deadline")
    private LocalDate deadline;
    @Column(name = "mark")
    private int mark;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(id, homework.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
