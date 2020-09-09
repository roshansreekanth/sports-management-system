import java.io.Serializable;

public class Player extends Person implements Serializable
{
  private int numGoals;
  private boolean goalieStatus;
  private Team team;
  private boolean inTeam;
  
  public Player(String name, String phone, String email, int numGoals, boolean goalieStatus)
  {
    super(name, phone, email);
    this.numGoals = numGoals;
    this.goalieStatus = goalieStatus;
    this.inTeam = false;
  }

  //----------------
  //  Getters
  //----------------

  public int getNumGoals()
  {
    return this.numGoals;
  }

  public boolean getGoalieStatus()
  {
    return this.goalieStatus;
  }

  public Team getTeam()
  {
    return this.team;
  }
 //----------------
 //  Setters
 //----------------

 public void setNumGoals(int newGoals)
 {
   this.numGoals = newGoals;
 }

 public void setGoalieStatus(boolean newGoalieStatus)
 {
   this.goalieStatus = newGoalieStatus;
 }

 public void setStatus(boolean newStatus)
 {
   this.inTeam = newStatus;
 }

public void setTeam(Team teamObj)
{
  this.team = teamObj;
}
 //----------------
 //  Methods
 //----------------
 public boolean isTaken()
 {
    return this.inTeam;
 }

 public String toString()
 {
   if(this.team == null)
   {
     return super.toString()  + " Goals: " + this.numGoals + " Goalie: " + this.goalieStatus;
   }
   return super.toString() + " Team: " + this.team.getName() + " Goals: " + this.numGoals + " Goalie: " + this.goalieStatus;
 }
}