package hkmu.comps380f.dao.FavouriteList;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FavouriteListService {
    @Resource
    private hkmu.comps380f.dao.FavouriteList.FavouriteListRepository FavouriteListRepository;

    @Resource
    private hkmu.comps380f.dao.TicketUserRepository TicketUserRepository;


}
