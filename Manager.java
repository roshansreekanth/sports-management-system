import java.io.Serializable;
import java.time.LocalDate;

public class Manager extends Person implements Serializable {
  private LocalDate birthDate;
  private float rating;
  private boolean status;
  private Team team;

  public Manager(String name, String phone, String email, LocalDate birthDate, float rating) {
    super(name, phone, email);
    this.birthDate = birthDate;
    this.rating = rating;
    this.status = false;
    this.team = null;
  }

  // ----------------
  // Getters
  // ----------------

  public float getRating() {
    return this.rating;
  }

  public boolean isTaken() {
    return this.status;
  }

  public Team getTeam() {
    return this.team;
  }

  public LocalDate getBirthDate() {
    return this.birthDate;
  }
  // ----------------
  // Setters
  // ----------------

  public void setRating(float newRating) {
    this.rating = newRating;
  }

  public void setStatus(boolean newStatus) {
    this.status = newStatus;
  }

  public void setTeam(Team teamObject) {
    this.team = teamObject;
  }

  public String toString() {
    if (this.team != null) {
      return super.toString() + " Team: " + this.team.getName() + " Rating: " + this.rating;
    } else {
      return super.toString() + " Rating: " + this.rating;
    }
  }
}