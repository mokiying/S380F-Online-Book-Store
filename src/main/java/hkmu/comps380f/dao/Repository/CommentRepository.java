package hkmu.comps380f.dao.Repository;

import hkmu.comps380f.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
}
