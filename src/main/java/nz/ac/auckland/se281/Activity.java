package nz.ac.auckland.se281;

public class Activity {
  private String activityName;
  private Types.ActivityType activityType;
  private String operatorId;
  private String activityNumber;
  private String activityId;

  public Activity(
      String activityName,
      Types.ActivityType activityType,
      String operatorId,
      String activityNumber) {
    this.activityName = activityName;
    this.activityType = activityType;
    this.operatorId = operatorId;
    this.activityNumber = activityNumber;
    this.activityId = operatorId + activityNumber;
  }

  public String getActivityName() {
    return activityName;
  }

  public Types.ActivityType getActivityType() {
    return activityType;
  }

  public String getOperatorId() {
    return operatorId;
  }

  public String getActivityNumber() {
    return activityNumber;
  }

  public String getActivityId() {
    return activityId;
  }

  @Override
  public String toString() {
    return MessageCli.ACTIVITY_ENTRY.getMessage(activityName, activityId, activityType + "", "");
  }
}
