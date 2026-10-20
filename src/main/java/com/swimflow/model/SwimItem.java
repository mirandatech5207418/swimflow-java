package com.swimflow.model;
import jakarta.persistence.*; import java.util.*;
@Entity public class SwimItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public String name;
 @Enumerated(EnumType.STRING) @Column(nullable=false) public ItemType type;
 @Enumerated(EnumType.STRING) @Column(nullable=false) public ItemStatus status=ItemStatus.ACTIVE;
 public Integer distanceMeters;
 @ManyToMany(fetch=FetchType.EAGER) @JoinTable(name="item_component",joinColumns=@JoinColumn(name="parent_id"),inverseJoinColumns=@JoinColumn(name="child_id")) public Set<SwimItem> components=new LinkedHashSet<>();
 protected SwimItem(){} public SwimItem(String name,ItemType type,Integer distanceMeters,ItemStatus status){this.name=name;this.type=type;this.distanceMeters=distanceMeters;this.status=status;}
}
