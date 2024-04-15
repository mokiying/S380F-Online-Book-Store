package hkmu.comps380f.dao.Repository;

import hkmu.comps380f.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite,String> {
    public Favourite getFavouriteByUsername(String username);
}
