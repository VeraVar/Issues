package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Issue {
    private int id;
    private boolean open;
    private String date;
    private String author;
    private HashSet<String> label;
    private HashSet<String> assignee;
    private int countComments;
}