package patterns.factory;

public interface  Troop {
  int getAttack();
  int getDefense();
  int getGoldCost();
  int getWoodCost();
  String getDescription();
  void display();
}
