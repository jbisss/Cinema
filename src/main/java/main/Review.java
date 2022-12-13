package main;

public class Review {
    private final String review;
    private final User reviewer;
    public Review(User reviewer, String review){
        this.reviewer = reviewer;
        this.review = review;
    }
    @Override
    public String toString(){
        return this.reviewer.getName() + "@" + this.reviewer.getLogin() + "\n" + this.review + "\n";
    }
}
