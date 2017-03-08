/**
 * Created by tyler on 2/20/2017.
 * Runs our junit tests
 */

public class TestDriver {

    public static void main(String[] args) {

        org.junit.runner.JUnitCore.main("test.UserDaoTest");
        org.junit.runner.JUnitCore.main("test.AuthTokenDaoTest");
        org.junit.runner.JUnitCore.main("test.EventDaoTest");
        org.junit.runner.JUnitCore.main("test.PersonDaoTest");
        org.junit.runner.JUnitCore.main("test.MultiDaoTest");
        org.junit.runner.JUnitCore.main("test.DataGeneratorTest");

        org.junit.runner.JUnitCore.main("test.FamilyMapServerProxyTest");
        org.junit.runner.JUnitCore.main("test.LoadServiceTest");
        org.junit.runner.JUnitCore.main("test.EventServiceTest");
        org.junit.runner.JUnitCore.main("test.EventsServiceTest");
        org.junit.runner.JUnitCore.main("test.LoginServiceTest");
        org.junit.runner.JUnitCore.main("test.PeopleServiceTest");
        org.junit.runner.JUnitCore.main("test.PersonServiceTest");
        org.junit.runner.JUnitCore.main("test.RegisterServiceTest");
        org.junit.runner.JUnitCore.main("test.FillServiceTest");
        org.junit.runner.JUnitCore.main("test.ClearServiceTest");
    }
}
