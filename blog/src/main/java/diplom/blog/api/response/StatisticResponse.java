package diplom.blog.api.response;

import org.springframework.stereotype.Component;

@Component
public class StatisticResponse {
//    "postsCount":7,
//            "likesCount":15,
//            "dislikesCount":2,
//            "viewsCount":58,
//            "firstPublication":1590217200

    private Long postsCount;

    private Long likesCount;

    private Long disLikesCount;

    private Long viewsCount;

    private Long firstPublication;


    public Long getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(Long postsCount) {
        this.postsCount = postsCount;
    }

    public Long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Long likesCount) {
        this.likesCount = likesCount;
    }

    public Long getDisLikesCount() {
        return disLikesCount;
    }

    public void setDisLikesCount(Long disLikesCount) {
        this.disLikesCount = disLikesCount;
    }

    public Long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Long viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Long getFirstPublication() {
        return firstPublication;
    }

    public void setFirstPublication(Long firstPublication) {
        this.firstPublication = firstPublication;
    }
}
