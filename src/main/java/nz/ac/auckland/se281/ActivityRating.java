package nz.ac.auckland.se281;

public class ActivityRating {
  private Activity activity;
  private int totalRating = 0;
  private int reviewCount = 0;

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

  public Activity getActivity() {
    return activity;
  }
}
