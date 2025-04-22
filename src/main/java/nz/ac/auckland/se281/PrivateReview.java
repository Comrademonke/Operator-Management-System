package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  private String name;
  private String rating;
  private String email;
  private String followUp;
  private String reviewId;
  private String resolvedMessage;
  private boolean resolved;

  public PrivateReview(
      String name,
      String email,
      String rating,
      String reviewText,
      String followUp,
      String activityId,
      String reviewId) {
    super(name, rating, reviewText, activityId, reviewId);
    this.name = name;
    this.rating = rating;
    this.email = email;
    this.followUp = followUp;
    this.reviewId = reviewId;

    if (getFollowUp()) {
      resolved = false;
    } else {
      resolved = true;
    }
  }

  public String getEmail() {
    return email;
  }

  public boolean getFollowUp() {
    return followUp.equals("n");
  }

  public boolean isResolved() {
    return resolved;
  }

  public void resolvedReview(String resolvedMessage) {
    followUp = "n";
    this.resolved = true;
    this.resolvedMessage = resolvedMessage;
  }

  public String getResolvedMessage() {
    return resolvedMessage;
  }

  @Override
  public String toString() {
    return MessageCli.REVIEW_ENTRY_HEADER.getMessage(rating, "5", "Private", reviewId, name);
  }
}
