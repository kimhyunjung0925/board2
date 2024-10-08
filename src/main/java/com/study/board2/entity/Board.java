package com.study.board2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iboard;
    private Integer iuser;

    private String title;
    private String content;
    private String filename;
    private String filepath;

    private String getdate;
}
