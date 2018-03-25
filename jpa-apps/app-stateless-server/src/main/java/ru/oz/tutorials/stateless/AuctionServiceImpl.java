package ru.oz.tutorials.stateless;

import ru.oz.tutorials.dao.BidDAO;
import ru.oz.tutorials.dao.ItemDAO;
import ru.oz.tutorials.model.Bid;
import ru.oz.tutorials.model.InvalidBidException;
import ru.oz.tutorials.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.inject.Inject;
import java.util.List;

//@javax.ejb.Stateless
//@javax.ejb.Local(AuctionService.class)
//@javax.ejb.Remote(RemoteAuctionService.class)
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    protected ItemDAO itemDAO;

    @Autowired
    protected BidDAO bidDAO;

    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED) // Default
    @Transactional
    public List<Item> getItems(boolean withBids) {
        return itemDAO.findAll(withBids);
    }

    @Override
    public Item storeItem(Item item) {
        return itemDAO.makePersistent(item);
    }

    @Override
    public Item placeBid(Bid bid) throws InvalidBidException {
        bid = bidDAO.makePersistent(bid);

        // Check that business rules are met
        if (!bid.getItem().isValidBid(bid))
            throw new InvalidBidException("Bid amount too low!");

        itemDAO.checkVersion(bid.getItem(), true);

        return bid.getItem();
    }
}