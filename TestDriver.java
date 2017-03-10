package test;

/**
 * Created by tyler on 2/20/2017.
 * Runs our junit tests
 */

public class TestDriver {

    public static void main(String[] args) {

        org.junit.runner.JUnitCore.main(
                "UserDaoTest",
                "AuthTokenDaoTest",
                "EventDaoTest",
                "PersonDaoTest",
                "MultiDaoTest",
                "DataGeneratorTest",
                "FamilyMapServerProxyTest",
                "LoadServiceTest",
                "EventServiceTest",
                "EventsServiceTest",
                "LoginServiceTest",
                "PeopleServiceTest",
                "PersonServiceTest",
                "RegisterServiceTest",
                "FillServiceTest",
                "ClearServiceTest");
    }
}
