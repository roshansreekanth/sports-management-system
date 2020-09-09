import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

  // ----------------
  // Validations
  // ----------------

  private static int getPositiveInt(String question, int min, int max) {
    Scanner kb = new Scanner(System.in);
    int input;

    while (true) {
      System.out.println(question);

      if (kb.hasNextInt()) {
        input = kb.nextInt();
        if (min <= input && input <= max) {
          kb.nextLine();
          return input;
        } else {
          System.out.println("Please enter a number in range " + min + " to " + max);
        }
      } else {
        kb.nextLine();
        System.out.println("Please enter a valid number");
      }
    }
  }

  private static boolean getBoolean(String question) {
    Scanner kb = new Scanner(System.in);

    while (true) {
      System.out.println(question);

      if (!kb.hasNextBoolean()) {
        kb.nextLine();
        System.out.println("Please enter a boolean value");
      } else {
        return kb.nextBoolean();
      }
    }
  }

  private static float getPositiveFloat(String question, float min, float max) {
    Scanner kb = new Scanner(System.in);
    float input;

    while (true) {
      System.out.println(question);

      if (kb.hasNextInt()) {
        input = kb.nextInt();
        if (min <= input && input <= max) {
          kb.nextLine();
          return input;
        } else {
          System.out.println("Please enter a number in range " + min + " to " + max);
        }
      } else {
        kb.nextLine();
        System.out.println("Please enter a valid number");
      }
    }
  }

  // ----------------
  // Menu Functions
  // ----------------

  public static void pause() {
    Scanner press = new Scanner(System.in);
    System.out.println("Press Enter to continue...");
    press.nextLine();
  }
  

  public static void showMenu() {
    System.out.println("1. Create a team");
    System.out.println("2. Create a Player");
    System.out.println("3. Create a Manager");
    System.out.println("4. Add player to team");
    System.out.println("5. Add Manager to a team");
    System.out.println("6. Remove a Player.");
    System.out.println("7. Search for a Player by supplying the Player name. Display goals and Manager details.");
    System.out.println("8. Display all players in a particular team.");
    System.out.println("9. Display all teams in a League");
    System.out.println("10. Save all information to a text file.");
    System.out.println("11. Load information from a text file.");
    System.out.println("12. Quit");
  }

  public static void createTeam(ArrayList<Team> teams, ArrayList<League> leagues) {
    Scanner teamDetails = new Scanner(System.in);

    System.out.println("Enter the name of the team: ");
    String teamName = teamDetails.nextLine();

    System.out.println("Enter the team colour: ");
    String teamColour = teamDetails.nextLine();

    Team teamObject = new Team(teamName, teamColour);
    boolean isUnique = true;

    for (Team team : teams) {
      if (team.getName().equals(teamObject.getName()) || team.getColour().equals(teamObject.getColour())) {
        isUnique = false;
      }
    }

    if (!isUnique) {
      System.out.println("Name or colour is not unique. Try again!");
      createTeam(teams, leagues);
      return;
    } else {
      teams.add(teams.size(), teamObject);
      System.out.println(teamName + " has been added");
    }

    System.out.println("Would you like to add the team to a league (y/n) ? ");
    String teamChoice = teamDetails.nextLine();

    if (teamChoice.equals("n")) {
      return;
    } else {
      display(leagues);
      System.out.println("Enter the name of the league:");
      String leagueName = teamDetails.nextLine();
      for (League league : leagues) {
        if (league.getName().equals(leagueName)) {
          league.addTeam(teamObject);
          System.out.println(teamObject.getName() + " has been added to " + league.getName());
          return;
        }
      }
      System.out.println("Could not find league");

    }
  }

  public static void createPlayer(ArrayList<Person> people, ArrayList<Player> players) {
    Scanner playerDetails = new Scanner(System.in);

    System.out.println("Enter the name of the player: (eg. firstName lastName): ");
    String playerName = playerDetails.nextLine();
    while (playerName.split(" ").length < 2) {
      System.out.println("Invalid name. Enter another one: ");
      playerName = playerDetails.nextLine();
    }

    System.out.println("Enter the phone number of " + playerName + " :");
    String phoneNumber = playerDetails.nextLine();

    System.out.println("Enter the email: ");
    String email = playerDetails.nextLine();

    int goals = getPositiveInt("Enter the number of goals scored: ", 0, Integer.MAX_VALUE);

    boolean goalieStatus = getBoolean("Is " + playerName + " a goalie? (true/false):");
    Player playerObject = new Player(playerName, phoneNumber, email, goals, goalieStatus);

    boolean isUnique = true;

    for (Person person : people) {
      if (person.compare(playerObject)) {
        isUnique = false;
      }
    }

    if (!isUnique) {
      System.out.println("Email/Password should be unique.");
    } else {
      System.out.println(playerName + " has been added");
      players.add(players.size(), playerObject);
      people.add(people.size(), playerObject);
    }

  }

  public static void removePlayer(ArrayList<Player> players, ArrayList<Person> people) {

    if (players.size() == 0) {
      System.out.println("No players found to remove");
      return;
    }

    display(players);
    int choice = getPositiveInt("Which player do you want to remove ?", 1, players.size());
    Player playerObject = players.get(choice - 1);
    Team playerTeam = playerObject.getTeam();

    if (playerTeam != null) {
      playerTeam.removePlayer(playerObject);
    }

    players.remove(playerObject);
    people.remove(playerObject);
    System.out.println(playerObject.getName() + " has been removed");

  }

  public static void createManager(ArrayList<Person> people, ArrayList<Manager> managers) {
    Scanner managerDetails = new Scanner(System.in);

    System.out.println("Enter the name of the manager: ");
    String name = managerDetails.nextLine();

    while (name.split(" ").length < 2) {
      System.out.println("Invalid name. Enter another one: ");
      name = managerDetails.nextLine();
    }

    System.out.println("Enter the phone number for the manager: ");
    String phone = managerDetails.nextLine();

    System.out.println("Enter the email for the manager: ");
    String email = managerDetails.nextLine();

    System.out.println("Enter the birth date of the manager: (YYYY-MM-DD)");
    LocalDate birthDate = LocalDate.parse(managerDetails.nextLine());

    float rating = getPositiveFloat("Enter the rating of the manager:", 0, 10);

    Manager managerObject = new Manager(name, phone, email, birthDate, rating);

    boolean isUnique = true;

    for (Person person : people) {
      if (managerObject.compare(person)) {
        isUnique = false;
      }
    }

    if (!isUnique) {
      System.out.println("Email/Phone should be unique");
    } else {
      managers.add(managers.size(), managerObject);
      people.add(people.size(), managerObject);
      System.out.println(name + " has been added as manager");
    }
  }

  public static void addPlayerToTeam(ArrayList<Player> players, ArrayList<Team> teams) {
    if (players.size() == 0) {
      System.out.println("No players found");
      return;
    }

    display(players);

    int choice = getPositiveInt("Which player do you want to add to a team ? ", 1, players.size());

    Player playerObject = players.get(choice - 1);

    display(teams);
    System.out.println("Enter the name of a team: ");
    Scanner getChoice = new Scanner(System.in);
    String teamName = getChoice.nextLine();

    for (Team team : teams) {
      if (team.getName().equals(teamName)) {
        team.addPlayer(playerObject);
        return;
      }
    }
    System.out.println(teamName + " not found");
  }

  public static void addManagertoTeam(ArrayList<Manager> managers, ArrayList<Team> teams) {
    if (managers.size() == 0) {
      System.out.println("No managers found");
      return;
    }

    display(managers);
    System.out.println("Choose a manager: ");
    int choice = getPositiveInt("Which Manager do you want to add to a team ?", 1, managers.size());
    Manager pickedManager = managers.get(choice - 1);

    System.out.println("Enter the name of a team: ");
    Scanner select = new Scanner(System.in);
    String teamName = select.nextLine();

    for (Team team : teams) {
      if (team.getName().equals(teamName)) {
        team.addManager(pickedManager);
        return;
      }
    }

    System.out.println("Team not found!");
  }

  public static ArrayList<Player> searchPlayer(ArrayList<Player> players) {
    Scanner select = new Scanner(System.in);
    ArrayList<Player> selectedPlayers = new ArrayList<Player>();

    System.out.println("Enter the name of a player: ");
    String playerName = select.nextLine();

    for (int i = 0; i < players.size(); i++) {
      Player playerObject = players.get(i);

      if (playerName.equals(playerObject.getName().toString())) {
        selectedPlayers.add(selectedPlayers.size(), playerObject);
      }
    }
    return selectedPlayers;
  }

  public static <T> void display(ArrayList<T> myList) {
    int counter = 0;
    for (T myObject : myList) {
      System.out.println((++counter) + ". " + myObject);
    }
  }

  public static void displayTeamPlayers(ArrayList<Player> players, ArrayList<Team> teams) {

    display(teams);

    Scanner pickTeam = new Scanner(System.in);
    System.out.println("Enter the name of the team: ");
    String teamName = pickTeam.nextLine();

    System.out.println("Players: ");

    for (Player player : players) {
      if (player.getTeam() != null && player.getTeam().getName().equals(teamName)) {
        System.out.println(player);
      }
    }

  }

  public static void displayLeagueTeams(ArrayList<Team> teams, ArrayList<League> leagues) {
    display(leagues);

    Scanner pickTeam = new Scanner(System.in);
    System.out.println("Enter the name of the League: ");
    String leagueName = pickTeam.nextLine();

    System.out.println("Teams: ");

    for (Team team : teams) {
      if (team.getLeague() != null && team.getLeague().getName().equals(leagueName)) {
        System.out.println(team);
      }
    }

  }

  public static void writeToFile(ArrayList<Player> players, ArrayList<Team> teams, ArrayList<Manager> managers,
      ArrayList<League> leagues, ArrayList<Person> people) {
    try {
      FileOutputStream file = new FileOutputStream("test.txt");
      ObjectOutputStream out = new ObjectOutputStream(file);

      out.writeObject(players);
      out.writeObject(teams);
      out.writeObject(managers);
      out.writeObject(leagues);
      out.writeObject(people);

      out.close();
      file.close();

      System.out.println("Objects have been serialized");
    }

    catch (IOException e) {
      System.out.println("Error: " + e);
    }

  }

  public static void loadFromFile(ArrayList<Player> players, ArrayList<Team> teams, ArrayList<Manager> managers,
      ArrayList<League> leagues, ArrayList<Person> people) {
    try {
      FileInputStream file = new FileInputStream("test.txt");
      ObjectInputStream in = new ObjectInputStream(file);

      ArrayList<Player> serialPlayers = new ArrayList<Player>();
      ArrayList<Team> serialTeams = new ArrayList<Team>();
      ArrayList<League> serialLeagues = new ArrayList<League>();
      ArrayList<Manager> serialManagers = new ArrayList<Manager>();
      ArrayList<Person> serialPeople = new ArrayList<Person>();

      serialPlayers = (ArrayList) in.readObject();
      serialTeams = (ArrayList) in.readObject();
      serialManagers = (ArrayList) in.readObject();
      serialLeagues = (ArrayList) in.readObject();
      serialPeople = (ArrayList) in.readObject();

      players.clear();
      teams.clear();
      managers.clear();
      people.clear();

      /*This is being done because in.readObject() creates a shallow copy of the object*/

      for(Player p : serialPlayers)
      {
        players.add(players.size(), p);
      }

      for(Team t : serialTeams)
      {
        teams.add(teams.size(), t);
      }

      for(Manager m : serialManagers)
      {
        managers.add(managers.size(), m);
      }

      for(League l : serialLeagues)
      {
        leagues.add(leagues.size(), l);
      }

      for(Person p : serialPeople)
      {
        people.add(people.size(), p);
      }

      System.out.println("Objects have been loaded");
    }

    catch (IOException e) {
      System.out.println("Error: " + e);
    }

    catch (ClassNotFoundException e) {
      System.out.println("Error: " + e);
    }
  }

  public static void processChoice(int choice, ArrayList<Player> players, ArrayList<Team> teams,
      ArrayList<League> leagues, ArrayList<Manager> managers, ArrayList<Person> people) {
    switch (choice) {
    case 1:
      createTeam(teams, leagues);
      break;
    case 2:
      createPlayer(people, players);
      break;
    case 3:
      createManager(people, managers);
      break;
    case 4:
      addPlayerToTeam(players, teams);
      break;
    case 5:
      addManagertoTeam(managers, teams);
      break;
    case 6:
      removePlayer(players, people);
      break;
    case 7:
      ArrayList<Player> searchedPlayers = searchPlayer(players);
      display(searchedPlayers);
      break;
    case 8:
      displayTeamPlayers(players, teams);
      break;
    case 9:
      displayLeagueTeams(teams, leagues);
      break;
    case 10:
      writeToFile(players, teams, managers, leagues, people);
      break;
    case 11:
      loadFromFile(players, teams, managers, leagues, people);
      break;
    }
  }

  // ----------------
  // Main Method
  // ----------------

  public static void main(String[] args) {
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Team> teams = new ArrayList<Team>();
    ArrayList<League> leagues = new ArrayList<League>();
    ArrayList<Manager> managers = new ArrayList<Manager>();
    ArrayList<Person> people = new ArrayList<Person>();
    
    showMenu();

    int choice = getPositiveInt("Pick an option: ", 1, 12);
    while (choice < 12) {
      processChoice(choice, players, teams, leagues, managers, people);
      pause();
      showMenu();
      choice = getPositiveInt("Pick an option: ", 1, 12);
    }
    System.out.println("Thank you for using the program");
  }

}