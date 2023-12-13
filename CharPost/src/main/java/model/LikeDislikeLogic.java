package model;

public class LikeDislikeLogic {
	public void like(PostBean post) {
		int count=post.getLike();
		post.setLike(count+1);
	}
	public void dislike(PostBean post) {
		int count=post.getDislike();
		post.setDislike(count+1);
	}
}
