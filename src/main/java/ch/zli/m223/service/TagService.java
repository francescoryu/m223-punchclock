package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Tag;

@ApplicationScoped
public class TagService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Tag createTag(Tag tag) {
        entityManager.persist(tag);
        return tag;
    }

    public List<Tag> findAll() {
        var query = entityManager.createQuery("FROM Tag", Tag.class);
        return query.getResultList();
    }
    
    @Transactional
    public String deleteTag(Tag tag) {
        var query = entityManager.createQuery("FROM Tag WHERE id=" + String.valueOf(tag.getId()));
        entityManager.remove(query.getSingleResult());
        return "" + tag.getId() + " deleted!";
    }

    @Transactional
    public Tag editTag(Long id, Tag updatedTag, Tag tag) {
        updatedTag = findById(id);
        updatedTag.setTagTitle(tag.getTagTitle());
        entityManager.merge(updatedTag);
        return updatedTag;
    }
    
    @Transactional
    public Tag findById(Long id) {
        return entityManager.find(Tag.class, id);
    }    
}
