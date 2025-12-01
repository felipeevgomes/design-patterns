package patterns.decorator;

public interface TroopComponent {
  int getAttack();
  int getDefense();
  int getCost();
  String getDescription();
  void display();
}
