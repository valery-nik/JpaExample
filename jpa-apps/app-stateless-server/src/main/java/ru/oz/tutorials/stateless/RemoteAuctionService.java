package ru.oz.tutorials.stateless;

import ru.oz.tutorials.model.Bid;
import ru.oz.tutorials.model.InvalidBidException;
import ru.oz.tutorials.model.Item;

import java.util.List;

public interface RemoteAuctionService {

    List<Item> getItems(boolean withBids);

    Item storeItem(Item item);

    Item placeBid(Bid bid) throws InvalidBidException;
}
