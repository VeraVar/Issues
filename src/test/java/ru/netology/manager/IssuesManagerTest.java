package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssuesRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.*;

public class IssuesManagerTest {
    private IssuesRepository repository = new IssuesRepository();
    private IssuesManager manager = new IssuesManager(repository);

    private HashSet<String> label1 = new HashSet<>((Arrays.asList("label1")));
    private HashSet<String> label2 = new HashSet<>((Arrays.asList("label2")));
    private HashSet<String> label3 = new HashSet<>((Arrays.asList("label3")));
    private HashSet<String> label4 = new HashSet<>((Arrays.asList("label4")));
    private HashSet<String> label10 = new HashSet<>((Arrays.asList("label10")));

    private HashSet<String> assignee1 = new HashSet<>((Arrays.asList("assignee1")));
    private HashSet<String> assignee2 = new HashSet<>((Arrays.asList("assignee2")));
    private HashSet<String> assignee3 = new HashSet<>((Arrays.asList("assignee3")));
    private HashSet<String> assignee4 = new HashSet<>((Arrays.asList("assignee4")));
    private HashSet<String> assignee10 = new HashSet<>((Arrays.asList("assignee10")));

    private Issue issue1 = new Issue(1, true, "01.05.2020", "author1", label1, assignee1, 10);
    private Issue issue2 = new Issue(2, true, "01.04.2020", "author2", label2, assignee2, 20);
    private Issue issue3 = new Issue(3, true, "01.03.2020", "author3", label3, assignee3, 30);
    private Issue issue4 = new Issue(4, false, "01.02.2020", "author4", label4, assignee4, 40);
    private Issue issue5 = new Issue(5, false, "01.01.2020", "author1", label2, assignee3, 50);

    @BeforeEach
    public void setUp() {
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
    }

    @Test
    void shouldFindAllOpen() {
        List<Issue> actual = manager.findAllOpen();
        List<Issue> expected = Arrays.asList(issue1, issue2, issue3);
        assertEquals(expected, actual);
    }
    @Test
    void shouldFindAllClosed() {
        List<Issue> actual = manager.findAllClosed();
        List<Issue> expected = Arrays.asList(issue4, issue5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthorIfExists() {
        String author = "author1";
        Predicate<Issue> predicate = issue -> issue.getAuthor().equals(author);
        List<Issue> actual = manager.filterByAuthor(predicate);
        List<Issue> expected = Arrays.asList(issue1, issue5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAuthorIfNotExists() {
        String author = "author10";
        Predicate<Issue> predicate = issue -> issue.getAuthor().equals(author);
        List<Issue> actual = manager.filterByAuthor(predicate);
        List<Issue> expected = Arrays.asList();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabelIfExists() {
        Predicate<HashSet> equalLabel = t -> t.equals(label2);
        List<Issue> actual = manager.filterByLabel(equalLabel);
        List<Issue> expected = Arrays.asList(issue2, issue5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByLabelIfNotExists() {
        Predicate<HashSet> equalLabel = t -> t.equals(label10);
        List<Issue> actual = manager.filterByLabel(equalLabel);
        List<Issue> expected = Arrays.asList();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssigneeIfExists() {
        Predicate<HashSet> equalAssignee = t -> t.equals(assignee3);
        List<Issue> actual = manager.filterByAssignee(equalAssignee);
        List<Issue> expected = Arrays.asList(issue3, issue5);
        assertEquals(expected, actual);
    }
    @Test
    void shouldNotFilterByAssigneeIfNotExists() {
        Predicate<HashSet> equalAssignee = t -> t.equals(assignee10);
        List<Issue> actual = manager.filterByAssignee(equalAssignee);
        List<Issue> expected = Arrays.asList();
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenById() {
        manager.openById(1);
        assertTrue(issue1.isOpen());
    }

    @Test
    void shouldCloseById() {
        manager.closeById(5);
        assertFalse(issue5.isOpen());
    }
}
