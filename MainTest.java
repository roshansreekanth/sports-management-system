import java.time.LocalDate;

public class MainTest {
  public static void main(String[] args) {
    System.out.println("Program Start");

    League leagueOne = new League("Irish Champions League");
    League leagueTwo = new League("English Champions League");

    Player playerOne = new Player("Player One", "1111111111", "playerOne@gmail.com", 3, false);
    Player playerTwo = new Player("Player Two", "222222222", "playerTwo@gmail.com", 1, true);

    Manager managerOne = new Manager("Manager One", "1010101010", "managerOne@gmail.com", LocalDate.parse("1989-10-12"),
        7f);
    Manager managerTwo = new Manager("Manager Two", "2020202020", "managerTwo", LocalDate.parse("1990-05-06"), 8.2f);

    Team teamOne = new Team("Red Sox", "red");
    Team teamTwo = new Team("Yellow Hornets", "yellow");
    Team teamThree = new Team("Red Giants", "red");

    System.out.println("Scenario 1: Adding one player to two different teams:");
    teamOne.addPlayer(playerOne);
    teamTwo.addPlayer(playerTwo);
    teamTwo.addPlayer(playerOne);

    System.out.println();

    System.out.println("Scenario 2: Adding one Manager to two different teams:");
    teamOne.addManager(managerOne);
    teamTwo.addManager(managerOne);
    System.out.println();

    System.out.println("Scenario 3: Removing a manager from a team with no manager:");
    System.out.println();
    teamThree.removeManager();

    System.out.println();
    teamTwo.addManager(managerTwo);
    System.out.println();

    System.out.println("Scenario 4: Adding two teams with the same jersey color to the same league");
    leagueOne.addTeam(teamOne);
    leagueOne.addTeam(teamThree);

    System.out.println();

    System.out.println("Scenario 5: Adding a team to a leage when it's already in another one:");
    leagueTwo.addTeam(teamOne);

    System.out.println("Changing goals for player one to 5.");

    playerOne.setNumGoals(5);

    System.out.println();

    System.out.println("Printing details: ");
    System.out.println(playerOne);
    System.out.println();
    System.out.println(teamOne);
    System.out.println();
    System.out.println(managerOne);
    System.out.println();
    System.out.println(leagueOne);
  }
}