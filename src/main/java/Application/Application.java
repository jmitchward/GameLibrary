package Application;

import DAO.LibraryNotFound;
import Controller.LibraryController;
import ServiceLayer.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) throws InvalidDateException, InvalidGenreSelection, InvalidRatingSelection, GameNotFoundException, EmptyFieldException, DuplicateIdException, InvalidPlatformException, LibraryNotFound, InvalidPlatformException, DuplicateIdException {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        LibraryController controller = context.getBean("controller", LibraryController.class);
        controller.runLibrary();
    }
}
