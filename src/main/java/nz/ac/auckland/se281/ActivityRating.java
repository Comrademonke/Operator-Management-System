package nz.ac.auckland.se281;

public class ActivityRating {
  Activity activity;
  int totalRating = 0;
  int reviewCount = 0;
  int averageRating = 0;

  public ActivityRating(Activity activity, int totalRating, int reviewCount) {
    this.activity = activity;
    this.totalRating = totalRating;
    this.reviewCount = reviewCount;
  }

  public int getTotalRating() {
    return totalRating;
  }

  public int getReviewCount() {
    return reviewCount;
  }
}
