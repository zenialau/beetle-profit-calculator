//package src.test.persistence;
//
//import org.junit.jupiter.api.Test;
//import src.main.model.company.BuyersMap;
//import src.main.persistence.JsonWriter;
//
//import java.io.IOException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//// Reference: JsonSerializationDemo
//public class JsonWriterTest extends JsonTest {
//
//    @Test
//    void testWriterInvalidFile() {
//        try {
//            BuyersMap bm = new BuyersMap();
//            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileNmae.json");
//            writer.open();
//            fail("IOException was expected.");
//        } catch (IOException e) {
//            // pass
//        }
//    }
//
//    @Test
//    void testWriterEmptyBuyersMap() {
//        try {
//            BuyersMap bm = new BuyersMap();
//            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBuyersMap.json");
//            writer.open();
//            writer.write(bm);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterEmptyBuyersMap.json");
//            bm = reader.read();
//            assertEquals("My collection", bm.getName());
//            assertEquals(0, bm.getListOfBuyers().size());
//        } catch (IOException e) {
//            fail("Exception should not hvae been thrown");
//        }
//    }
//
//    @Test
//    void testWriterGeneralBuyersMap() {
//        try {
//            BookCollection bc = new BookCollection("My Collection - General");
//            BuyersMap bm = new BuyersMap();
//            Book book1 = new Book("Abc", "Aut", "fantasy");
//            Book book2 = new Book("Hello", "Person", "magical realism");
//            bc.addBook(book1);
//            bc.addBook(book2);
//            book1.readBook();
//            book1.rateBook(3.25);
//
//            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookCollection.json");
//            writer.open();
//            writer.write(bc);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterGeneralBookCollection.json");
//            bc = reader.read();
//            assertEquals("My Collection - General", bc.getName());
//            List<Book> books = bc.getBookList();
//            assertEquals(2, books.size());
//            checkBook("Abc", "Aut", "fantasy", true, 3.25, books.get(0));
//            checkBook("Hello", "Person", "magical realism", false, 0, books.get(1));
//
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }
//
//}
