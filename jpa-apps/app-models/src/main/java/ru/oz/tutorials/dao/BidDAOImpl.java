package ru.oz.tutorials.dao;

//import org.Bid;
//
//import javax.ejb.Stateless;

import ru.oz.tutorials.model.Bid;

//@Stateless
public class BidDAOImpl extends GenericDAOImpl<Bid, Long>
    implements BidDAO {

    public BidDAOImpl() {
        super(Bid.class);
    }
}
