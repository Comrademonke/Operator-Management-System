package nz.ac.auckland.se281;

import java.util.ArrayList;

public class OperatorManagementSystem {

  private int totalOperatorCount = 0;
  private ArrayList<Operator> operatorList = new ArrayList<>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {

    String trimAndLowerCaseKeyword = keyword.trim().toLowerCase();

    // Prints message based off total operator count when searching for all operators
    if (trimAndLowerCaseKeyword.equals("*") && totalOperatorCount == 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else if (trimAndLowerCaseKeyword.equals("*") && totalOperatorCount == 1) {
      MessageCli.OPERATORS_FOUND.printMessage("is", Integer.toString(totalOperatorCount), "", ":");
      for (Operator operator : operatorList) {
        System.out.println(operator);
      }
    } else if (trimAndLowerCaseKeyword.equals("*") && totalOperatorCount > 1) {
      MessageCli.OPERATORS_FOUND.printMessage(
          "are", Integer.toString(totalOperatorCount), "s", ":");
      for (Operator operator : operatorList) {
        System.out.println(operator);
      }
    } else if (!trimAndLowerCaseKeyword.equals("*")) {

      // Searching for existing operators with keywords and adds them to operator arraylist
      int operatorsFound = 0;
      ArrayList<Operator> matchingOperatorList = new ArrayList<>();
      for (Operator operator : operatorList) {
        if (operator.getName().toLowerCase().contains(trimAndLowerCaseKeyword)
            || operator.getId().toLowerCase().contains(trimAndLowerCaseKeyword)
            || operator
                .getLocation()
                .getFullName()
                .toLowerCase()
                .contains(trimAndLowerCaseKeyword)) {
          operatorsFound++;
          matchingOperatorList.add(operator);
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
      for (Operator operator : matchingOperatorList) {
        System.out.println(operator.toString());
      }
    }
  }

  public void createOperator(String operatorName, String location) {

    // Converts location to the Location enum constant
    String trimLocation = location.trim();
    Types.Location locationEnum = Types.Location.fromString(trimLocation);

    // Stripping operator name of white space and storing each word
    String trimOperatorName = operatorName.trim();
    String[] operatorNameParts = trimOperatorName.split(" ");

    // Checks for invalid operator name
    if (trimOperatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(trimOperatorName);
      return;
    }

    // Checks for invalid location name
    if (locationEnum == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    }

    String locationFullName = locationEnum.getFullName();
    String locationAbbreviation = locationEnum.getLocationAbbreviation();

    // Checks for existing operators
    for (Operator operator : operatorList) {
      if (operator.getName().equalsIgnoreCase(trimOperatorName)
          && operator.getLocation() == locationEnum) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
            trimOperatorName, locationFullName);
        return;
      }
    }

    // Loops through each word of the operator name and takes the first initial to make operator
    // id
    String operatorInitials = "";
    for (String operatorNamePart : operatorNameParts) {
      operatorInitials = operatorInitials + operatorNamePart.toUpperCase().charAt(0);
    }

    // Loops through operatorsList and counts operators that exist in the same location
    int operatorCount = 1;
    for (Operator operatorLocation : operatorList) {
      if (operatorLocation.getLocation() == locationEnum) {
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
    } else if (operatorCount >= 10 && operatorCount < 99) {
      operatorCountId.append("0");
    }
    operatorCountId.append(operatorCountString);

    MessageCli.OPERATOR_CREATED.printMessage(
        trimOperatorName, operatorCountId.toString(), locationFullName);

    // adds operator to ArrayList for tracking
    operatorList.add(new Operator(trimOperatorName, operatorCountId.toString(), locationEnum));
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
