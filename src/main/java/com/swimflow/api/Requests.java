package com.swimflow.api;
import com.swimflow.model.*;import jakarta.validation.constraints.*;import java.time.*;
public final class Requests {private Requests(){}
 public record Item(@NotBlank String name,@NotNull ItemType type,Integer distanceMeters,@NotNull ItemStatus status){}
 public record Session(@NotNull Long workoutId,@NotBlank String instructor,@NotNull @Future LocalDateTime startsAt,@Min(1) int capacity){}
 public record Enrollment(@NotBlank @Email String swimmerEmail){}
}
