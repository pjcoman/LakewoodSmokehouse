package comapps.com.theblindbutcherdallas;

public class ReviewListObject {

	public String reviewName;
	public String reviewRating;
	public String review;

	public String getReviewName() {
		return reviewName;
	}

	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;

    }

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "ReviewList [reviewname=" + reviewName + ", " + "rating=" + reviewRating + "review=" + review +"]";
	}

}
