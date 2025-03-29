package nz.ac.auckland.se281;

import java.util.ArrayList;

public class OperatorManagementSystem {

  private int totalOperatorCount = 0;
  private ArrayList<String> operatorList = new ArrayList<>();
  private ArrayList<String> operatorLocationList = new ArrayList<>();
  private ArrayList<String> operatorNameList = new ArrayList<>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {

    // Prints message based off total operator count when searching for all operators
    if (keyword.trim().equals("*") && totalOperatorCount == 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else if (keyword.trim().equals("*") && totalOperatorCount == 1) {
      MessageCli.OPERATORS_FOUND.printMessage("is", Integer.toString(totalOperatorCount), "", ":");
      for (String operator : operatorList) {
        System.out.println(operator);
      }
    } else if (keyword.trim().equals("*") && totalOperatorCount > 1) {
      MessageCli.OPERATORS_FOUND.printMessage(
          "are", Integer.toString(totalOperatorCount), "s", ":");
      for (String operator : operatorList) {
        System.out.println(operator);
      }
    } else if (!keyword.trim().equals("*")) {

      // Searching for existing operators with keywords
      int operatorsFound = 0;
      ArrayList<String> matchingOperatorList = new ArrayList<>();
      for (String operatorName : operatorList) {
        if (operatorName.toLowerCase().contains(keyword.trim().toLowerCase())) {
          operatorsFound++;
          matchingOperatorList.add(operatorName);
        }
      }

      // Prints number of matching operators and the list of matching operators
      if (operatorsFound == 1) {
        MessageCli.OPERATORS_FOUND.printMessage("is", Integer.toString(operatorsFound), "", ":");
      } else if (operatorsFound > 1) {
        MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(operatorsFound), "s", ":");
      } else if (operatorsFound == 0) {
        MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
      }
      for (String operatorMatch : matchingOperatorList) {
        System.out.println(operatorMatch);
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

    // Checks for existing operators
    boolean existingOperator = false;
    for (int operator = 0; operator < operatorList.size(); operator++) {
      if (operatorNameList.get(operator).equalsIgnoreCase(operatorName.trim())
          && operatorLocationList.get(operator).equalsIgnoreCase(locationAbbreviation)) {
        existingOperator = true;
        break;
      }
    }

    if (existingOperator) {
      MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
          operatorName, locationFullName);
    } else {
      // Loops through each word of the operator name and takes the first initial to make operator
      // id
      String operatorInitials = "";
      for (String operatorNamePart : operatorNameParts) {
        operatorInitials = operatorInitials + operatorNamePart.toUpperCase().charAt(0);
      }

      // Loops through operatorsList and counts operators that exist in the same location
      int operatorCount = 1;
      for (String operatorLocation : operatorLocationList) {
        if (operatorLocation.contains(locationAbbreviation)) {
          operatorCount++;
        }
      }
      totalOperatorCount++;

      // Concatenates the ID part of the operator name
      String operatorCountString = Integer.toString(operatorCount);
      StringBuilder operatorCountId =
          new StringBuilder(operatorInitials + "-" + locationAbbreviation + "-");

      // Prints operator count format based off number of existing operators
      if (operatorCount < 10) {
        operatorCountId.append("00");
      } else if (operatorCount > 10 && operatorCount < 99) {
        operatorCountId.append("0");
      }
      operatorCountId.append(operatorCountString);

      MessageCli.OPERATOR_CREATED.printMessage(
          operatorName, operatorCountId.toString(), locationFullName);

      // adds operator to ArrayList for tracking
      operatorList.add(
          MessageCli.OPERATOR_ENTRY.getMessage(
              operatorName, operatorCountId.toString(), locationFullName));
      operatorLocationList.add(locationAbbreviation);
      operatorNameList.add(operatorName);
    }
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
