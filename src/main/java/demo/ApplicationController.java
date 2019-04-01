package demo;

import java.util.Date;
import java.util.Optional;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ApplicationController {

    @GetMapping("/data")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="anonymous") String name,
            @RequestParam(name="db", required=false, defaultValue="test") String dbName,
            @RequestParam(name="collection", required=false, defaultValue="bechdel") String collName,
            Model model) {

        UserViewsDao.saveUserView(name, new Date(), dbName, collName);
        final GenericDao dao = new GenericDao(dbName, collName);

        model.addAttribute("dbName", dbName);
        model.addAttribute("collName", collName);
        model.addAttribute("count", dao.countDocuments());
        model.addAttribute("name", name);
        model.addAttribute("review", new Review());

        return "data";
    }

    @PostMapping("/data")
    public RedirectView submitReview(@ModelAttribute Review review) {
        review.setDate(new Date());
        ReviewsDao.saveReview(review);
        return new RedirectView("/reviews");
    }

    @GetMapping("/userViews")
    public String userViews(Model model) {

        final Optional<Document> lastViewer = UserViewsDao.getMostRecentViewer();
        final String viewerName = lastViewer.isPresent() ? lastViewer.get().getString(UserViewsDao.USER_FIELD) : "nobody!";

        model.addAttribute("user", viewerName);

        return "userViews";
    }

    @GetMapping("/reviews")
    public String reviews(Model model) {

        model.addAttribute("reviews", ReviewsDao.findReviews(10));
        return "reviews";
    }
}
