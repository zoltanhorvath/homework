package hu.horvathzoltan.facade;

import hu.horvathzoltan.entity.GuestBook;
import hu.horvathzoltan.entity.Park;
import hu.horvathzoltan.entity.Visitor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

@Stateless
public class GuestBookFacade extends AbstractFacade<GuestBook> {

    @Inject
    ParkFacade parkFacade;
    @Inject
    VisitorFacade visitorFacade;

    public GuestBookFacade() {
        super(GuestBook.class);
    }

    public void writeGuestBookEntry(Long visitorId, Long parkId, String text) {
        Visitor visitor = visitorFacade.findById(visitorId);
        Park park = parkFacade.findById(parkId);

        if (park.getVisitors().contains(visitor)) {
            GuestBook guestBook = new GuestBook();
            guestBook.setPark(park);
            guestBook.setVisitor(visitor);
            guestBook.setText(text);
            persist(guestBook);
        }
    }

    public List<String> getGuestBookEntries(Long visitorId, Long parkId) {
        Park park = parkFacade.findById(parkId);
        Visitor visitor = visitorFacade.findById(visitorId);

        TypedQuery<GuestBook> query = entityManager.createNamedQuery("getGuestBookEntriesForPark", GuestBook.class);
        query.setParameter("park", park);
        query.setParameter("visitor", visitor);

        List<GuestBook> result = query.getResultList();
        List<String> texts = new ArrayList<>();

        for (GuestBook book : result) {
            String entry = book.getText() + book.getEntryDate();
            texts.add(entry);
        }

        return texts;
    }

}
