package ch.zli.m223.model;

import java.util.Set;

import javax.persistence.*;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long categoryId;

  @Column(nullable = false)
  private String categoryTitle;

  @OneToMany(mappedBy = "category")
  private Set<Entry> entries;

  public Long getCategoryId() {
      return categoryId;
  }

  public void setCategoryId(Long categoryId) {
      this.categoryId = categoryId;
  }

  public void setEntries(Set<Entry> entries) {
      this.entries = entries;
  }

  public String getCategoryTitle() {
      return categoryTitle;
  }

  public void setCategoryTitle(String categoryTitle) {
      this.categoryTitle = categoryTitle;
  }

  public Set<Entry> getEntries() {
      return entries;
  }

  
}