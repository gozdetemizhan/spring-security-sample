package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ROLES")
public class Role {
  @Id
  private String name;

  public Role() {}
  public Role(String name) { this.name = name; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
}
