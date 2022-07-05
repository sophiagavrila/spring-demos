package com.revature.quizzard.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FlashCard {

    @Id @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false)
    private String question;

    @Column(nullable=false)
    private String answer;

    @ManyToOne
    @JoinColumn
    private AppUser creator;

    public FlashCard() {
        super();
    }

    public FlashCard(String question, String answer, AppUser creator) {
        this.question = question;
        this.answer = answer;
        this.creator = creator;
    }

    public FlashCard(int id, String question, String answer, AppUser creator) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public AppUser getCreator() {
        return creator;
    }

    public void setCreator(AppUser creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlashCard flashCard = (FlashCard) o;
        return id == flashCard.id &&
                Objects.equals(question, flashCard.question) &&
                Objects.equals(answer, flashCard.answer) &&
                Objects.equals(creator, flashCard.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer, creator);
    }

    @Override
    public String toString() {
        return "FlashCard{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", creator=" + creator +
                '}';
    }

}
