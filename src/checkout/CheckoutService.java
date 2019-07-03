package checkout;
import java.time.LocalDate;
import java.util.ArrayList;

public class CheckoutService {

    private ArrayList<Offer> Offers = new ArrayList<>();
    private Check check;

    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        if (check == null) {
            openCheck();
        }
        check.addProduct(product);
    }

    public Check closeCheck() {
        Check closedCheck = check;
        for (Offer Offer : Offers) {
            Offer.apply(check);
        }
        Offers.clear();
        check = null;
        return closedCheck;
    }

    public void useOffer(Offer offer, LocalDate date) {
        if(!offer.isOfferExpired(date)){
            Offers.add(offer);
        }
    }
}
