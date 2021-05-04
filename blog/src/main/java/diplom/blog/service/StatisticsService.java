package diplom.blog.service;

import diplom.blog.api.response.StatisticResponse;
import diplom.blog.repo.PostRepository;
import diplom.blog.repo.PostVotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Component
public class StatisticsService {
    private final PostRepository postRepository;
    private final PostVotesRepository postVotesRepository;

    @Autowired
    public StatisticsService(PostRepository postRepository
            , PostVotesRepository postVotesRepository) {
        this.postRepository = postRepository;
        this.postVotesRepository = postVotesRepository;
    }

//"postsCount":7,
//        "likesCount":15,
//        "dislikesCount":2,
//        "viewsCount":58,
//        "firstPublication":1590217200

    public ResponseEntity<StatisticResponse> myStatistics(Principal principal){

        var statisticResponse = new StatisticResponse();

        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
        }

        var myPosts = postRepository.findAllPostByUserEmail(principal.getName());
        statisticResponse.setPostsCount((long)myPosts.size());


        return ResponseEntity.ok(statisticResponse);
    }



}
