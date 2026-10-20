package com.swimflow.model;
import jakarta.persistence.*;
@Entity @Table(uniqueConstraints=@UniqueConstraint(columnNames={"session_id","swimmer_email"})) public class Enrollment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @ManyToOne(optional=false) @JoinColumn(name="session_id") public SwimSession session;
 @Column(name="swimmer_email",nullable=false) public String swimmerEmail;
 @Enumerated(EnumType.STRING) @Column(nullable=false) public EnrollmentStatus status=EnrollmentStatus.CONFIRMADA;
 protected Enrollment(){} public Enrollment(SwimSession session,String email){this.session=session;this.swimmerEmail=email;}
}
