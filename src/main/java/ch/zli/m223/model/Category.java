package ch.zli.m223.model;

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

  public Long getCategoryId() {
      return categoryId;
  }

  public void setCategoryId(Long categoryId) {
      this.categoryId = categoryId;
  }

  public String getCategoryTitle() {
      return categoryTitle;
  }

  public void setCategoryTitle(String categoryTitle) {
      this.categoryTitle = categoryTitle;
  }
}