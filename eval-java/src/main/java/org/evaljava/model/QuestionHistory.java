package org.evaljava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QuestionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(columnDefinition = "TEXT")
    protected String content;

    @Column(columnDefinition = "TEXT")
    protected String response;

    @ManyToOne
    protected AppUser user;

    @ManyToOne
    protected Recipe recipe;
}
