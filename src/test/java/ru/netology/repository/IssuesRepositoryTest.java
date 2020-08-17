package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IssuesRepositoryTest {
    private IssuesRepository repository = new IssuesRepository();

    private HashSet<String> label1 = new HashSet<>((Arrays.asList("label1")));
    private HashSet<String> label2 = new HashSet<>((Arrays.asList("label2")));
    private HashSet<String> label3 = new HashSet<>((Arrays.asList("label3")));
    private HashSet<String> label4 = new HashSet<>((Arrays.asList("label4")));

    private HashSet<String> assignee1 = new HashSet<>((Arrays.asList("assignee1")));
    private HashSet<String> assignee2 = new HashSet<>((Arrays.asList("assignee2")));
    private HashSet<String> assignee3 = new HashSet<>((Arrays.asList("assignee3")));
    private HashSet<String> assignee4 = new HashSet<>((Arrays.asList("assignee4")));

    private Issue issue1 = new Issue(1, true, "01.05.2020", "author1", label1, assignee1, 10);
    private Issue issue2 = new Issue(2, true, "01.04.2020", "author2", label2, assignee2, 20);
    private Issue issue3 = new Issue(3, true, "01.03.2020", "author3", label3, assignee3, 30);
    private Issue issue4 = new Issue(4, false, "01.02.2020", "author4", label4, assignee4, 40);
    private Issue issue5 = new Issue(5, false, "01.01.2020", "author1", label2, assignee3, 50);

    @BeforeEach
    public void setUp() {
        repository.save(issue1);
        repository.save(issue2);
        repository.save(issue3);
        repository.save(issue4);
        repository.save(issue5);
    }

    @Test
    void shouldFindAll() {
        List<Issue> actual = repository.findAll();
        List<Issue> expected = Arrays.asList(issue1, issue2, issue3, issue4, issue5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Issue actual = repository.findById(3);
        Issue expected = issue3;
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenById() {
        repository.openById(1);
        assertTrue(issue1.isOpen());
    }

    @Test
    void shouldCloseById() {
        repository.closeById(5);
        assertFalse(issue5.isOpen());
    }
}
