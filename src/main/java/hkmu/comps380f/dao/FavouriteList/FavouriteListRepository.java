package hkmu.comps380f.dao.FavouriteList;

import hkmu.comps380f.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavouriteListRepository extends JpaRepository<Attachment, UUID> {
}
