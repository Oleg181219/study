package diplom.blog.service;

import diplom.blog.api.response.TagResponse;
import diplom.blog.model.DtoModel.TagDto;
import diplom.blog.model.Post;
import diplom.blog.model.Tags;
import diplom.blog.repo.PostRepository;
import diplom.blog.repo.TagsRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TagService {
    private final TagsRepository tagsRepository;
    private final PostRepository postRepository;


    public TagService(TagsRepository tagsRepository
            , PostRepository postRepository) {
        this.tagsRepository = tagsRepository;
        this.postRepository = postRepository;
    }
    TagResponse tagResponse = new TagResponse();

    public TagResponse getTags(String query) {
        List<Tags> allTagToPost = tagsRepository.findAll();
        List<Post> allPosts = postRepository.getCountPosts();
        ArrayList<TagDto> respTags = new ArrayList<>();
        HashMap<String, Integer> respTagsList = new HashMap<>();

       for(Tags tag: allTagToPost){

           if (respTagsList.containsKey(tag.getName())){
               respTagsList.put(tag.getName(),(respTagsList.get(tag.getName())+ 1));
           } else respTagsList.put(tag.getName() , 1);
       }

        int countOfMaxPopularTag = respTagsList.entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .get().getValue();


        double dWeightMax = (1 / ((double) countOfMaxPopularTag / (double) allPosts.size()));

        respTagsList.entrySet().forEach(entry -> {
            TagDto respTag = new TagDto();
            respTag.setName(entry.getKey());
            respTag.setWeight(entry.getValue() / (double) allPosts.size() * dWeightMax);
            respTags.add(respTag);
        });
        tagResponse.setTags(respTags);
        return tagResponse;
    }


    }


