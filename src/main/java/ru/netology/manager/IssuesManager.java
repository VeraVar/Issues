package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssuesRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

public class IssuesManager {
    private IssuesRepository repository;

    public IssuesManager(IssuesRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public List<Issue> findAllOpen() {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.isOpen()) {
                temp.add(issue);
            }
        }
        return temp;
    }

    public List<Issue> findAllClosed() {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (!issue.isOpen()) {
                temp.add(issue);
            }
        }
        return temp;
    }

    public List<Issue> filterByAuthor(Predicate<Issue> predicate) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue))
                result.add(issue);
        }
        return result;
    }

    public List<Issue> filterByLabel(Predicate<HashSet> equalLabel) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (equalLabel.test(issue.getLabel())) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> filterByAssignee(Predicate<HashSet> equalAssignee) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (equalAssignee.test(issue.getAssignee())) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> sortByDate(List<Issue> list) {
        list.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        return list;
    }

    public void openById(int id) {
        repository.openById(id);
    }

    public void closeById(int id) {
        repository.closeById(id);
    }
}
