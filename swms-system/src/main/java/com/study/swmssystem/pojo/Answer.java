package com.study.swmssystem.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "comment")
    String comment;

    @Column(name = "grade")
    int grade;

    @Column(name = "committime")
    Timestamp committime;

    @Column(name = "answer")
    String answer;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name = "homework_id")
    private Homework homework;

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Timestamp getCommittime() {
        return committime;
    }

    public void setCommittime(Timestamp committime) {
        this.committime = committime;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
