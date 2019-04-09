package spring.cloud.service.vo;

public class TestMain {

    public static void main(String[] args) throws Exception {
        Card card = new Card();
        card.setCardName("card1");
        TestClone testClone = new TestClone();
        TestDate testDate = new TestDate();
        testDate.setCard(card);
        testDate.setId(12L);
        testClone.setTestDate(testDate);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 70000; i++) {
            Card card1 = new Card();
            card1.setCardName("card1" + i);
            TestClone testClone1 = new TestClone();
            TestDate testDate1 = new TestDate();
            testDate1.setCard(card1);
            testDate1.setId(16L);
            testClone1.setTestDate(testDate1);
        }
        long end = System.currentTimeMillis();
        System.out.println("1----------->" + (end-start));


        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 70000; i++) {
            TestClone clone = (TestClone)testClone.clone();
            clone.getTestDate().setId(18L);
            clone.getTestDate().getCard().setCardName("card333" + i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("1----------->" + (end1 - start1));

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 70000; i++) {
            TestClone clone2 = IoClone.clone(testClone);
            clone2.setId("1");
            clone2.getTestDate().setId(33L);
            clone2.getTestDate().getCard().setCardName("asdfg");
        }
        long end2 = System.currentTimeMillis();
        System.out.println("1----------->" + (end2 - start2));

    }
}
