package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AndrewWebServicesTest {
    FakeDatabase fakeDatabase;
    StubRecommender recommender;
    PromoService mockPromoService;
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        // You need to use some mock objects here
        fakeDatabase = new FakeDatabase(); // We probably don't want to access our real database...
        recommender = new StubRecommender();
        mockPromoService = Mockito.mock(PromoService.class);
        
        andrewWebService = new AndrewWebServices(fakeDatabase, recommender, mockPromoService);
    }

    @Test
    public void testLogIn() {
        // This is taking way too long to test
        assertTrue(andrewWebService.logIn("Bruh", 12345));
    }

    @Test
    public void testGetRecommendation() {
        // This is taking way too long to test
        assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
    }

    @Test
    public void testSendEmail() {
        // How should we test sendEmail() when it doesn't have a return value?
        // Hint: is there something from Mockito that seems useful here?
        String testEmail = "test@testing.com";
        andrewWebService.sendPromoEmail(testEmail);
        Mockito.verify(mockPromoService).mailTo(testEmail);
    }

    @Test
    public void testNoSendEmail() {
        // How should we test that no email has been sent in certain situations (like right after logging in)?
        // Hint: is there something from Mockito that seems useful here?
        Mockito.verifyNoInteractions(mockPromoService);
        
    }

    class FakeDatabase extends Database {

        @Override
        public int getPassword(String accountName) {
            if (accountName == "Bruh") {
                return 12345;
            } else {
                return 0;
            }
        }

    }

    class StubRecommender extends RecSys {

        @Override
        public String getRecommendation(String accountName) {
            return "Animal House";
        }

    }
}
