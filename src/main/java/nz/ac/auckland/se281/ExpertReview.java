package nz.ac.auckland.se281;

public class ExpertReview extends Review {
  private String name;
  private String rating;
  private String recommendation;
  private String reviewId;

  public ExpertReview(
      String name,
      String rating,
      String reviewText,
      String recommendation,
      String activityId,
      String reviewId) {
    super(name, rating, reviewText, activityId, reviewId);
    this.name = name;
    this.rating = rating;
    this.recommendation = recommendation;
    this.reviewId = reviewId;
  }

  public boolean isRecommended() {
    return !recommendation.equals("n");
  }

  @Override
  public String toString() {
    return MessageCli.REVIEW_ENTRY_HEADER.getMessage(rating, "5", "Expert", reviewId, name);
  }
}
