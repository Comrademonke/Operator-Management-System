package nz.ac.auckland.se281;

public class PublicReview extends Review {
  private String name;
  private String rating;
  private String reviewId;
  private boolean endorsedReview = false;

  public PublicReview(
      String name,
      String anonymous,
      String rating,
      String reviewText,
      String activityId,
      String reviewId) {
    super(name, rating, reviewText, activityId, reviewId);
    this.name = name;
    this.rating = rating;
    this.reviewId = reviewId;

    if (!anonymous.equals("n")) {
      this.name = "Anonymous";
    }
  }

  public boolean isEndorsed() {
    return endorsedReview;
  }

  public void reviewEndorsed() {
    endorsedReview = true;
  }

  @Override
  public String toString() {
    return MessageCli.REVIEW_ENTRY_HEADER.getMessage(rating, "5", "Public", reviewId, name);
  }
}
