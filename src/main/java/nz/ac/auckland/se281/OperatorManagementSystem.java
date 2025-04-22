package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import nz.ac.auckland.se281.Types.ActivityType;

public class OperatorManagementSystem {

  // Initialize values;
  private int totalOperatorCount = 0;
  private int totalActivityCount = 0;
  private ArrayList<Operator> operatorList = new ArrayList<>();
  private ArrayList<Activity> activityList = new ArrayList<>();
  private ArrayList<PublicReview> publicReviewList = new ArrayList<>();
  private ArrayList<PrivateReview> privateReviewList = new ArrayList<>();
  private ArrayList<ExpertReview> expertReviewList = new ArrayList<>();

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
    // Checks if operatorId is valid with an operator
    if (getOperatorName(operatorId) == null) {
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }

    // Checks for number of matching activities
    int activityFound = 0;
    for (Activity activity : activityList) {
      if (activity.getOperatorId().equals(operatorId)) {
        activityFound++;
      }
    }

    // Prints number of matching activities found
    if (getOperatorName(operatorId) != null && activityFound == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    } else if (activityFound == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", activityFound + "", "y", ":");
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", activityFound + "", "ies", ":");
    }

    for (Activity activity : activityList) {
      if (activity.getOperatorId().equals(operatorId)) {
        System.out.println(activity + getOperatorName(operatorId));
      }
    }
  }

  private String getOperatorName(String operatorId) {
    for (Operator operator : operatorList) {
      if (operator.getId().contains(operatorId)) {
        return operator.getName();
      }
    }
    return null;
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // Checks for invalid activity name
    if (activityName.trim().length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }

    // Checks if operatorId is valid with an operator
    if (getOperatorName(operatorId) == null) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }

    Types.ActivityType activityTypeEnum = Types.ActivityType.fromString(activityType);

    // Checks the activity count for an operator
    int activityCount = 1;
    for (Activity activity : activityList) {
      if (activity.getOperatorId().equals(operatorId)) {
        activityCount++;
      }
    }

    // Formats the activity count
    String activityCountFormat = "";
    if (activityCount < 10) {
      activityCountFormat = "-00";
    } else if (activityCount > 10 && activityCount < 100) {
      activityCountFormat = "-0";
    }

    MessageCli.ACTIVITY_CREATED.printMessage(
        activityName.trim(),
        operatorId + activityCountFormat + activityCount,
        activityTypeEnum.getName(),
        getOperatorName(operatorId));

    addOperatorActivity(operatorId, activityTypeEnum);

    activityList.add(
        new Activity(
            activityName, activityTypeEnum, operatorId, activityCountFormat + activityCount));
    totalActivityCount++;
  }

  private void addOperatorActivity(String operatorId, ActivityType activity) {
    // adds the activity to the operator
    for (Operator operator : operatorList) {
      if (operator.getId().contains(operatorId)) {
        operator.addActivity(activity);
      }
    }
  }

  public void searchActivities(String keyword) {
    String trimAndLowerCaseKeyword = keyword.trim().toLowerCase();

    // Prints message when searching for all activities
    if (trimAndLowerCaseKeyword.equals("*") && totalActivityCount == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    } else if (trimAndLowerCaseKeyword.equals("*") && totalActivityCount == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "is", Integer.toString(totalActivityCount), "y", ":");
      for (Activity activity : activityList) {
        System.out.println(activity + getOperatorName(activity.getOperatorId()));
      }
      return;
    } else if (trimAndLowerCaseKeyword.equals("*") && totalActivityCount > 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "are", Integer.toString(totalActivityCount), "ies", ":");
      for (Activity activity : activityList) {
        System.out.println(activity + getOperatorName(activity.getOperatorId()));
      }
      return;
    }
    // Initialise values;
    int activitiesFound = 0;
    ArrayList<Activity> matchingActivityList = new ArrayList<>();

    // Adds the activity to a matching activity list if it is matching a keyword
    for (Activity activity : activityList) {
      if (activity.getActivityName().toLowerCase().contains(trimAndLowerCaseKeyword)
          || activity.getActivityType().getName().toLowerCase().contains(trimAndLowerCaseKeyword)
          || getLocation(activity.getOperatorId()).toLowerCase().contains(trimAndLowerCaseKeyword)
          || activity.getOperatorId().toLowerCase().contains(trimAndLowerCaseKeyword)) {
        activitiesFound++;
        matchingActivityList.add(activity);
      }
    }

    // Prints the number of matching activities and the list of matching activities
    if (activitiesFound == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
    } else if (activitiesFound == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", Integer.toString(activitiesFound), "y", ":");
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "are", Integer.toString(activitiesFound), "ies", ":");
    }
    for (Activity activity : matchingActivityList) {
      System.out.println(activity + getOperatorName(activity.getOperatorId()));
    }
  }

  private String getLocation(String operatorId) {
    for (Operator operator : operatorList) {
      if (operator.getId().equals(operatorId)) {
        return operator.getLocation().getFullName();
      }
    }
    return null;
  }

  public void addPublicReview(String activityId, String[] options) {
    // Checks for invalid activityId
    if (getActivityName(activityId) == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    // Adjusts the rating to fit between 1 and 5
    if (Integer.valueOf(options[2]) < 1) {
      options[2] = "1";
    } else if (Integer.valueOf(options[2]) > 5) {
      options[2] = "5";
    }

    int reviewNumber = 1;
    reviewNumber = getReviewNumber(activityId, reviewNumber);

    MessageCli.REVIEW_ADDED.printMessage(
        "Public", activityId + "-R" + reviewNumber, getActivityName(activityId));

    publicReviewList.add(
        new PublicReview(
            options[0],
            options[1],
            options[2],
            options[3],
            activityId,
            activityId + "-R" + reviewNumber));
  }

  public void addPrivateReview(String activityId, String[] options) {
    // Checks for invalid activityId
    if (getActivityName(activityId) == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    // Adjusts the rating to fit between 1 and 5
    if (Integer.valueOf(options[2]) < 1) {
      options[2] = "1";
    } else if (Integer.valueOf(options[2]) > 5) {
      options[2] = "5";
    }

    int reviewNumber = 1;
    reviewNumber = getReviewNumber(activityId, reviewNumber);

    MessageCli.REVIEW_ADDED.printMessage(
        "Private", activityId + "-R" + reviewNumber, getActivityName(activityId));

    privateReviewList.add(
        new PrivateReview(
            options[0],
            options[1],
            options[2],
            options[3],
            options[4],
            activityId,
            activityId + "-R" + reviewNumber));
  }

  public void addExpertReview(String activityId, String[] options) {
    // Checks for invalid activityId
    if (getActivityName(activityId) == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    // Adjusts the rating to fit between 1 and 5
    if (Integer.valueOf(options[1]) < 1) {
      options[1] = "1";
    } else if (Integer.valueOf(options[1]) > 5) {
      options[1] = "5";
    }

    int reviewNumber = 1;
    reviewNumber = getReviewNumber(activityId, reviewNumber);

    MessageCli.REVIEW_ADDED.printMessage(
        "Expert", activityId + "-R" + reviewNumber, getActivityName(activityId));

    expertReviewList.add(
        new ExpertReview(
            options[0],
            options[1],
            options[2],
            options[3],
            activityId,
            activityId + "-R" + reviewNumber));
  }

  private int getReviewNumber(String activityId, int reviewNumber) {
    // Checks the number of reviews and returns the number of matching reviews
    for (PublicReview review : publicReviewList) {
      if (review.getActivityId().equals(activityId)) {
        reviewNumber++;
      }
    }
    for (PrivateReview review : privateReviewList) {
      if (review.getActivityId().equals(activityId)) {
        reviewNumber++;
      }
    }
    for (ExpertReview review : expertReviewList) {
      if (review.getActivityId().equals(activityId)) {
        reviewNumber++;
      }
    }
    return reviewNumber;
  }

  public void displayReviews(String activityId) {
    if (getActivityName(activityId) == null) {
      MessageCli.ACTIVITY_NOT_FOUND.printMessage(activityId);
      return;
    }
    int reviewNumber = 0;
    reviewNumber = getReviewNumber(activityId, reviewNumber);

    // Print number of matching reviews
    if (reviewNumber == 0) {
      MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", getActivityName(activityId));
    } else if (reviewNumber == 1) {
      MessageCli.REVIEWS_FOUND.printMessage(
          "is", reviewNumber + "", "", getActivityName(activityId));
    } else {
      MessageCli.REVIEWS_FOUND.printMessage(
          "are", reviewNumber + "", "s", getActivityName(activityId));
    }

    // Prints reviews
    for (PublicReview review : publicReviewList) {
      if (review.getActivityId().equals(activityId)) {
        System.out.println(review);
        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(review.getReviewText());

        // Checks if the review is endorsed
        if (review.isEndorsed()) {
          MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
        }
      }
    }
    for (PrivateReview review : privateReviewList) {
      if (review.getActivityId().equals(activityId)) {
        System.out.println(review);
        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(review.getReviewText());

        // Checks for any follow ups and any resolved issues
        if (review.getFollowUp() && !review.isResolved()) {
          MessageCli.REVIEW_ENTRY_RESOLVED.printMessage("-");
        } else if (review.getFollowUp() && review.isResolved()) {
          MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(review.getResolvedMessage());
        } else {
          MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(review.getEmail());
        }
      }
    }
    for (ExpertReview review : expertReviewList) {
      if (review.getActivityId().equals(activityId)) {
        System.out.println(review);
        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(review.getReviewText());

        // Checks if recommended by expert
        if (review.isRecommended()) {
          MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
        }
        // Checks if there are images
        if (!review.isImagesEmpty()) {
          StringBuilder imagesBuilder = new StringBuilder();
          for (int images = 0; images < review.getImages().size(); images++) {
            if (images > 0) {
              imagesBuilder.append(",");
            }
            imagesBuilder.append(review.getImages().get(images).trim());
          }
          MessageCli.REVIEW_ENTRY_IMAGES.printMessage(imagesBuilder.toString());
        }
      }
    }
  }

  private String getActivityName(String activityId) {
    for (Activity activity : activityList) {
      if (activity.getActivityId().equals(activityId)) {
        return activity.getActivityName();
      }
    }
    return null;
  }

  public void endorseReview(String reviewId) {
    int endorsementType = 0;
    // Checks if reviewId is valid
    for (PublicReview review : publicReviewList) {
      if (review.getReviewId().equals(reviewId)) {
        endorsementType = 1;
        break;
      }
    }
    for (PrivateReview review : privateReviewList) {
      if (review.getReviewId().equals(reviewId)) {
        endorsementType = 2;
        break;
      }
    }
    for (ExpertReview review : expertReviewList) {
      if (review.getReviewId().equals(reviewId)) {
        endorsementType = 2;
        break;
      }
    }

    // Prints message based off the endorsement type
    if (endorsementType == 0) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    } else if (endorsementType == 2) {
      MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
      return;
    } else if (endorsementType == 1) {
      MessageCli.REVIEW_ENDORSED.printMessage(reviewId);
    }

    // Endorses the review
    for (PublicReview review : publicReviewList) {
      if (review.getReviewId().equals(reviewId)) {
        review.reviewEndorsed();
      }
    }
  }

  public void resolveReview(String reviewId, String response) {
    // Initialize values
    boolean reviewExists = false;
    boolean isPrivate = false;

    // Check all review lists
    for (PrivateReview review : privateReviewList) {
      if (review.getReviewId().equals(reviewId)) {
        reviewExists = true;
        isPrivate = true;
        break;
      }
    }

    if (!reviewExists) {
      // Checks if review exists, but is public or expert
      for (PublicReview review : publicReviewList) {
        if (review.getReviewId().equals(reviewId)) {
          reviewExists = true;
          break;
        }
      }
      for (ExpertReview review : expertReviewList) {
        if (review.getReviewId().equals(reviewId)) {
          reviewExists = true;
          break;
        }
      }
    }

    if (!reviewExists) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    } else if (!isPrivate) {
      MessageCli.REVIEW_NOT_RESOLVED.printMessage(reviewId);
      return;
    }

    // Resolve the issue
    for (PrivateReview review : privateReviewList) {
      if (review.getReviewId().equals(reviewId)) {
        MessageCli.REVIEW_RESOLVED.printMessage(reviewId);
        review.resolvedReview(response);
        return;
      }
    }
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // Print message if reviewId belongs to a public or private
    for (PublicReview review : publicReviewList) {
      if (review.getReviewId().equals(reviewId)) {
        MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
        return;
      }
    }
    for (PrivateReview review : privateReviewList) {
      if (review.getReviewId().equals(reviewId)) {
        MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
        return;
      }
    }

    for (ExpertReview review : expertReviewList) {
      if (review.getReviewId().equals(reviewId)) {
        MessageCli.REVIEW_IMAGE_ADDED.printMessage(imageName, reviewId);
        review.addImages(imageName);
        return;
      }
    }
    MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
  }

  public void displayTopActivities() {
    HashMap<Types.Location, ArrayList<ActivityRating>> locationRatings = new HashMap<>();

    // Initialise locations
    for (Types.Location location : Types.Location.values()) {
      locationRatings.put(location, new ArrayList<>());
    }

    // Collect all activities and their ratings
    for (Activity activity : activityList) {
      int totalRating = 0;
      int reviewCount = 0;
      for (PublicReview review : publicReviewList) {
        if (review.getReviewId().equals(activity.getActivityId())) {
          totalRating += review.getRating();
          reviewCount++;
        }
      }
      for (ExpertReview review : expertReviewList) {
        if (review.getReviewId().equals(activity.getActivityId())) {
          totalRating += review.getRating();
          reviewCount++;
        }
      }

      if (reviewCount > 0) {
        Types.Location location = getLocationType(activity.getOperatorId());
        if (location != null) {
          locationRatings.get(location).add(new ActivityRating(activity, totalRating, reviewCount));
        }
      }
    }

    for (Types.Location location : locationRatings.keySet()) {
      ArrayList<ActivityRating> ratings = locationRatings.get(location);
      // Prints message if no reviews exist for location
      if (ratings.isEmpty()) {
        MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location.getFullName());
      }
    }
  }

  private Types.Location getLocationType(String operatorId) {
    for (Operator operator : operatorList) {
      if (operator.getId().equals(operatorId)) {
        return operator.getLocation();
      }
    }
    return null;
  }
}
