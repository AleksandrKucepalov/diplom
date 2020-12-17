package main.service;


import main.api.response.ResultResponse;
import main.model.Post;
import main.model.PostVote;
import main.model.User;
import main.repository.PostRepository;
import main.repository.PostVoteRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VoteService {
    @Autowired
    private PostVoteRepository postVoteRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public ResultResponse setVote(long postId, int likeOrDislike, String email) {

        try {
            main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user " + email + " not found"));

            PostVote postVoteTek = postVoteRepository.findByValueAndPostID(likeOrDislike, postId, userTek.getId());

            if (postVoteTek != null) {
                return new ResultResponse();
            } else {
                PostVote postVoteTekInv = postVoteRepository.findByValueAndPostID(-likeOrDislike, postId, userTek.getId());
                if (postVoteTekInv != null) {
                    postVoteRepository.delete(postVoteTekInv);
                }

                PostVote postVote = new PostVote();
                postVote.setPost(postRepository.findById(postId));
                postVote.setValue(likeOrDislike);
                postVote.setTime(new Date());
                postVote.setUser(userTek);

                postVoteRepository.save(postVote);

                ResultResponse resultResponse = new ResultResponse();
                resultResponse.setResult(true);

                return resultResponse;
            }
        } catch (Exception e) {
            return new ResultResponse();
        }
    }

}
