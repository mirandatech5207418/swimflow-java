package com.swimflow.model;
import jakarta.persistence.*; import java.time.*;
@Entity public class SwimSession {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @ManyToOne(optional=false) public SwimItem workout;
 @Column(nullable=false) public String instructor;
 @Column(nullable=false) public LocalDateTime startsAt;
 @Column(nullable=false) public int capacity;
 @Enumerated(EnumType.STRING) @Column(nullable=false) public SessionStatus status=SessionStatus.ABERTA;
 @Version public long version;
 protected SwimSession(){} public SwimSession(SwimItem workout,String instructor,LocalDateTime startsAt,int capacity){this.workout=workout;this.instructor=instructor;this.startsAt=startsAt;this.capacity=capacity;}
}
