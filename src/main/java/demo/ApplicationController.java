package demo;

import java.util.Date;
import java.util.Optional;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApplicationController {

    @GetMapping("/data")
    public String greeting(
            @RequestParam(name="name") String name,
            @RequestParam(name="db", required=false, defaultValue="test") String dbName,
            @RequestParam(name="collection", required=false, defaultValue="bechdel") String collName,
            Model model) {

        UserViewsDao.saveUserView(name, new Date(), dbName, collName);
        final GenericDao dao = new GenericDao(dbName, collName);

        model.addAttribute("dbName", dbName);
        model.addAttribute("collName", collName);
        model.addAttribute("count", dao.countDocuments());
        model.addAttribute("name", name);

        return "data";
    }

    @GetMapping("/userViews")
    public String userViews(Model model) {

        final Optional<Document> lastViewer = UserViewsDao.getMostRecentViewer();
        final String viewerName = lastViewer.isPresent() ? lastViewer.get().getString(UserViewsDao.USER_FIELD) : "nobody!";

        model.addAttribute("user", viewerName);

        return "userViews";
    }
}
