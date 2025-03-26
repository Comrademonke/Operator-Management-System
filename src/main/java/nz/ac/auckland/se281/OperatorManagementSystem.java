package nz.ac.auckland.se281;

public class OperatorManagementSystem {

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    if (keyword.equals("*")) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    }
  }

  public void createOperator(String operatorName, String location) {

    // Converts location to the Location enum constant
    Types.Location locationEnum = Types.Location.fromString(location);
    String locationFullName = locationEnum.getFullName();

    // Stripping operator name of white space and storing each word
    String clearedWhiteSpaceOperatorName = operatorName.trim();
    String[] operatorNameParts = clearedWhiteSpaceOperatorName.split(" ");

    // Loops through each word of the operator name and takes the first initial to make operator id
    String operatorInitials = "";
    for (String operatorNamePart : operatorNameParts) {
      operatorInitials = operatorInitials + operatorNamePart.charAt(0);
    }

    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorInitials, locationFullName);
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
