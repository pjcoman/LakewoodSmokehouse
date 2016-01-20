package comapps.com.lakewoodsmokehouse;

class ReviewListObject {

	private String reviewName;
	private String reviewRating;
	private String review;

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
