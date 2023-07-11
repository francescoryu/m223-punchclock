package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry", Entry.class);
        return query.getResultList();
    }
    
    @Transactional
    public String deleteEntry(Entry entry) {
        var query = entityManager.createQuery("FROM Entry WHERE id=" + String.valueOf(entry.getId()));
        entityManager.remove(query.getSingleResult());
        return "" + entry.getId() + " deleted!";
    }

    @Transactional
    public Entry editEntry(Long id, Entry updatedEntry, Entry entry) {
        updatedEntry = findById(id);
        updatedEntry.setCheckIn(entry.getCheckIn());
        updatedEntry.setCheckOut(entry.getCheckOut());
        entityManager.merge(updatedEntry);
        return updatedEntry;
    }
    
    @Transactional
    public Entry findById(Long id) {
        return entityManager.find(Entry.class, id);
    }    
}
