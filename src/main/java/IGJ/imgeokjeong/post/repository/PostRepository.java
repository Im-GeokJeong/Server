package IGJ.imgeokjeong.post.repository;

import IGJ.imgeokjeong.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContains(String title);

    List<Post> findByRegion(String region);
}
