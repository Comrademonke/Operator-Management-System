package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;

public class Operator {
  private String operatorName;
  private String id;
  private Types.Location location;
  private ArrayList<ActivityType> activityList = new ArrayList<>();

  public Operator(String operatorName, String id, Types.Location location) {
    this.operatorName = operatorName;
    this.id = id;
    this.location = location;
  }

  public void addActivity(ActivityType activityName) {
    activityList.add(activityName);
  }

  public boolean hasActivity() {
    return !activityList.isEmpty();
  }

  public String getName() {
    return operatorName;
  }

  public String getId() {
    return id;
  }

  public Types.Location getLocation() {
    return location;
  }

  @Override
  public String toString() {
    return MessageCli.OPERATOR_ENTRY.getMessage(operatorName, id, location.getFullName());
  }
}
