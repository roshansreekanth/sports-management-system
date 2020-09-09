import java.util.Set;
import java.io.Serializable;
import java.util.HashSet;

public class League implements Serializable
{
  private String name;
  private Set<Team> teams;
  public League(String name)
  {
    this.name = name;
    this.teams = new HashSet<Team>();
  }

 //----------------
 //  Getters
 //----------------
  public String getName()
  {
    return this.name;
  }

  public void addTeam(Team teamObj)
  {
    if(teamObj.isTaken())
    {
      System.out.println(teamObj.getName() + " is already in " + teamObj.getLeague().getName());
    }
    
    else if(!teamIsUnique(teamObj))
    {
      System.out.println("Cannot add " + teamObj.getName() + " to " + this.name + ". The jersey colour is already taken.");
    }

    else
    {
      System.out.println(teamObj.getName() + " has been added to " + this.name);
      teams.add(teamObj);
      teamObj.setLeague(this);
      teamObj.setStatus(true);
    }
  }

  public void removeTeam(Team teamObj)
  {
    if(teams.remove(teamObj))
    {
      System.out.println(teamObj.getName() + " has been removed from " + this.name);
    }
    else
    {
      System.out.println("Remove operation failed");
    }
  }

  public boolean teamIsUnique(Team teamObj)
  {
    boolean isUnique = true;
    
    for(Team team : this.teams)
    {
        if(!(team.getName().equals(teamObj.getName())) && team.getColour().equals(teamObj.getColour()))
        {
          isUnique = false;
        }
    }
    return isUnique;
  }

  public String toString()
  {
    String teamList = "";
    
    for(Team team : this.teams)
    {
      teamList += team.getName() + " ";
    }
    
    return this.name + "\nTeams: " + teamList;
  }
}