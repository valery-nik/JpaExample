package ru.oz.tutorials.dao;

import ru.oz.tutorials.model.Bid;
import ru.oz.tutorials.model.Item;
import ru.oz.tutorials.model.ItemBidSummary;

//import javax.ejb.Stateless;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

//@Stateless
public class ItemDAOImpl extends GenericDAOImpl<Item, Long>
    implements ItemDAO {

    public ItemDAOImpl() {
        super(Item.class);
    }

    public List<Item> findAll(boolean withBids) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> criteria = cb.createQuery(Item.class);
        Root<Item> i = criteria.from(Item.class);
        criteria.select(i)
            .distinct(true) // In-memory "distinct"!
            .orderBy(cb.asc(i.get("auctionEnd")));
        if (withBids)
            i.fetch("bids", JoinType.LEFT);
        return em.createQuery(criteria).getResultList();
    }

    public List<Item> findByName(String name, boolean substring) {
        return em.createNamedQuery(
            substring ? "getItemsByNameSubstring" : "getItemsByName"
        ).setParameter(
            "itemName",
            substring ? ("%" + name + "%") : name
        ).getResultList();
    }

    public List<ItemBidSummary> findItemBidSummaries() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ItemBidSummary> criteria =
            cb.createQuery(ItemBidSummary.class);
        Root<Item> i = criteria.from(Item.class);
        Join<Item, Bid> b = i.join("bids", JoinType.LEFT);
        criteria.select(
            cb.construct(
                ItemBidSummary.class,
                i.get("id"), i.get("name"), i.get("auctionEnd"),
                cb.max(b.<BigDecimal>get("amount"))
            )
        );
        criteria.orderBy(cb.asc(i.get("auctionEnd")));
        criteria.groupBy(i.get("id"), i.get("name"), i.get("auctionEnd"));
        return em.createQuery(criteria).getResultList();
    }
}
