import java.util.Set;
import java.io.Serializable;
import java.util.HashSet;

public class Team implements Serializable
{
  private Set<Player> players;
  private String name;
  private String colour;
  private Manager owner; 
  private League league;
  private boolean inLeague;

  public Team(String name, String colour)
  {
    this.name = name;
    this.colour = colour.toLowerCase();
    this.players = new HashSet<Player>();
    this.owner = null;
    this.league = null;
    this.inLeague = false;
  }

 //----------------
 //  Getters
 //----------------
  public String  getName()
  {
    return this.name;
  }

  public String getColour()
  {
    return this.colour;
  }

  public boolean isTaken()
  {
    return this.inLeague;
  }

  public League getLeague()
  {
    return this.league;
  }

  public Manager getManager()
  {
    return this.owner;
  }

 //----------------
 //  Setters
 //----------------

  public void setName(String newName)
  {
    this.name = newName;
  }

  public void setColour(String newColour)
  {
    this.name = newColour.toLowerCase();
  }

  public void setStatus(boolean newStatus)
  {
    this.inLeague = newStatus;
  }

  public void setLeague(League leagueObject)
  {
    this.league = leagueObject; 
  }

 //----------------
 //  Methods
 //---------------- 

  public void addManager(Manager managerObject)
  {
    if(managerObject.isTaken())
    {
      System.out.println(managerObject.getName() + " cannot be assigned to "  + this.name + ", and already manages " + managerObject.getTeam().getName());
    }
    else
    {
      managerObject.setStatus(true);
      this.owner = managerObject;
      managerObject.setTeam(this);
      System.out.println(managerObject.getName() + " has been assigned to manage " + this.name);
    }
  }
  
  public void removeManager()
  {
    if (this.owner == null)
    {
      System.out.println("No owner assigned to " + this.name);
    }
    else
    {
      System.out.println(this.owner.getName() + " has been removed from " + this.name);
      this.owner.setStatus(false);
      this.owner.setTeam(null);
      this.owner = null;
    }
  }

  public void addPlayer(Player playerObject)
  {
    if(playerObject.isTaken())
    {
      System.out.println(playerObject.getName() + " is already in " + playerObject.getTeam().getName() + ", cannot be added to " + this.name);
    }
    else
    {
      playerObject.setStatus(true);
      players.add(playerObject);
      playerObject.setTeam(this);
      System.out.println(playerObject.getName() + " has been assigned to " + this.name);
    }
  }

  public void removePlayer(Player playerObj)
  {
    if(players.remove(playerObj))
    {
      System.out.println(playerObj.getName() + " has been removed from " + this.name);
    }
    else
    {
      System.out.println("Remove operation failed.");
    }
  }

  public String toString()
  {
    if(this.owner == null || this.league == null)
    {
      return " Name: " + this.name + " Colour: " + this.colour;
    }
    return " Name: " + this.name + " Colour: " + this.colour + " Manager: " + this.owner.getName() + " League: " + this.league.getName();
  }
}
