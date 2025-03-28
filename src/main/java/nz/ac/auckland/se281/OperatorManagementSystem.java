package nz.ac.auckland.se281;

import java.util.ArrayList;

public class OperatorManagementSystem {

  private int operatorCount = 0;
  private ArrayList<String> operatorList = new ArrayList<>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {

    // Prints message when searching for all operators
    if (keyword.equals("*") && operatorCount == 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else if (keyword.equals("*") && operatorCount == 1) {
      MessageCli.OPERATORS_FOUND.printMessage("is", "" + operatorCount, "", ":");
      for (String operator : operatorList) {
        System.out.println(operator);
      }
    }
  }

  public void createOperator(String operatorName, String location) {

    // Converts location to the Location enum constant
    Types.Location locationEnum = Types.Location.fromString(location);
    String locationFullName = locationEnum.getFullName();
    String locationAbbreviation = locationEnum.getLocationAbbreviation();

    // Stripping operator name of white space and storing each word
    String clearedWhiteSpaceOperatorName = operatorName.trim();
    String[] operatorNameParts = clearedWhiteSpaceOperatorName.split(" ");

    // Loops through each word of the operator name and takes the first initial to make operator id
    String operatorInitials = "";
    for (String operatorNamePart : operatorNameParts) {
      operatorInitials = operatorInitials + operatorNamePart.charAt(0);
    }

    // Prints operator count format based off number of existing operators
    operatorCount++;

    if (operatorCount < 10) {
      MessageCli.OPERATOR_CREATED.printMessage(
          operatorName,
          operatorInitials + "-" + locationAbbreviation + "-00" + operatorCount,
          locationFullName);
    } else if (operatorCount > 10 && operatorCount < 99) {
      MessageCli.OPERATOR_CREATED.printMessage(
          operatorName,
          operatorInitials + "-" + locationAbbreviation + "-0" + operatorCount,
          locationFullName);
    } else {
      MessageCli.OPERATOR_CREATED.printMessage(
          operatorName,
          operatorInitials + "-" + locationAbbreviation + "-" + operatorCount,
          locationFullName);
    }

    // adds operator to ArrayList for tracking
    operatorList.add(
        "* "
            + operatorName
            + " ('"
            + operatorInitials
            + "-"
            + locationAbbreviation
            + "-00"
            + operatorCount
            + "' located in '"
            + locationFullName
            + "')");
  }

  public void viewActivities(String operatorId) {
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // TODO implement
  }

  public void searchActivities(String keyword) {
    // TODO implement
  }

  public void addPublicReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addPrivateReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addExpertReview(String activityId, String[] options) {
    // TODO implement
  }

  public void displayReviews(String activityId) {
    // TODO implement
  }

  public void endorseReview(String reviewId) {
    // TODO implement
  }

  public void resolveReview(String reviewId, String response) {
    // TODO implement
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // TODO implement
  }

  public void displayTopActivities() {
    // TODO implement
  }
}
