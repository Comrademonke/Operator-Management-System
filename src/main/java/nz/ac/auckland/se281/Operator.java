package nz.ac.auckland.se281;

public class Operator {
  private String operatorName;
  private String id;
  private Types.Location location;

  public Operator(String operatorName, String id, Types.Location location) {
    this.operatorName = operatorName;
    this.id = id;
    this.location = location;
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
