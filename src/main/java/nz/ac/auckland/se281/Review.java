package nz.ac.auckland.se281;

public abstract class Review {
  private String name;
  private String rating;
  private String reviewText;
  private String activityId;
  private String reviewId;

  public Review(String name, String rating, String reviewText, String activityId, String reviewId) {
    this.name = name;
    this.rating = rating;
    this.reviewText = reviewText;
    this.activityId = activityId;
    this.reviewId = reviewId;
  }

  public String getName() {
    return name;
  }

  public int getRating() {
    return Integer.valueOf(rating);
  }

  public String getReviewText() {
    return reviewText;
  }

  public String getActivityId() {
    return activityId;
  }

  public String getReviewId() {
    return reviewId;
  }

  @Override
  public String toString() {
    return MessageCli.REVIEW_ENTRY_HEADER.getMessage(rating, "5", "publicity", reviewId, name);
  }
}
